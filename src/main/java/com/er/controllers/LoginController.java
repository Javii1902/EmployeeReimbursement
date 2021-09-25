package com.er.controllers;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import com.er.service.LoginService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LoginController {
	private LoginService loginService;

	public LoginController(Javalin app) {
		this.loginService = new LoginService();
		app.routes(()-> {
			//path("/login/employeeid/:employeeid/password/:password",() -> {
			path("/login/employeeid/",() -> {
				post(login);
			});
		});
	}
	private Handler login = ctx ->{
		HttpSession session = ctx.req.getSession(false);
		if(session !=null) {
			this.loginService.login(Integer.parseInt(ctx.pathParam("employeeid")),ctx.pathParam("password"));
		}

	};
}
