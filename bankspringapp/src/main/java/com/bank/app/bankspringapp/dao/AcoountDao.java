package com.bank.app.bankspringapp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankspringapp.dto.Account;
import com.bank.app.bankspringapp.dto.AccountType;
import com.bank.app.bankspringapp.repo.AccountRepo;

@Repository
public class AcoountDao {
	@Autowired
	AccountRepo accrepo;
	
	public Account saveAccount(Account account) {
		return accrepo.save(account);
	}
	public Account findAccount(int accountId) {
		Optional<Account> optAccount=accrepo.findById(accountId);
		if (!optAccount.isEmpty()) {
			return optAccount.get();
		}
		return null;
	}
	public Account deleteAccount(int accountId) {
		Account exiAccount=findAccount(accountId);
		accrepo.delete(exiAccount);
		return exiAccount;
	}
	public Account updateAccount(Account account,int accountId) {
		Account exiAccount=findAccount(accountId);
		if (exiAccount!=null) {
			account.setAccountId(accountId);
			return accrepo.save(account);
			}
		return null;
	}
	public Account updateAccountType(AccountType accountType,int accountId) {
		Account exiAccount=findAccount(accountId);
		if (exiAccount!=null) {
			exiAccount.setAccountType(accountType);
			return accrepo.save(exiAccount);
			}
		return null;
	}

}
