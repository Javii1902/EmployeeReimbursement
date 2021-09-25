package com.er.service;

import java.util.List;

import com.er.model.Reimbursement;
import com.er.repository.ReimbursementRepository;

public class ReimbursementService {
private ReimbursementRepository reimbursementRepository;
	
	public ReimbursementService() {
		this.reimbursementRepository = new ReimbursementRepository();
	}
	public List<Reimbursement> findAll(){
		return this.reimbursementRepository.findAll();
	}
	public void save(Reimbursement reimbursement) {
		this.reimbursementRepository.save(reimbursement);
	}
	public Reimbursement findByID(int id) {
		return this.reimbursementRepository.findByID(id);
	}	
}
