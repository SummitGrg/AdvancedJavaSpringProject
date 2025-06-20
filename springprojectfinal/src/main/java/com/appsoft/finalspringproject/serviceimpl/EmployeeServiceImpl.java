package com.appsoft.finalspringproject.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.finalspringproject.model.Employee;
import com.appsoft.finalspringproject.repository.EmployeeRepository;
import com.appsoft.finalspringproject.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void addEmp(Employee emp) {
		
		employeeRepository.save(emp);
	}

	@Override
	public void deleteEmp(int id) {
		
		employeeRepository.deleteById(id);
	}

	@Override
	public void updateEmp(Employee emp) {
		
		employeeRepository.save(emp);
		
	}

	@Override
	public Employee getEmpById(int id) {
		
		return employeeRepository.findById(id).get();
		//return employeeRepository.findById(id).get();
	}

	@Override
	public List<Employee> getAllEmp() {
		
		return employeeRepository.findAll();
	}
}