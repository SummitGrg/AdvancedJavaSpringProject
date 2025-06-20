package com.appsoft.finalspringproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.finalspringproject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	//Object findById(int id);

}
