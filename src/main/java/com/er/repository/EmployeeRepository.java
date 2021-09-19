package com.er.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.er.model.Employee;
import com.er.utils.HibernateSessionFactory;


public class EmployeeRepository {
	public List<Employee> findAll(){
		return null;
	}
	
	public void save(Employee employee) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.save(employee);
			tx.commit();
		}catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
