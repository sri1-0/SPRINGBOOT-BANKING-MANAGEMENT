package com.bank.app.bankspringapp.exception;

public class UserContactNotFound extends RuntimeException{
	private String message="USER NOT FOUND";

	public String getMessage() {
		return message;
	}

	public UserContactNotFound() {
		super();
	}

	public UserContactNotFound(String message) {
		this.message = message;
	}
	
}
