package com.bank.app.bankspringapp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankspringapp.dto.Bank;
import com.bank.app.bankspringapp.repo.BankRepo;

@Repository
public class BankDao {
	@Autowired
	BankRepo bankRepo;

	public Bank saveBank(Bank bank) {
		return bankRepo.save(bank);
	}

	public Bank findBank(int bankId) {
		Optional<Bank> optBank = bankRepo.findById(bankId);
		if (optBank.isPresent()) {
			return optBank.get();
		}
		return null;
	}

	public Bank dleteBank(int bankId) {
		Bank exiBank = findBank(bankId);
		if (exiBank != null) {
			bankRepo.delete(exiBank);
			return exiBank;
		}
		return null;

	}

	public Bank updateBank(int bankId, Bank bank) {
		Bank exiBank = findBank(bankId);
		if (exiBank != null) {
			bank.setBankid(bankId);
			bankRepo.save(bank);
			return bank;
		}
		return null;
	}

}
