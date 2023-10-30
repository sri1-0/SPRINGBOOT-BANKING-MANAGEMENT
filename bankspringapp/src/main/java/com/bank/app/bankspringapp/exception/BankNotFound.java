package com.bank.app.bankspringapp.exception;

public class BankNotFound extends RuntimeException{
	private String message="BANK NOT FOUND";

	public String getMessage() {
		return message;
	}

	public BankNotFound() {
		super();
	}

	public BankNotFound(String message) {
		this.message = message;
	}
	
}
