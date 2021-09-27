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
			path("/reimbursement/all",() -> {
				get(findAllReimbursements);
			});
			path("/reimbursement/:id",() -> {
				get(reimbursementByID);
			});
			path("/reimbursement/employeeid/reimbursements", () ->{
				get(reimbursementByEmployee);
			});
			path("/reimbursement/new",() -> {
				post(saveReimbursement);
			});
			path("/reimbursement/approve", () -> {
				post(approve);
			});
			path("/reimbursement/deny", () -> {
				post(deny);
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
		HttpSession session = ctx.req.getSession(false);

		if(session != null) {
			Reimbursement reimbursement =new Reimbursement(1,
					Double.parseDouble(ctx.req.getParameter("amount")),
					"Pending",
					ctx.req.getParameter("description"),
					Integer.parseInt(ctx.req.getParameter("employee_id"))
					);
			this.reimbursementService.save(reimbursement);
		}else
			ctx.res.getWriter().write("you do not have a session.");
	};
	
	private Handler reimbursementByID = ctx ->{
		int id = Integer.parseInt(ctx.pathParam("id"));
		Reimbursement reimbursement = this.reimbursementService.findByID(id);
		ctx.json(reimbursement);
	};
	
	private Handler approve = ctx->{
		HttpSession session = ctx.req.getSession(false);

		if(session != null)
			this.reimbursementService.approve(Integer.parseInt(ctx.req.getParameter("id")));
		else
			ctx.res.getWriter().write("you do not have a session.");
	};
	private Handler deny = ctx->{
		HttpSession session = ctx.req.getSession(false);

		if(session != null)
			this.reimbursementService.deny(Integer.parseInt(ctx.req.getParameter("id")));
		else
			ctx.res.getWriter().write("you do not have a session.");
	};
	private Handler reimbursementByEmployee = ctx -> {
		HttpSession s = ctx.req.getSession(false);
		if(s != null) {
			int id = Integer.parseInt(ctx.req.getParameter("employeeid"));
			ctx.json(this.reimbursementService.reimbursementByEmployee(id));
		}else {
			ctx.res.getWriter().write("you do not have a session.");
		}
	};
	
	
	
}
