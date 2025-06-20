package com.appsoft.finalspringproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.finalspringproject.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
