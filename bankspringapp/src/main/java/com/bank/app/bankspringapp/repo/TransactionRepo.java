package com.bank.app.bankspringapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.app.bankspringapp.dto.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
//	@Query("SELECT t FROM Transaction t WHERE t.transaction_date BETWEEN +""+ 2001-02-01 10:00:00' AND\n"
//			+ " '2007-03-01 22:00:00'")
//public List<Transaction> dateWiseTransaction( String startDate,String endDate) ;
}
