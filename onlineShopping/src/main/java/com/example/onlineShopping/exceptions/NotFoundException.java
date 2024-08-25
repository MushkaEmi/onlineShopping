package com.example.onlineShopping.exceptions;

public class NotFoundException extends RuntimeException {
	
	private String message;
	
	public NotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
	
}
