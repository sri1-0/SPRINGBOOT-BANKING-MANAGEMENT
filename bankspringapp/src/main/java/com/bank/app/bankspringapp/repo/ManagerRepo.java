package com.bank.app.bankspringapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.app.bankspringapp.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {
	@Query("select m from Manager m where m.managerEmail=?1")
	public  Manager getByMail(String managerEmail);
	
	@Query("select m from Manager m where m.managerEmail=?1 and managerPass=?2")
	public  Manager getByPass(String managerEmail,String managerPass);
	
}
