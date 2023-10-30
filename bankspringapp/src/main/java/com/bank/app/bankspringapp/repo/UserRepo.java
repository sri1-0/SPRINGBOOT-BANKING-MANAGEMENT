package com.bank.app.bankspringapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.app.bankspringapp.dto.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.userName=?1")
	public  User getByMail(String userName);
	
	@Query("select u from User u where u.userName=?1 and u.userContact=?2")
	public  User getByPass(String userName,long userContact);

}
