package com.example.onlineShopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineShopping.dao.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	Optional<Rating> findById(Long id);
}
