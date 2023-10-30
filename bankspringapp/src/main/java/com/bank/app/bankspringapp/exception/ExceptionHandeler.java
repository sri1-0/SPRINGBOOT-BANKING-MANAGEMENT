package com.bank.app.bankspringapp.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.app.bankspringapp.config.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandeler extends ResponseEntityExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Object> handleConstrainViolationExceptionEntity(ConstraintViolationException e) {
		ResponseStructure<Object> structure = new ResponseStructure<Object>();
		Map<String, String> hashMap = new HashMap<>();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			hashMap.put(field, message);
		}
		structure.setMessage("add proper deatail");
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(hashMap);

		return new ResponseEntity<Object>(structure, HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> list = ex.getAllErrors();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		for (ObjectError error : list) {
			String messagwe = error.getDefaultMessage();
			String fieldName = ((FieldError) error).getField();
			hashMap.put(messagwe, fieldName);
		}
		return new ResponseEntity<Object>(hashMap, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("USER NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFound() {
		ResponseStructure<String> structure = new ResponseStructure<String>();
//		structure.setMessage();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("USER NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> accountNotFound(AccountNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("ACCOUNT NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> accountTypeNotFound(AccountTypeNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("ACCOUNT TYPE NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bankNotFound(BankNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("BANK NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> managerMailNotFound(ManagerMailNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("MANAGER MAIL NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> managerNotFound(ManagerNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("MANAGER NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> managerPassNotFound(ManagerPassNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("MANAGER PASSWORD IS WRONG");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userContactNotFound(UserContactNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("USER CONTACT NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userMailNotFound(UserMailNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("USER MAIL IS WRONG");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
