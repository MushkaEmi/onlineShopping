package com.example.onlineShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineShopping.dao.entity.Courier;
import com.example.onlineShopping.repository.CourierRepository;

@RestController
@RequestMapping("/courier")
public class CourierController {

	@Autowired
	private CourierRepository courierRepository;

	@PostMapping
	public Courier addCourier(@RequestBody Courier courier) {
		return courierRepository.save(courier);
	}
}
