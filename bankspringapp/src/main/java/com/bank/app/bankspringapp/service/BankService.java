package com.bank.app.bankspringapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.bank.app.bankspringapp.config.ResponseStructure;
import com.bank.app.bankspringapp.dao.BankDao;
import com.bank.app.bankspringapp.dao.ManagerDao;
import com.bank.app.bankspringapp.dao.UserDao;
import com.bank.app.bankspringapp.dto.Bank;
import com.bank.app.bankspringapp.dto.Manager;
import com.bank.app.bankspringapp.dto.User;
import com.bank.app.bankspringapp.exception.BankNotFound;
import com.bank.app.bankspringapp.exception.ManagerNotFound;
import com.bank.app.bankspringapp.repo.ManagerRepo;

@Repository
public class BankService {
	@Autowired
	BankDao bankDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ManagerDao mDao;
	

	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank bank) {
		ResponseStructure<Bank> structure=new ResponseStructure<Bank>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Bank added");
		structure.setData(bankDao.saveBank(bank));
		return new ResponseEntity<ResponseStructure<Bank>>(structure,HttpStatus.CREATED);

	}
	public ResponseEntity<ResponseStructure<Bank>> findBank(int bankId) {
	 Bank exiBank=bankDao.findBank(bankId);
		if (exiBank!=null) {
			ResponseStructure<Bank> structure=new ResponseStructure<Bank>();
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Bank added");
			structure.setData(exiBank);
			return new ResponseEntity<ResponseStructure<Bank>>(structure,HttpStatus.CREATED);
		} else {
throw new BankNotFound();
		}

	}
	
	public ResponseEntity<ResponseStructure<List<User>>> findBankAllUser(String managerEmail, String managerPass) {
		
		Manager exiManager=mDao.loginManager(managerEmail, managerPass);
		
		if (exiManager!=null) {
			ResponseStructure<List<User>> structure=new ResponseStructure<List<User>>();
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Bank user displayed");
			List<User> users=userDao.findAllUser();
			structure.setData(users);
			return new ResponseEntity<ResponseStructure<List<User>>>(structure,HttpStatus.CREATED);
		}
		throw new ManagerNotFound(); //mangernot

	}
	

}
