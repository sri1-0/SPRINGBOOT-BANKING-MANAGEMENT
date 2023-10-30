package com.bank.app.bankspringapp.exception;

public class AccountTypeNotFound extends RuntimeException{
	private String message="BANK NOT FOUND";

	public String getMessage() {
		return message;
	}

	public AccountTypeNotFound() {
		super();
	}

	public AccountTypeNotFound(String message) {
		this.message = message;
	}
}
