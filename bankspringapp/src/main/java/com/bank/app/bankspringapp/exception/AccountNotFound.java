package com.bank.app.bankspringapp.exception;

public class AccountNotFound extends RuntimeException{
	private String message="BANK NOT FOUND";

	public String getMessage() {
		return message;
	}

	public AccountNotFound() {
		super();
	}

	public AccountNotFound(String message) {
		this.message = message;
	}}
	


