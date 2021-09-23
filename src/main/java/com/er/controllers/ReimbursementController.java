package com.er.controllers;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import com.er.model.Reimbursement;
import com.er.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController {
	private ReimbursementService reimbursementService;

	public ReimbursementController(Javalin app){
		this.reimbursementService = new ReimbursementService();
		app.routes(()-> {
			path("/all",() -> {
				get(findAllReimbursements);
			});
			path("/Reimbursement/:id",() -> {
				get(reimbursementByID);
			});
			path("/new",() -> {
				post(saveReimbursement);
			});
		});
	}

	private Handler findAllReimbursements = ctx -> {
		HttpSession session = ctx.req.getSession(false);

		if(session != null)
			ctx.json(this.reimbursementService.findAll());
		else
			ctx.res.getWriter().write("you do not have a session.");
	};

	private Handler saveReimbursement = ctx -> {
		Reimbursement reimbursement =new Reimbursement(1,
				Double.parseDouble(ctx.req.getParameter("amount")),
				ctx.req.getParameter("status"),
				ctx.req.getParameter("description"),
				Integer.parseInt(ctx.req.getParameter("employee_id"))
				);
		this.reimbursementService.save(reimbursement);
		//ctx.redirect("/home.html");
	};
	
	private Handler reimbursementByID = ctx ->{
		int id = Integer.parseInt(ctx.pathParam("id"));
		Reimbursement employee = this.reimbursementService.findByID(id);
		ctx.json(employee);
	};
}
