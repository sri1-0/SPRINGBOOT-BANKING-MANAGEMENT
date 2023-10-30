package com.bank.app.bankspringapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.app.bankspringapp.config.ResponseStructure;
import com.bank.app.bankspringapp.dao.BankDao;
import com.bank.app.bankspringapp.dao.ManagerDao;
import com.bank.app.bankspringapp.dto.Bank;
import com.bank.app.bankspringapp.dto.Manager;
import com.bank.app.bankspringapp.exception.BankNotFound;
import com.bank.app.bankspringapp.exception.ManagerMailNotFound;
import com.bank.app.bankspringapp.exception.ManagerNotFound;
import com.bank.app.bankspringapp.exception.ManagerPassNotFound;
import com.bank.app.bankspringapp.repo.ManagerRepo;

@Service
public class ManagerService {
	@Autowired
	ManagerDao managerdao;
	@Autowired
	ManagerRepo managerRepo;
	@Autowired
	BankDao bankDao;

	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager manager, int bankId) {
		Bank exiBank = bankDao.findBank(bankId);
		if (exiBank != null) {

			ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("manager added");
			structure.setData(managerdao.saveManager(manager));

			exiBank.setManager(manager);
			bankDao.updateBank(bankId, exiBank);

			return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.CREATED);
		} else {
			throw new BankNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Manager>> findMannager(int managerId) {
		Manager exiManager = managerdao.findMannager(managerId);
		if (exiManager != null) {
			ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("manager foundeded");
			structure.setData(exiManager);
			return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.FOUND);
		} else {
			throw new ManagerNotFound();
		}

	}

	public ResponseEntity<ResponseStructure<Manager>> updateManager(Manager manager, int managerId) {
		Manager exiManager = managerdao.findMannager(managerId);
		if (exiManager != null) {
			ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("manager updated");
			structure.setData(managerdao.updateManager(manager, managerId));
			return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.OK);
		} 
		throw new ManagerNotFound();
	}

	public ResponseEntity<ResponseStructure<List<Manager>>> findAllManager() {
		ResponseStructure<List<Manager>> structure = new ResponseStructure<List<Manager>>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Manager Displayed");
		structure.setData(managerdao.findAllManager());
		return new ResponseEntity<ResponseStructure<List<Manager>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Manager>> deleteManager(int managerId, int bankId) {
		Bank exiBank = bankDao.findBank(bankId);
		if (exiBank != null) {
			if (exiBank.getManager().getManagerId() == managerId) {
				exiBank.setManager(null);
				bankDao.updateBank(bankId, exiBank);
				ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("manager deleted");
				structure.setData(managerdao.deleteManager(managerId));
				return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.OK);
			}
			throw new ManagerNotFound();
		} else {
			throw new BankNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Manager>> loginManager(String managerEmail, String managerPass) {
		Manager manager = managerRepo.getByMail(managerEmail);
		ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
		if (manager != null) {
			if (manager.getManagerPass().equals(managerPass)) {
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage("welcome Manager");
				structure.setData(manager);
				return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.OK);
			}
			throw new ManagerPassNotFound();
		}
		throw new ManagerMailNotFound();
	}
}
