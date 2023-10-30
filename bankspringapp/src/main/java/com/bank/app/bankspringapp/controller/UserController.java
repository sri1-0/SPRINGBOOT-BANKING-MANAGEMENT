package com.bank.app.bankspringapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.bankspringapp.config.ResponseStructure;
import com.bank.app.bankspringapp.dto.User;
import com.bank.app.bankspringapp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user,@RequestParam int bankId) {
		return userService.saveUser(user, bankId);
	}

	@GetMapping("/findUser")
	public ResponseEntity<ResponseStructure<User>> findUser(@RequestParam int userId) {
		return userService.findUser(userId);
	}

	@GetMapping("/deleteUser")
	public ResponseEntity<ResponseStructure<User>> delteUser(@RequestParam int userId,@RequestParam int bankId) 
	{
		return userService.delteUser(userId, bankId);
	}

	@GetMapping("/updateUser")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user, @RequestParam int userId,@RequestParam String userName,@RequestParam long userContact,@RequestParam String managerMail,@RequestParam String managerPass) {
		return userService.updateUser(user, userId, userName, userContact, managerMail, managerPass);
	}

	@GetMapping("/findAllUser")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser(@RequestParam String managerMail,@RequestParam String managerPass) {
		 	return userService.findAllUser(managerMail, managerPass);
	}

	@GetMapping("/chageUserNumber")
	public ResponseEntity<ResponseStructure<User>> chageUserNumber(@RequestParam int userId,@RequestParam long userNumber,@RequestParam String userName,@RequestParam long userContact,@RequestParam String managerMail,@RequestParam String managerPass) {
			return userService.chageUserNumber(userId, userNumber, userName, userContact, managerMail, managerPass);
	}
	@GetMapping("/login")
	public User loginUser(String userName, long userContact) {
		return userService.loginUser(userName, userContact);
	}
}
