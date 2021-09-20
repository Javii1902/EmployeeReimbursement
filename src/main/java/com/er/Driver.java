package com.er;

import java.sql.Connection;

import javax.servlet.http.HttpSession;

import com.er.controllers.EmployeeController;
import com.er.utils.HibernateSessionFactory;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;


public class Driver {
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		app.post("/login", ctx -> {
			ctx.req.getSession();
		});
		app.get("/logout",ctx -> {
			HttpSession session = ctx.req.getSession(false);
			if(session!=null)
				session.invalidate();
		});
		app.after(ctx -> {
			ctx.res.addHeader("Access-Control-Allow-Origin", "null");
		});
		
		app.config.addStaticFiles("/static", Location.CLASSPATH);
		
		EmployeeController employeeController = new EmployeeController(app);
	}
}
