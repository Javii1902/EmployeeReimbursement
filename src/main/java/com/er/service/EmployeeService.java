package com.er.service;

import java.util.List;

import com.er.model.Employee;
import com.er.repository.EmployeeRepository;

public class EmployeeService {
	private EmployeeRepository employeeRepository;
	
	public EmployeeService() {
		this.employeeRepository = new EmployeeRepository();
	}
	public List<Employee> findAllEmployees(){
		List<Employee> allEmployees = this.employeeRepository.findAll();
		return null;
	}
	public List<Employee> findAll(){
		return this.employeeRepository.findAll();
	}
	public void save(Employee employee) {
		this.employeeRepository.save(employee);
	}
}
