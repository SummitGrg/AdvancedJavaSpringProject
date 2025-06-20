package com.appsoft.finalspringproject.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.finalspringproject.model.User;
import com.appsoft.finalspringproject.repository.UserRepository;
import com.appsoft.finalspringproject.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void userSignup(User u) {
		
		userRepository.save(u);
		
	}

	@Override
	public User userLogin(String un, String pass) {
		
		return userRepository.findByUsernameAndPassword(un, pass);
	}

}