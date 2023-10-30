package com.bank.app.bankspringapp.exception;

public class UserMailNotFound extends RuntimeException{
	private String message="USER NOT FOUND";

	public String getMessage() {
		return message;
	}

	public UserMailNotFound() {
		super();
	}

	public UserMailNotFound(String message) {
		this.message = message;
	}
	
}
