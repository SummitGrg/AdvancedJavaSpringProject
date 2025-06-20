package com.appsoft.finalspringproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.finalspringproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(String un, String pass);
	
	
}
