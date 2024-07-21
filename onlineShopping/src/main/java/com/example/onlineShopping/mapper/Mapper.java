package com.example.onlineShopping.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.onlineShopping.dao.entity.Basket;
import com.example.onlineShopping.dao.entity.Coupon;
import com.example.onlineShopping.dao.entity.Order;
import com.example.onlineShopping.dao.entity.Product;
import com.example.onlineShopping.dao.entity.Rating;
import com.example.onlineShopping.model.BasketDto;
import com.example.onlineShopping.model.CouponDto;
import com.example.onlineShopping.model.OrderDto;
import com.example.onlineShopping.model.ProductDto;
import com.example.onlineShopping.model.RatingDto;

@Component
public class Mapper {

	public Product productDtoToEntity(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setRatingAverage(productDto.getRatingAverage());
		product.setCategory(productDto.getCategory());
		return product;
	}

	public List<Product> productDtoListToEntityList(List<ProductDto> productDtos) {
		List<Product> products = new ArrayList<Product>();
		for (ProductDto productDto : productDtos) {
			Product product = productDtoToEntity(productDto);
			products.add(product);
		}
		return products;
	}

	public ProductDto productEntityToDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setRatingAverage(product.getRatingAverage());
		productDto.setCategory(product.getCategory());
		return productDto;
	}

	public List<ProductDto> productEntityListToDtoList(List<Product> products) {
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		for (Product product : products) {
			ProductDto productDto = productEntityToDto(product);
			productDtos.add(productDto);
		}
		return productDtos;
	}

	public BasketDto basketEntityToDto(Basket basket) {
		BasketDto basketDto = new BasketDto();
		basketDto.setExpireDate(basket.getExpireDate());
		basketDto.setId(basket.getId());
		basketDto.setProducts(basket.getProducts());
		return basketDto;
	}

	public Basket basketDtoToEntity(BasketDto basketDto) {
		Basket basket = new Basket();
		basket.setExpireDate(basketDto.getExpireDate());
		return basket;
	}

	public OrderDto orderEntityToDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setProducts(order.getProducts());
		orderDto.setUserId(order.getUser().getId());
		orderDto.setStatus(order.getStatus());
		orderDto.setTotalPrice(order.getTotalPrice());
		return orderDto;
	}

	public List<OrderDto> orderListEntityToDtoList(List<Order> orders) {
		List<OrderDto> orderDtos = new ArrayList<>();

		for (Order order : orders) {
			OrderDto orderDto = orderEntityToDto(order);
			orderDtos.add(orderDto);
		}
		return orderDtos;
	}

	public Coupon couponDtoToEntity(CouponDto couponDto) {
		Coupon coupon = new Coupon();
		coupon.setCode(couponDto.getCode());
		coupon.setDiscount(couponDto.getDiscount());
		coupon.setExpiryDate(couponDto.getExpiryDate());
		return coupon;
	}

	public CouponDto couponEntityToDto(Coupon Coupon) {
		CouponDto couponDto = new CouponDto();
		couponDto.setCode(couponDto.getCode());
		couponDto.setDiscount(couponDto.getDiscount());
		couponDto.setExpiryDate(couponDto.getExpiryDate());
		return couponDto;
	}

	public RatingDto ratingEntityToDto(Rating rating) {
		RatingDto ratingDto = new RatingDto();
		ratingDto.setComment(rating.getComment());
		ratingDto.setUserId(rating.getUser().getId());
		ratingDto.setRating(rating.getRating());
		return ratingDto;
	}
	
	public List<RatingDto> ratingEntityListToDto(List<Rating> ratings) {
		List<RatingDto> ratingDtos = new ArrayList<>();
		for (Rating rating : ratings) {
			RatingDto ratingDto = ratingEntityToDto(rating);
			ratingDtos.add(ratingDto);
		}
		return ratingDtos;
	}
}
