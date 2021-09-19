package com.er.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity

@Table(name = "employees")

public class Employee {
	@Id
	@Column(name="employee_id")
	@GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "employee_id_seq", sequenceName = "employee_id_seq")
	private int employee_id;
	@Column
	private String name;
	@Column
	private String emp_pos;
	@Column
	private String username;
	@Column 
	private String password;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int employee_id, String name, String emp_pos, String username, String password) {
		super();
		this.employee_id = employee_id;
		this.name = name;
		this.emp_pos = emp_pos;
		this.username = username;
		this.password = password;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmp_pos() {
		return emp_pos;
	}

	public void setEmp_pos(String emp_pos) {
		this.emp_pos = emp_pos;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(emp_pos, employee_id, name, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(emp_pos, other.emp_pos) && employee_id == other.employee_id
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", name=" + name + ", emp_pos=" + emp_pos + ", username="
				+ username + ", password=" + password + "]";
	}
	
	
}
