package com.example.onlineShopping.exceptions;

public class IllegalArgumentException extends RuntimeException {
	private String message;

	public IllegalArgumentException(String message) {
		super(message);
		this.message = message;
	}
}
