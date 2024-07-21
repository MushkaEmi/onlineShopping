package com.example.onlineShopping.dao.entity;

import java.util.List;

import com.example.onlineShopping.model.CourierStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "couriers")
public class Courier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullName;

	@Enumerated(EnumType.STRING)
	private CourierStatus courierStatus;

	@OneToMany(mappedBy = "courier", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Order> orders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public CourierStatus getCourierStatus() {
		return courierStatus;
	}

	public void setCourierStatus(CourierStatus status) {
		this.courierStatus = status;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
