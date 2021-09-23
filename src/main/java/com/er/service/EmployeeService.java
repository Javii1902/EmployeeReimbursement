package com.er.service;

import java.util.List;

import com.er.model.Employee;
import com.er.repository.EmployeeRepository;

public class EmployeeService {
	private EmployeeRepository employeeRepository;
	
	public EmployeeService() {
		this.employeeRepository = new EmployeeRepository();
	}
	public List<Employee> findAll(){
		return this.employeeRepository.findAll();
	}
	public void save(Employee employee) {
		this.employeeRepository.save(employee);
	}
	public Employee findByName(String name) {
		return this.employeeRepository.findByName(name);
	}
	public Employee findByID(int id) {
		return this.employeeRepository.findByID(id);
	}
}
