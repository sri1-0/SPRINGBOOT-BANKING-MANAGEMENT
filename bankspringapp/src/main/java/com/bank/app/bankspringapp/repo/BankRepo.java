package com.bank.app.bankspringapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.bankspringapp.dto.Bank;

public interface BankRepo extends JpaRepository<Bank, Integer> {

}
