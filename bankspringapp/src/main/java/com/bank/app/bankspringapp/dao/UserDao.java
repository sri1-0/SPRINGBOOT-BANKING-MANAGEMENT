package com.bank.app.bankspringapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankspringapp.dto.User;
import com.bank.app.bankspringapp.repo.UserRepo;

@Repository
public class UserDao {
	@Autowired
	UserRepo repo;

	public User saveUser(User user) {
		return repo.save(user);
	}

	public User findUser(int userId) {
		Optional<User> useOptional = repo.findById(userId);
		if (useOptional.isPresent()) {
			return useOptional.get();
		}
		return null;
	}

	public User delteUser(int userId) {
		User exiUser = findUser(userId);
		if (exiUser != null) {
			repo.delete(exiUser);
			return exiUser;
		}
		return null;

	}

	public User updateUser(User user, int userId) {
		User exiUser = findUser(userId);
		if (exiUser != null) {
			user.setUserId(userId);
			repo.save(user);
			return user;
		}
		return null;
	}

	public List<User> findAllUser() {
		return repo.findAll();
	}

	public User chageUserNumber(User user, long userNumber) {
		user.setUserContact(userNumber);
		return user;

	}
	
	public User loginUser(String userName, long userContact) {
		if (repo.getByMail(userName) != null) {
			if (repo.getByPass(userName,userContact) != null) {
				return repo.getByPass(userName,userContact);
			}
			return null;
		}
		return null;
	}
}