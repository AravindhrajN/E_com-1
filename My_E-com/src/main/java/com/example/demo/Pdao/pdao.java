package com.example.demo.Pdao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Product;

@Component
public interface pdao extends JpaRepository<Product, Integer> {

}
