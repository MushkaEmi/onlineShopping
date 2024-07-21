package com.example.onlineShopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineShopping.dao.entity.Courier;
import com.example.onlineShopping.model.CourierStatus;

public interface CourierRepository extends JpaRepository<Courier, Long> {
	Optional<Courier> findById(Long id);

	Optional<Courier> findByCourierStatus(CourierStatus courierStatus);
}
