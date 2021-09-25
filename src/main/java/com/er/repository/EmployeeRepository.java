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

import com.er.model.Employee;
import com.er.utils.HibernateSessionFactory;

import io.javalin.http.Context;


public class EmployeeRepository {
	
	public Employee login(int employeeID, String password) {
		System.out.println("in the login repo");
		//Session s = null;
		//Transaction tx = null;
		Employee employee = null;
		EmployeeRepository empRepo= null;
		Context ctx= null;
		
		try {
			empRepo = new EmployeeRepository();
			employee = empRepo.findByID(employeeID);		
			if(employee.getEmployee_id() == employeeID && employee.getPassword().equals(password)) {
				if(employee.getEmp_pos().equals("Employee")) {
					System.out.print("Im an Employee");
					ctx.req.getSession();
					ctx.redirect("/employeeHome.html");
				}
				else if(employee.getEmp_pos().equals("Manager")) {
					System.out.print("Im a Manger");
					ctx.req.getSession();
					ctx.redirect("/managerHome.html");
				}
			}
			else
				System.out.print("wrong something bud");
		}catch(HibernateException e) {
			//tx.rollback();
			e.printStackTrace();
		}
		return employee;	
	}
	public List<Employee> findAll(){
		List<Employee> employees = new ArrayList<Employee>();
		Session s = null;
		Transaction tx = null;
		
		try {
			s=  HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			employees = s.createQuery("FROM Employee", Employee.class).getResultList();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
		return employees;
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
	public Employee findByName(String name) {
		Session s = null;
		Transaction tx = null;
		Employee employee = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			Root<Employee> root = cq.from(Employee.class);
			cq.select(root).where(cb.equal(root.get("name"), name));
			Query<Employee> query = s.createQuery(cq);
			employee = query.getSingleResult();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
		return employee;
	}
	public Employee findByID(int id) {
		Session s = null;
		Transaction tx = null;
		Employee employee = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			Root<Employee> root = cq.from(Employee.class);
			cq.select(root).where(cb.equal(root.get("employee_id"), id));
			Query<Employee> query = s.createQuery(cq);
			employee = query.getSingleResult();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}		
		return employee;
	}
}
