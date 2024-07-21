package com.example.onlineShopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineShopping.dao.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);
	
	 Optional<User> findByEmail(String email);
}
