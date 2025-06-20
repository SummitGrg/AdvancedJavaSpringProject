package com.appsoft.finalspringproject.service;

import java.util.List;

import com.appsoft.finalspringproject.model.Department;

public interface DepartmentService {

	void addDept(Department dept);
	void deleteDept(int id);
	void updateDept(Department dept);
	Department getDeptById(int id);
	List<Department> getAllDepts();
	
	
}
