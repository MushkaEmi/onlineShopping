package com.example.onlineShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineShopping.model.OrderDto;
import com.example.onlineShopping.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/{userId}")
	public List<OrderDto> getAllOrders(@PathVariable("userId") Long userId) {
		return orderService.getAllOrdersByUserId(userId);
	}

	@PostMapping("/{userId}")
	public OrderDto createOrder(@PathVariable("userId") Long userId,
			@RequestParam(required = false) String couponCode) {
		return orderService.createOrder(userId, couponCode);
	}

	@PutMapping("/{userId}")
	public OrderDto assignOrderToCourier(@PathVariable("userId") Long userId) {
		return orderService.assignOrderToCourier(userId);
	}
}
