package com.bank.app.bankspringapp.exception;

public class ManagerMailNotFound extends RuntimeException{
	private String message="USER NOT FOUND";

	public String getMessage() {
		return message;
	}

	public ManagerMailNotFound() {
		super();
	}

	public ManagerMailNotFound(String message) {
		this.message = message;
	}
	
}
