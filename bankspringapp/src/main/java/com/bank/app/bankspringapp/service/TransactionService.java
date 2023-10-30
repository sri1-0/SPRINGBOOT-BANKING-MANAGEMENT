package com.bank.app.bankspringapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.app.bankspringapp.config.ResponseStructure;
import com.bank.app.bankspringapp.dao.AcoountDao;
import com.bank.app.bankspringapp.dao.TransactionDao;
import com.bank.app.bankspringapp.dao.UserDao;
import com.bank.app.bankspringapp.dto.Account;
import com.bank.app.bankspringapp.dto.Transaction;
import com.bank.app.bankspringapp.dto.TransactionStatus;
import com.bank.app.bankspringapp.dto.User;
import com.bank.app.bankspringapp.exception.AccountNotFound;
import com.bank.app.bankspringapp.exception.AccountTypeNotFound;
import com.bank.app.bankspringapp.exception.UserContactNotFound;
import com.bank.app.bankspringapp.exception.UserNotFound;

@Service
public class TransactionService {
	@Autowired
	TransactionDao tranDao;
	@Autowired
	AcoountDao accountDao;
	@Autowired
	UserDao uDao;

	public ResponseEntity<ResponseStructure<Transaction>> saveTransaction(int amt, int accountId,
			int transactionStatus) {
		Account exiAccount = accountDao.findAccount(accountId);

		if (exiAccount != null) {
			User exiUser = exiAccount.getUser();
			if (exiUser != null) {
				Transaction newTransaction = new Transaction();
				newTransaction.setTransactionDate(LocalDate.now());
				newTransaction.setTransactionAmount(amt);
				if (covertTransaction(transactionStatus) == TransactionStatus.CREDITING) {
					newTransaction.setStatus(covertTransaction(transactionStatus));
					exiAccount.setAccountBalance(exiAccount.getAccountBalance() + amt);
				} else if (covertTransaction(transactionStatus) == TransactionStatus.DEBITING) {
					newTransaction.setStatus(covertTransaction(transactionStatus));
					exiAccount.setAccountBalance(exiAccount.getAccountBalance() - amt);
				} else {
					throw new AccountTypeNotFound();
				}
				tranDao.createTransaction(newTransaction);
				exiAccount.getAccountTransaction().add(newTransaction);
				accountDao.updateAccount(exiAccount, accountId);

				ResponseStructure<Transaction> structure = new ResponseStructure<Transaction>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("transaction completed");
				structure.setData(newTransaction);

				exiUser.setUserAccount(exiAccount);
				uDao.updateUser(exiUser, exiUser.getUserId());

				return new ResponseEntity<ResponseStructure<Transaction>>(structure, HttpStatus.OK);
			}
			throw new AccountNotFound();
		}
		throw new UserNotFound();
	}

	public TransactionStatus covertTransaction(int transaction) {
		if (transaction == 1) {
			return TransactionStatus.CREDITING;
		} else if (transaction == 2) {
			return TransactionStatus.DEBITING;

		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<List<Transaction>>> allTransaction(String userName, long userContact) {
		User exiUser = uDao.loginUser(userName, userContact);
		if (exiUser != null) {
			if (exiUser.getUserContact() == userContact) {
				Account exiAcc = exiUser.getUserAccount();
				if (exiAcc != null) {
					List<Transaction> allTransaction = exiAcc.getAccountTransaction();
					ResponseStructure<List<Transaction>> structure = new ResponseStructure<List<Transaction>>();
					structure.setStatus(HttpStatus.FOUND.value());
					structure.setMessage("TRANSACTION DISPLAYED");
					structure.setData(allTransaction);
					return new ResponseEntity<ResponseStructure<List<Transaction>>>(structure, HttpStatus.NOT_FOUND);
				}
				throw new AccountNotFound();
			}
			throw new UserContactNotFound();
		}
		throw new UserNotFound("USER CREDENTIAL IS WRONG");
	}

}
