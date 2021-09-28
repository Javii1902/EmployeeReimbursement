package com.er.controllers;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.er.model.Employee;
import com.er.service.EmployeeService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(Javalin app){
		this.employeeService = new EmployeeService();
		app.routes(()-> {
			path("/login",() -> {
				post(login);
			});
			path("/employee/all",() -> {
				get(findAllEmployees);
			});
			path("/employee/name",() -> {
				get(employeeByName);
			});
			path("/employee/:employee_id",() -> {
				get(employeeByID);
			});
			path("/new",() -> {
				post(saveEmployee);
			});
		});
	}
	private Handler login = ctx->{

		int employeeid = Integer.parseInt(ctx.req.getParameter("employeeid"));
		String password= ctx.req.getParameter("password");
		Employee employee = this.employeeService.findByID(employeeid);
		
		if(employee.getEmployee_id() == employeeid && employee.getPassword().equals(password)) {
			if(employee.getEmp_pos().equals("Employee")) {
				ctx.req.getSession();
				HttpSession s = ctx.req.getSession(false);
				s.setAttribute("employee_id", employee.getEmployee_id());
				ctx.redirect("/employeeHome.html");
				System.out.println(s.getAttribute("employee_id"));
			}else if (employee.getEmp_pos().equals("Manager")) {
				ctx.req.getSession();
				HttpSession s = ctx.req.getSession(false);
				s.setAttribute("employee_id", employee.getEmployee_id());
				ctx.redirect("/managerHome.html");
			}
		}else {
			HttpSession session = ctx.req.getSession(false);
			if(session!=null)
				session.invalidate();
			ctx.redirect("/login.html");
		}
	};
	private Handler findAllEmployees = ctx -> {
		//HttpSession session = ctx.req.getSession(false);
		ctx.json(this.employeeService.findAll());
	};

	private Handler employeeByName = ctx -> {
		Employee employee = this.employeeService.findByName(ctx.req.getParameter("name"));
		ctx.json(employee);
	};

	private Handler saveEmployee = ctx -> {
		Employee employee = new Employee(1,
				ctx.req.getParameter("name"),
				ctx.req.getParameter("emp_pos"),
				ctx.req.getParameter("username"),
				ctx.req.getParameter("password"));
		this.employeeService.save(employee);
	};

	private Handler employeeByID = ctx ->{
		int id = Integer.parseInt(ctx.pathParam("employee_id"));
		Employee employee = this.employeeService.findByID(id);
		ctx.json(employee);
	};
}