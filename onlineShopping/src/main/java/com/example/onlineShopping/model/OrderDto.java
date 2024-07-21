package com.example.onlineShopping.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.onlineShopping.dao.entity.Courier;
import com.example.onlineShopping.dao.entity.Product;

@Component
public class OrderDto {
	private Long id;
	private List<Product> products;
	private Long userId;
	private Double totalPrice;
	private Status status;
	private Courier courier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}
}
