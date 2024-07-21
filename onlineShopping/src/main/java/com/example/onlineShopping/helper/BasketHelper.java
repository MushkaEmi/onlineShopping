package com.example.onlineShopping.helper;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.onlineShopping.constant.ExceptionMessages;
import com.example.onlineShopping.dao.entity.Basket;
import com.example.onlineShopping.exceptions.NotFoundException;
import com.example.onlineShopping.repository.BasketRepository;


@Component
public class BasketHelper {
	
	@Autowired
	private BasketRepository basketRepository;

	public Basket getValidBasket(Long userId) {
		Optional<Basket> basket = basketRepository.findTop1ByUserIdOrderByIdDesc(userId);
		if (basket.isEmpty() || basket.get().getExpireDate().isBefore(LocalDateTime.now())) {
			throw new NotFoundException(ExceptionMessages.BASKET_NOT_FOUND);
		}

		if (basket.get().getProducts().isEmpty())
			throw new NotFoundException("Products are sold");
		return basket.get();
	}
}
