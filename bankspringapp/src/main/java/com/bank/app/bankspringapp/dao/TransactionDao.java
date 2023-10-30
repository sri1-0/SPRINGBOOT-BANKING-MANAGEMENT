package com.bank.app.bankspringapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankspringapp.dto.Transaction;
import com.bank.app.bankspringapp.repo.TransactionRepo;

@Repository
public class TransactionDao {
@Autowired
TransactionRepo tranRepo;

public Transaction createTransaction(Transaction transaction) {
	return tranRepo.save(transaction);
}
}
