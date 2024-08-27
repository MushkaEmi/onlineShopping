package com.example.onlineShopping.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlineShopping.repository.CouponRepository;
import com.example.onlineShopping.repository.CourierRepository;
import com.example.onlineShopping.repository.OrderRepository;
import com.example.onlineShopping.dao.entity.Basket;
import com.example.onlineShopping.dao.entity.Coupon;
import com.example.onlineShopping.dao.entity.Courier;
import com.example.onlineShopping.dao.entity.Order;
import com.example.onlineShopping.dao.entity.Product;
import com.example.onlineShopping.dao.entity.User;
import com.example.onlineShopping.exceptions.NotFoundException;
import com.example.onlineShopping.helper.BasketHelper;
import com.example.onlineShopping.helper.UserHelper;
import com.example.onlineShopping.mapper.Mapper;
import com.example.onlineShopping.model.CourierStatus;
import com.example.onlineShopping.model.OrderDto;
import com.example.onlineShopping.model.Status;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CourierRepository courierRepository;

	@Autowired
	private Mapper mapper;

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private BasketHelper basketHelper;

	@Autowired
	private CouponRepository couponRepository;

	public List<OrderDto> getAllOrdersByUserId(Long userId) {
		List<Order> orders = orderRepository.findByUserId(userId);
		if (orders.isEmpty())
			return new ArrayList<OrderDto>();
		return mapper.orderListEntityToDtoList(orders);
	}

	public OrderDto createOrder(Long userId, String couponCode) {
		User user = userHelper.getUserById(userId);
		Basket basket = basketHelper.getValidBasket(userId);
		Double totalPrice = calculateTotalPrice(basket);

		if (couponCode != null && !couponCode.isEmpty()) {
			Coupon coupon = couponRepository.findByCode(couponCode)
					.orElseThrow(() -> new NotFoundException("Coupon not found"));
			if (isCouponValid(coupon)) {
				totalPrice = applyDiscount(totalPrice, coupon);
			} else {
				throw new NotFoundException("Coupon is expired or not valid");
			}
		}

		List<Product> selectedProducts = new ArrayList<>(basket.getProducts());
		Order order = new Order();
		order.setUser(user);
		order.setProducts(selectedProducts);
		order.setStatus(Status.DELIVERING);
		order.setTotalPrice(totalPrice);

		orderRepository.save(order);

		OrderDto orderDto = mapper.orderEntityToDto(order);

		return orderDto;
	}

	private Double applyDiscount(Double totalPrice, Coupon coupon) {
		Double discountAmount = totalPrice * (coupon.getDiscount() / 100);
		return totalPrice - discountAmount;
	}

	private Double calculateTotalPrice(Basket basket) {
		List<Product> selectedProducts = basket.getProducts();
		Double totalPrice = Double.valueOf(0);
		for (Product product : selectedProducts) {
			totalPrice += product.getPrice();
		}

		return totalPrice;
	}

	private boolean isCouponValid(Coupon coupon) {
		return coupon.getExpiryDate().isAfter(LocalDate.now());
	}

	public OrderDto assignOrderToCourier(Long userId) {
		User user = userHelper.getUserById(userId);
		Optional<Order> optionalOrder = orderRepository.findByUserIdAndStatus(userId, Status.SOLD);
		if (optionalOrder.isEmpty()) {
			throw new NotFoundException("Order not found");
		}
		Optional<Courier> optionalCourier = courierRepository.findByCourierStatus(CourierStatus.FREE);
		if (optionalCourier.isEmpty()) {
			throw new NotFoundException("Free Courier not found");
		}
		Order order = optionalOrder.get();
		Courier courier = optionalCourier.get();
		courier.setCourierStatus(CourierStatus.ON_THE_WAY);
		order.setCourier(courier);
		order.setStatus(Status.DELIVERING);
		orderRepository.save(order);
		return mapper.orderEntityToDto(order);
	}

}
