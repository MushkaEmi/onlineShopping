package com.example.onlineShopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineShopping.dao.entity.Order;
import com.example.onlineShopping.model.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserId(Long userId);

	Optional<Order> findByUserIdAndId(Long userId, Long id);
	
	Optional<Order> findByUserIdAndStatus(Long userId, Status status);
}
