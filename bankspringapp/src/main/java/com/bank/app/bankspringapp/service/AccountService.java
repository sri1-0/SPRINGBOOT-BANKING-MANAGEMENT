package com.bank.app.bankspringapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.app.bankspringapp.config.ResponseStructure;
import com.bank.app.bankspringapp.dao.AcoountDao;
import com.bank.app.bankspringapp.dao.ManagerDao;
import com.bank.app.bankspringapp.dao.UserDao;
import com.bank.app.bankspringapp.dto.Account;
import com.bank.app.bankspringapp.dto.AccountType;
import com.bank.app.bankspringapp.dto.Manager;
import com.bank.app.bankspringapp.dto.Transaction;
import com.bank.app.bankspringapp.dto.User;
import com.bank.app.bankspringapp.exception.ManagerMailNotFound;
import com.bank.app.bankspringapp.exception.ManagerPassNotFound;
import com.bank.app.bankspringapp.exception.UserContactNotFound;
import com.bank.app.bankspringapp.exception.UserNotFound;
import com.bank.app.bankspringapp.repo.ManagerRepo;
import com.bank.app.bankspringapp.repo.UserRepo;

@Service
public class AccountService {
	@Autowired
	AcoountDao accountDao;
	@Autowired
	ManagerRepo mRepo;
	@Autowired
	UserRepo uRepo;
	@Autowired
	UserDao uDao;

	public ResponseEntity<ResponseStructure<Account>> saveAccount(Account account, String managerMail,
			String managerPass, int userId, int accountType) {
		User exiUser = uDao.findUser(userId);
		Manager manager = mRepo.getByMail(managerMail);
		if (manager != null) {
			if (manager.getManagerPass().equals(managerPass)) {
				if (exiUser != null) {
					account.setAccountType(convertAccountType(accountType));
					ResponseStructure<Account> structure = new ResponseStructure<Account>();
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setMessage("Account added");
					account.setUser(exiUser);
					structure.setData(accountDao.saveAccount(account));

					exiUser.setUserAccount(account);
					uDao.updateUser(exiUser, userId);

					return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED);
				}
				throw new UserNotFound("USER CREDENTIAL IS WRNOG");
			}
			throw new ManagerPassNotFound();
		}
		throw new ManagerMailNotFound();
	}

	public ResponseEntity<ResponseStructure<Account>> findAccount(int accountId) {
		Account exiAccount = accountDao.findAccount(accountId);
		if (exiAccount != null) {
			ResponseStructure<Account> structure = new ResponseStructure<Account>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Account founded");
			structure.setData(exiAccount);
			return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.FOUND);

		}
		throw new UserContactNotFound();
	}

	public ResponseEntity<ResponseStructure<Account>> deleteAccount(int accountId, String managerMail,
			String managerPass, String userName, long userContact) {
		Manager managerPermission = mRepo.getByMail(managerMail);
		User userPermission = uRepo.getByMail(userName);
		if (managerPermission != null) {
			if (managerPermission.getManagerPass().equals(managerPass)) {
				if (userPermission != null) {
					if (userPermission.getUserContact() == userContact) {
						ResponseStructure<Account> structure = new ResponseStructure<Account>();
						structure.setStatus(HttpStatus.FOUND.value());
						structure.setMessage("Account founded");
						structure.setData(accountDao.deleteAccount(accountId));
						return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.FOUND);

					}
					throw new UserContactNotFound();

				}
				throw new UserNotFound("USER CREDENTIAL IS WRNOG");
			}
			throw new ManagerPassNotFound();

		}
		throw new ManagerMailNotFound();
	}

	public ResponseEntity<ResponseStructure<Account>> updateAccountType(int accountId, String userName,
			long userContact, int accountType) {
		User userPermission = uRepo.getByMail(userName);
		if (userPermission != null) {
			if (userPermission.getUserContact() == userContact) {
				ResponseStructure<Account> structure = new ResponseStructure<Account>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Account Type Updated");
				structure.setData(accountDao.updateAccountType(convertAccountType(accountType), accountId));
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.OK);

			}
			throw new UserContactNotFound();
		}
		throw new UserNotFound("USER CREDENTIAL IS WRNOG");
	}

	public ResponseEntity<ResponseStructure<Double>> accountBalance(int accountId, String userName, long userContact) {
		User userPermission = uRepo.getByMail(userName);
		if (userPermission != null) {
			if (userPermission.getUserContact() == userContact) {
				Account account = accountDao.findAccount(accountId);
				double accountBal = account.getAccountBalance();
				ResponseStructure<Double> structure = new ResponseStructure<Double>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Account Balance Displayed");
				structure.setData(accountBal);
				return new ResponseEntity<ResponseStructure<Double>>(structure, HttpStatus.OK);

			}
			throw new UserContactNotFound();

		}
		throw new UserNotFound("USER CREDENTIAL IS WRNOG");
	}

	public ResponseEntity<ResponseStructure<List<Transaction>>> getAccountTransaction(int accountId, String userName,
			long userContact) {
		User userPermission = uRepo.getByMail(userName);
		if (userPermission != null) {
			if (userPermission.getUserContact() == userContact) {
				Account account = accountDao.findAccount(accountId);
				List<Transaction> accountTransaction = account.getAccountTransaction();
				ResponseStructure<List<Transaction>> structure = new ResponseStructure<List<Transaction>>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Account Transaction Displayed");
				structure.setData(accountTransaction);
				return new ResponseEntity<ResponseStructure<List<Transaction>>>(structure, HttpStatus.OK);
			}
			throw new UserContactNotFound();

		}
		throw new UserNotFound("USER CREDENTIAL IS WRNOG");
	}

	public AccountType convertAccountType(int accountType) {
		if (accountType == 1) {
			return AccountType.SAVINGS;

		} else if (accountType == 2) {
			return AccountType.CURRENT;
		} else {
			return AccountType.LOAN;
		}

	}
}
