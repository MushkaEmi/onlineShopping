package com.example.onlineShopping.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class CouponDto {
	private String code;
	private double discount;
	private LocalDate expiryDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
}
