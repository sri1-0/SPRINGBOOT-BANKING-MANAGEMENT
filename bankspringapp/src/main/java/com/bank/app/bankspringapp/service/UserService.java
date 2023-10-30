package com.bank.app.bankspringapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.app.bankspringapp.config.ResponseStructure;
import com.bank.app.bankspringapp.dao.AcoountDao;
import com.bank.app.bankspringapp.dao.BankDao;
import com.bank.app.bankspringapp.dao.ManagerDao;
import com.bank.app.bankspringapp.dao.UserDao;
import com.bank.app.bankspringapp.dto.Bank;
import com.bank.app.bankspringapp.dto.Manager;
import com.bank.app.bankspringapp.dto.User;
import com.bank.app.bankspringapp.exception.BankNotFound;
import com.bank.app.bankspringapp.exception.ManagerNotFound;
import com.bank.app.bankspringapp.exception.UserNotFound;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	BankDao bankDao;
	@Autowired
	ManagerDao managerDao;
	@Autowired
	AcoountDao accountDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user, int bankId) {
		Bank exiBank = bankDao.findBank(bankId);
		if (exiBank != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("user is created");
			structure.setData(userDao.saveUser(user));

			List<User> exiUsers = exiBank.getUsers();
			exiUsers.add(user);
			exiBank.setUsers(exiUsers);
			bankDao.updateBank(bankId, exiBank);

			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
		}
		throw new BankNotFound();

	}

	public ResponseEntity<ResponseStructure<User>> findUser(int userId) {
		User exiUser = userDao.findUser(userId);
		if (exiUser != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("user is founded");
			structure.setData(exiUser);
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}
		throw new UserNotFound();

	}

	public ResponseEntity<ResponseStructure<User>> delteUser(int userId, int bankId) {
		Bank exiBank = bankDao.findBank(bankId);
		if (exiBank != null) {
			User exiUser = userDao.findUser(userId);
			if (exiUser != null) {
				exiBank.getUsers().remove(exiUser);

				ResponseStructure<User> structure = new ResponseStructure<User>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("user is deleted");
				structure.setData(userDao.delteUser(userId));
				bankDao.updateBank(bankId, exiBank);
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new UserNotFound();// usernot
		}
		throw new BankNotFound();// bank
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user, int userId, String userName, long userContact,
			String managerMail, String managerPass) {
		User userPermission = loginUser(userName, userContact);
		Manager managerPermission = managerDao.loginManager(managerMail, managerPass);
		if (userPermission != null) {
			if (managerPermission != null) {
				ResponseStructure<User> structure = new ResponseStructure<User>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("user is updated");
				structure.setData(userDao.updateUser(user, userId));
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new ManagerNotFound("MANAGER CREDENTIAL IS WRONG");
		}
		throw new UserNotFound("USER DEATAILS IS WRONG");
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAllUser(String managerMail, String managerPass) {
		Manager managerPermission = managerDao.loginManager(managerMail, managerPass);
		if (managerPermission != null) {
			ResponseStructure<List<User>> structure = new ResponseStructure<List<User>>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("all user is displayed");
			List<User> users = userDao.findAllUser();
			structure.setData(users);
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		}
		throw new ManagerNotFound("YOU ARE NOT AUTHORISED");
	}

	public ResponseEntity<ResponseStructure<User>> chageUserNumber(int userId, long userNumber, String userName,
			long userContact, String managerMail, String managerPass) {
		User userPermission = loginUser(userName, userContact);
		Manager managerPermission = managerDao.loginManager(managerMail, managerPass);
		if (userPermission != null) {
			if (managerPermission != null) {
				User exiuser = userDao.findUser(userId);
				User updatedUser = userDao.chageUserNumber(exiuser, userNumber);
				User user = userDao.updateUser(updatedUser, userId);
				ResponseStructure<User> structure = new ResponseStructure<User>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("user contact number is changed");
				structure.setData(user);
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new ManagerNotFound("MANAGER DEATAILS IS WRONG");
		}
		throw new UserNotFound("USER CREDDENTIAL IS WRONG");
	}

	public User loginUser(String userName, long userContact) {
		return userDao.loginUser(userName, userContact);
	}

}
