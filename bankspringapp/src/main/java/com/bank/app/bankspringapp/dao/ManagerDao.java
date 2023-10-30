package com.bank.app.bankspringapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankspringapp.dto.Manager;
import com.bank.app.bankspringapp.repo.ManagerRepo;

@Repository
public class ManagerDao {
	@Autowired
	ManagerRepo managerrepo;

	public Manager saveManager(Manager manager) {
		return managerrepo.save(manager);

	}

	public Manager findMannager(int managerId) {
		Optional<Manager> optManager = managerrepo.findById(managerId);
		if (optManager.isPresent()) {
			return optManager.get();
		}
		return null;
	}

	public Manager deleteManager(int managerId) {
		Manager exiManager = findMannager(managerId);
		if (exiManager != null) {
			managerrepo.delete(exiManager);
			return exiManager;
		}
		return null;

	}

	public Manager updateManager(Manager manager, int managerId) {
		Manager exiManager = findMannager(managerId);
		if (exiManager != null) {
			manager.setManagerId(managerId);
			managerrepo.save(manager);
			return manager;
		}
		return null;

	}

	public List<Manager> findAllManager() {
		return managerrepo.findAll();
	}

	public Manager loginManager(String managerMail, String managerPass) {
		Manager exiManager = managerrepo.getByMail(managerMail);
		if (exiManager != null) {
			if (exiManager.getManagerPass().equals(managerPass)) {
				return exiManager;
			}
			return null;
		}
		return null;
	}

}
