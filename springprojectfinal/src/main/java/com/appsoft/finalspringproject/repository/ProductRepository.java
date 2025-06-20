package com.appsoft.finalspringproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.finalspringproject.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
}
