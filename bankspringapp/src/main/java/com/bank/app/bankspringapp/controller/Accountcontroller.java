package com.bank.app.bankspringapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.bankspringapp.config.ResponseStructure;
import com.bank.app.bankspringapp.dto.Account;
import com.bank.app.bankspringapp.dto.AccountType;
import com.bank.app.bankspringapp.dto.Manager;
import com.bank.app.bankspringapp.dto.Transaction;
import com.bank.app.bankspringapp.dto.User;
import com.bank.app.bankspringapp.service.AccountService;

@RestController
@RequestMapping("/account")
public class Accountcontroller {
	@Autowired
	AccountService accountService;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Account>> saveAccount(@RequestBody Account account,@RequestParam int userId,
			@RequestParam String managerMail, @RequestParam String managerPass,@RequestParam int accountType) {
		return accountService.saveAccount(account, managerMail, managerPass, userId,accountType);
	}

	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<Account>> findAccount(@RequestParam int accountId) {
		return accountService.findAccount(accountId);
	}

	@GetMapping("/delete")
	public ResponseEntity<ResponseStructure<Account>> deleteAccount(@RequestParam int accountId, @RequestParam String managerMail,
			@RequestParam String managerPass, @RequestParam String userName,@RequestParam long userContact) {
		return accountService.deleteAccount(accountId, managerMail, managerPass, userName, userContact);

	}

	@GetMapping("/accountType")
	public ResponseEntity<ResponseStructure<Account>> updateAccountType(@RequestParam int accountType,@RequestParam int accountId,
			@RequestParam String userName,@RequestParam long userContact) {
		return accountService.updateAccountType(accountId, userName, userContact, accountType);
	}

	@GetMapping("/transaction")
	public ResponseEntity<ResponseStructure<List<Transaction>>> getAccountTransaction(@RequestParam int accountId, @RequestParam String userName,
			@RequestParam	long userContact) {
		return accountService.getAccountTransaction(accountId, userName, userContact);

	}

	@GetMapping("/balance")
	public ResponseEntity<ResponseStructure<Double>> accountBalance(@RequestParam int accountId,@RequestParam String userName,@RequestParam long userContact) {
		return accountService.accountBalance(accountId, userName, userContact);
	}

}
