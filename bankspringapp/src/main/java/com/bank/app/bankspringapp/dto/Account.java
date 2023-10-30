package com.bank.app.bankspringapp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Component
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
	private long accountNumber;
	private double accountBalance;
	private AccountType accountType;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> accountTransaction;
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Transaction> getAccountTransaction() {
		return accountTransaction;
	}
	public void setAccountTransaction(List<Transaction> accountTransaction) {
		this.accountTransaction = accountTransaction;
	}

}
