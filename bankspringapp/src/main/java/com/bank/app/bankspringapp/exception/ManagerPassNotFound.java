package com.bank.app.bankspringapp.exception;

public class ManagerPassNotFound extends RuntimeException{
	private String message="USER NOT FOUND";

	public String getMessage() {
		return message;
	}

	public ManagerPassNotFound() {
		super();
	}

	public ManagerPassNotFound(String message) {
		this.message = message;
	}
	
}
