package com.spring.service;

import java.util.List;

import com.spring.model.Department;

public interface DepartmentService {

	
	public void insertDepartment(Department dept);
	public List<Department> getAllDepartments();
	public String updateDepartment(Department dept);
	public void deleteDepartment(int deptId);
	Department getDeptById(int deptId);


}
