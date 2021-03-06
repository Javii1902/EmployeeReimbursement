package com.er.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.er.model.Reimbursement;
import com.er.utils.HibernateSessionFactory;

public class ReimbursementRepository {
	public List<Reimbursement> findAll(){
		List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
		Session s = null;
		Transaction tx = null;
		
		try {
			s=  HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			reimbursement = s.createQuery("FROM Reimbursement", Reimbursement.class).getResultList();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
		return reimbursement;
	}
	
	public void save(Reimbursement reimbursement) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.save(reimbursement);
			tx.commit();
		}catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	public Reimbursement findByID(int id) {
		Session s = null;
		Transaction tx = null;
		Reimbursement reimbursement= null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = cq.from(Reimbursement.class);
			cq.select(root).where(cb.equal(root.get("reimbursement_id"), id));
			Query<Reimbursement> query = s.createQuery(cq);
			reimbursement = query.getSingleResult();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}		
		return reimbursement;
	}
	
	public void approve(int id) {
		Session s = null;
		Transaction tx = null;
		Reimbursement reim = null;
		ReimbursementRepository reimRepo = null; 
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = cq.from(Reimbursement.class);
			cq.select(root).where(cb.equal(root.get("reimbursement_id"), id));
			Query<Reimbursement> query = s.createQuery(cq);
			reim = query.getSingleResult();
			
			reim.setStatus("Approved");
			s.saveOrUpdate(reim);
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
	}
	public void denied(int id) {
		Session s = null;
		Transaction tx = null;
		Reimbursement reim = null;
		ReimbursementRepository reimRepo = null; 
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = cq.from(Reimbursement.class);
			cq.select(root).where(cb.equal(root.get("reimbursement_id"), id));
			Query<Reimbursement> query = s.createQuery(cq);
			reim = query.getSingleResult();
			
			reim.setStatus("Denied");
			s.saveOrUpdate(reim);
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
	}
	public List<Reimbursement> reimbursementbyEmployee(int id) {
		Session s = null;
		Transaction tx = null;
		List<Reimbursement> reimbursement= null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = cq.from(Reimbursement.class);
			cq.select(root).where(cb.equal(root.get("employee_id"), id));
			Query<Reimbursement> query = s.createQuery(cq);
			reimbursement = query.getResultList();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}		
		return reimbursement;
	}
}








































