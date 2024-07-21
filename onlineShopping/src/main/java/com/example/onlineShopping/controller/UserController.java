package com.example.onlineShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineShopping.dao.entity.User;
import com.example.onlineShopping.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
}