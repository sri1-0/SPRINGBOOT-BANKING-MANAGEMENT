package com.bank.app.bankspringapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.bankspringapp.config.ResponseStructure;
import com.bank.app.bankspringapp.dto.Transaction;
import com.bank.app.bankspringapp.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	TransactionService transService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Transaction>> saveTransaction(@RequestParam int amt,@RequestParam int accountId,@RequestParam int transactionStatus) {
		return transService.saveTransaction(amt, accountId, transactionStatus);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Transaction>>> allTransaction(@RequestParam String userName,@RequestParam long userContact) {

		return transService.allTransaction(userName, userContact);

	}}
