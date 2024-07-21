package com.example.onlineShopping.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.onlineShopping.dao.entity.Product;

@Component
public class BasketDto {
	private Long id;
	private LocalDateTime expireDate;
	private List<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
