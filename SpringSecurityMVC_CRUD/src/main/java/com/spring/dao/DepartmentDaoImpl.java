package com.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Department;

@Repository("departmentDao")
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void insertDepartment(Department dept) {

		sessionFactory.getCurrentSession().saveOrUpdate(dept);
		System.out.println("Datas inserted");


	}

	
	public List<Department> getAllDepartments() {
		
		return sessionFactory.getCurrentSession().createQuery("from Department").list();

	 }

	public String updateDepartment(Department dept) {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(dept);
		String rs = "Record Updated Successfully";
		session.getTransaction().commit();
		session.close();
		return rs;
	}

	public void deleteDepartment(int deptId) {

		Department department= (Department) sessionFactory.getCurrentSession().load(Department.class, deptId);

		  if (department != null) {

		  this.sessionFactory.getCurrentSession().delete(department);

		  }
	}

	public Department getDeptById(int deptId) {
		Department department= (Department) sessionFactory.getCurrentSession().load(Department.class, deptId);

		 return department;
	}
}
