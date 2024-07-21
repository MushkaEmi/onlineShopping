package com.example.onlineShopping.model;

import java.util.List;

import com.example.onlineShopping.dao.entity.Rating;

public class ProductDto {
	private String name;
	private Double price;
	private Double ratingAverage;
	private String category;
	private List<Rating> ratings;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getRatingAverage() {
		return ratingAverage;
	}

	public void setRatingAverage(Double ratingAverage) {
		this.ratingAverage = ratingAverage;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
}
