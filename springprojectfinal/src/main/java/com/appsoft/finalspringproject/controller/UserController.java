package com.appsoft.finalspringproject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.finalspringproject.model.User;
import com.appsoft.finalspringproject.repository.ProductRepository;
import com.appsoft.finalspringproject.service.UserService;
import com.appsoft.finalspringproject.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/")
	public String getCustomerHome(Model model) {
		
		model.addAttribute("productList", productRepository.findAll());
		return "CustomerHome";
	}
	
	@GetMapping({"/login"})
	public String getLogin() {
		
		return "LoginForm";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute User u, Model model, HttpSession session,@RequestParam("g-recaptcha-response") String greCaptCode) throws IOException {
		
		if(VerifyRecaptcha.verify(greCaptCode)) {
		
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		User usr = userService.userLogin(u.getUsername(), u.getPassword());
		
		if(usr != null) {
			
			session.setAttribute("activeuser", usr);
			session.setMaxInactiveInterval(300);
			
			if(usr.getRole().equals("CUSTOMER")) {
				
				model.addAttribute("productList", productRepository.findAll());
				return "CustomerHome";
			}
			return "Home"; 
		}else {
			
			model.addAttribute("message","user not found!");
			return "LoginForm";
			
		}
		
	}
		model.addAttribute("message","You are a robot.");
		return "LoginForm";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		
		return "SignupForm";
	}
	
	@PostMapping("signup")
	public String postSignup(@ModelAttribute User u) {
		
		//check if user already exist in db
		
		
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		userService.userSignup(u);
		return "LoginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		
		return "LoginForm";
	}
	
	@GetMapping("/profile")
	public String getProfile() {
		
		return "Profile";
	}
	
	@GetMapping("/home")
	public String getHome() {
		
		return "Home";
	}
}