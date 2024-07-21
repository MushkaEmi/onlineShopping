package com.example.onlineShopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineShopping.dao.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findById(Long id);

	List<Product> findProductsByIdIn(List<Long> id);
	
	List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
