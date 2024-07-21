package com.example.onlineShopping.model;

public class RegisterUserDto {
	private String email;

	private String password;

	private String name;
	
	private String userName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String fullName) {
		this.name = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userNameString) {
		this.userName = userNameString;
	}
}
