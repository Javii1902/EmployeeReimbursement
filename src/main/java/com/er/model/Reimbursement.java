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

@Table(name="reimbursements")
public class Reimbursement {
	@Id
	@Column
	@GeneratedValue(generator = "reimbursement_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "reimbursement_id_seq", sequenceName = "reimbursement_id_seq")
	private int reimbursement_id;
	@Column
	private double amount;
	@Column
	private int employee_id;
	@Column
	private String status;
	@Column
	private String description;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int reimbursement_id, double amount, int employee_id, String status, String description) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.amount = amount;
		this.employee_id = employee_id;
		this.status = status;
		this.description = description;
	}
	public int getReimbursement_id() {
		return reimbursement_id;
	}
	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, description, employee_id, reimbursement_id, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(description, other.description) && employee_id == other.employee_id
				&& reimbursement_id == other.reimbursement_id && Objects.equals(status, other.status);
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbursement_id=" + reimbursement_id + ", amount=" + amount + ", employee_id="
				+ employee_id + ", status=" + status + ", description=" + description + "]";
	}
	

}
