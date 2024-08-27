package com.example.onlineShopping.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlineShopping.dao.entity.Basket;
import com.example.onlineShopping.dao.entity.Product;
import com.example.onlineShopping.dao.entity.User;
import com.example.onlineShopping.exceptions.NotFoundException;
import com.example.onlineShopping.helper.UserHelper;
import com.example.onlineShopping.mapper.Mapper;
import com.example.onlineShopping.model.BasketDto;
import com.example.onlineShopping.repository.BasketRepository;
import com.example.onlineShopping.repository.ProductRepository;
import com.example.onlineShopping.repository.UserRepository;

@Service
public class BasketService {

	@Autowired
	private BasketRepository basketRepository;

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Mapper mapper;

	public BasketDto findByUserId(Long userId) {
		User user = userHelper.getUserById(userId);
		Optional<Basket> basket = basketRepository.findTop1ByUserIdOrderByIdDesc(userId);
		if (basket.isEmpty() || basket.get().getExpireDate().isBefore(LocalDateTime.now())) {
			Basket createdBasket = new Basket();
			setExpireDate(createdBasket);
			createdBasket.setUser(user);
			createdBasket = basketRepository.save(createdBasket);
			return mapper.basketEntityToDto(createdBasket);
		}
		return mapper.basketEntityToDto(basket.get());
	}

	public Basket addProductToBasket(Long userId, List<Long> productIds) {
		User user = userHelper.getUserById(userId);
		List<Product> selectedProducts = productRepository.findProductsByIdIn(productIds);
		Optional<Basket> basket = basketRepository.findTop1ByUserIdOrderByIdDesc(userId);
		basket.get().setProducts(selectedProducts);
		basketRepository.save(basket.get());
		return basket.get();
	}

	public Basket createBasket(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
		Basket basket = new Basket();
		basket.setUser(user);
		basket.setProducts(new ArrayList<>());
		setExpireDate(basket);

		return basketRepository.save(basket);
	}

	private void setExpireDate(Basket basket) {
		basket.setExpireDate(LocalDateTime.now().plusDays(3));
	}
}
