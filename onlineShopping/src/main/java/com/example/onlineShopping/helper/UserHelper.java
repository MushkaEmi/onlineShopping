package com.example.onlineShopping.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.onlineShopping.constant.ExceptionMessages;
import com.example.onlineShopping.dao.entity.User;
import com.example.onlineShopping.exceptions.NotFoundException;
import com.example.onlineShopping.repository.UserRepository;


@Component
public class UserHelper {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new NotFoundException(ExceptionMessages.USER_NOT_FOUND);
		}
		return user.get();
	}
}
