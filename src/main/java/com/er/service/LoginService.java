package com.er.service;

import com.er.model.Employee;
import com.er.repository.LoginRepository;

public class LoginService {
	private LoginRepository loginRepo;
	
	public Employee login(int employeeID,String password) {
		return this.loginRepo.login(employeeID, password);
	}
}
