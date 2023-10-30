package com.bank.app.bankspringapp.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Component
public class Manager {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int managerId;
	private String managerName;
	private String managerEmail;
	private String managerPass;
	private long managerContact;
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getManagerPass() {
		return managerPass;
	}
	public void setManagerPass(String managerPass) {
		this.managerPass = managerPass;
	}
	public long getManagerContact() {
		return managerContact;
	}
	public void setManagerContact(long managerContact) {
		this.managerContact = managerContact;
	}

}
