package com.example.onlineShopping.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.onlineShopping.dao.entity.User;
import com.example.onlineShopping.model.LoginUserDto;
import com.example.onlineShopping.model.RegisterUserDto;
import com.example.onlineShopping.repository.UserRepository;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signup(RegisterUserDto input) {
		User user = new User();
		user.setName(input.getName());
		user.setEmail(input.getEmail());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setUserName(input.getUserName());

		return userRepository.save(user);
	}

	public User authenticate(LoginUserDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

		return userRepository.findByEmail(input.getEmail()).orElseThrow();
	}
}
