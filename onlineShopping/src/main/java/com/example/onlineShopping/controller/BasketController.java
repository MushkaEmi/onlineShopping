package com.example.onlineShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineShopping.dao.entity.Basket;
import com.example.onlineShopping.model.BasketDto;
import com.example.onlineShopping.service.BasketService;

@RestController
@RequestMapping("/v1/api/baskets")
public class BasketController {

	@Autowired
	private BasketService basketService;

	@GetMapping("/{userId}")
	public BasketDto findByUserId(@PathVariable("userId") Long userId) {
		return basketService.findByUserId(userId);
	}

	@PostMapping("/{userId}/products")
	public Basket addProductToBasket(@PathVariable("userId") Long userId, @RequestBody List<Long> productIds) {
		return basketService.addProductToBasket(userId, productIds);
	}
}
