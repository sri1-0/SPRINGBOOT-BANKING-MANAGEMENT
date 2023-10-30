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
import com.bank.app.bankspringapp.dto.Bank;
import com.bank.app.bankspringapp.dto.Manager;
import com.bank.app.bankspringapp.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	ManagerService managerService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Manager>> saveManager(@RequestBody Manager manager,@RequestParam int bankId) {
		return managerService.saveManager(manager, bankId);
	}
    @GetMapping
	public ResponseEntity<ResponseStructure<Manager>> findMannager(@RequestParam int managerId) {
		return managerService.findMannager(managerId);
	}

	@GetMapping("/updateManager")
	public ResponseEntity<ResponseStructure<Manager>> updateManager(@RequestBody Manager manager,@RequestParam int managerId) {
		return managerService.updateManager(manager, managerId);
	}

	@GetMapping("/findAllManager")
	public ResponseEntity<ResponseStructure<List<Manager>>> findAllManager() {
		return managerService.findAllManager();
	}

	@PostMapping("/deleteManager")
	public ResponseEntity<ResponseStructure<Manager>> deleteManager(@RequestParam int managerId,@RequestParam int bankId) {
		return managerService.deleteManager(managerId,bankId);
	}
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<Manager>> loginManager(@RequestParam String managerEmail,@RequestParam String managerPass) {
		return managerService.loginManager(managerEmail,managerPass);
	}
}
