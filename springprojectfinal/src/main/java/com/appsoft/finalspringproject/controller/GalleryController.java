package com.appsoft.finalspringproject.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.appsoft.finalspringproject.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class GalleryController {
	
	@Autowired
	private ProductRepository pr;

	@GetMapping("/gallery")
	public String getGallery(Model model, HttpSession session) {
		
		if(session.getAttribute("activeuser")==null) {
			
			return "LoginForm";
		}
		
		String[] imgList = new File("src/main/resources/static/images").list();
		model.addAttribute("imgList",imgList);
		return "GalleryForm";
	}
	
	@GetMapping("/productGallery")
	public String getProducts(Model model) {
		
		model.addAttribute("productList", pr.findAll());
		return "ProductGallery";
	}
}