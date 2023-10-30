package com.bank.app.bankspringapp.exception;

public class ManagerNotFound extends RuntimeException{
	private String message="MANAGER NOT FOUND";

	public String getMessage() {
		return message;
	}

	public ManagerNotFound() {
		super();
	}

	public ManagerNotFound(String message) {
		this.message = message;
	}
	
}
