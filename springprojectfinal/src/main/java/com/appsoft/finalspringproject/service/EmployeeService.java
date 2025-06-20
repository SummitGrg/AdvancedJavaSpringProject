package com.appsoft.finalspringproject.service;

import java.util.List;

import com.appsoft.finalspringproject.model.Employee;

public interface EmployeeService {

	void addEmp(Employee emp);
	void deleteEmp(int id);
	void updateEmp(Employee emp);
	Employee getEmpById(int id);
	List<Employee>getAllEmp();
	
	
	
}
