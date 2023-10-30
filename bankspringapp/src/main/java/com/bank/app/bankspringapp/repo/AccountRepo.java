package com.bank.app.bankspringapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.bankspringapp.dto.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}
