package com.bank.app.bankspringapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.bankspringapp.config.ResponseStructure;
import com.bank.app.bankspringapp.dto.Bank;
import com.bank.app.bankspringapp.dto.User;
import com.bank.app.bankspringapp.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {
@Autowired
BankService bankService;

@PostMapping("/saveBank")
public ResponseEntity<ResponseStructure<Bank>> saveBank(@RequestBody Bank bank) {
	return bankService.saveBank(bank);

}
@GetMapping("/findBank")
public ResponseEntity<ResponseStructure<Bank>> findBank(@RequestParam int bankId) {
	return bankService.findBank(bankId);

}

@GetMapping("/findBankAllUser")
public ResponseEntity<ResponseStructure<List<User>>> findBankAllUser(String managerEmail, String managerPass) {
		return bankService.findBankAllUser(managerEmail, managerPass);
	}
}
