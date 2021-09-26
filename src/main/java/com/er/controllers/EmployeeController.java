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
			path("/name/:name",() -> {
				get(employeeByName);
			});
			path("/employee/:id",() -> {
				get(employeeByID);
			});
			path("/new",() -> {
				post(saveEmployee);
			});
		});
	}

//	private Handler login = ctx ->{
//		int employeeid = Integer.parseInt(ctx.req.getParameter("employeeid"));
//		String password = ctx.req.getParameter("password");
//		
//		if(this.employeeService.validate(employeeid, password) == true) {
//			System.out.println("true");
//		};
//		
//	};
	private Handler login = ctx->{
		
		int employeeid = Integer.parseInt(ctx.req.getParameter("employeeid"));
		String password= ctx.req.getParameter("password");
		Employee employee = this.employeeService.findByID(employeeid);

		if(employee.getEmployee_id() == employeeid && employee.getPassword().equals(password)) {
			if(employee.getEmp_pos().equals("Employee")) {
				ctx.req.getSession();
				ctx.redirect("/employeeHome.html");
			}else if (employee.getEmp_pos().equals("Manager")) {
				ctx.req.getSession();
				ctx.redirect("/ManagerHome.html");
			}
		}else
			ctx.redirect("/login.html");
	};
	private Handler findAllEmployees = ctx -> {
		//HttpSession session = ctx.req.getSession(false);
		ctx.json(this.employeeService.findAll());
	};
	
	private Handler employeeByName = ctx -> {
		Employee employee = this.employeeService.findByName(ctx.pathParam("name"));
		ctx.json(employee);
	};

	private Handler saveEmployee = ctx -> {
		Employee employee = new Employee(1,
				ctx.req.getParameter("name"),
				ctx.req.getParameter("emp_pos"),
				ctx.req.getParameter("username"),
				ctx.req.getParameter("password"));
		this.employeeService.save(employee);
		ctx.redirect("/home.html");
	};
	
	private Handler employeeByID = ctx ->{
		int id = Integer.parseInt(ctx.pathParam("id"));
		Employee employee = this.employeeService.findByID(id);
		ctx.json(employee);
	};
}