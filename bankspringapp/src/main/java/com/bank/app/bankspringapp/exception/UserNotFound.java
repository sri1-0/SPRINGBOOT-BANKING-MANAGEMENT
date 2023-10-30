package com.bank.app.bankspringapp.exception;

public class UserNotFound extends RuntimeException{
	private String message="USER NOT FOUND";

	public String getMessage() {
		return message;
	}

	public UserNotFound() {
		super();
	}

	public UserNotFound(String message) {
		this.message = message;
	}
	
}
