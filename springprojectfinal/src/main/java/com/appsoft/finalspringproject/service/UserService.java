package com.appsoft.finalspringproject.service;

import com.appsoft.finalspringproject.model.User;

public interface UserService {

	void userSignup(User u);
	User userLogin(String un, String pass);
	
}
