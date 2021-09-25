package com.er.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.er.model.Employee;

import io.javalin.http.Context;


public class LoginRepository {
	public Employee login(int employeeID, String password) {
		Session s = null;
		Transaction tx = null;
		Employee employee = null;
		EmployeeRepository empRepo= null;
		Context ctx = null;
		
		try {
			empRepo = new EmployeeRepository();
			employee = empRepo.findByID(employeeID);
			
			if(employee.getEmployee_id() == employeeID && employee.getPassword() == password) {
				if(employee.getEmp_pos() == "Employee") {
					ctx.req.getSession();
					ctx.redirect("/employeeHome.html");
				}
				else if(employee.getEmp_pos() == "Manager") {
					ctx.req.getSession();
					ctx.redirect("/managerHome.html");
				}
			}
		}catch(HibernateException e) {
			//tx.rollback();
			e.printStackTrace();
		}
		return employee;
				
	}
}
