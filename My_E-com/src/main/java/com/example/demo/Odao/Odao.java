package com.example.demo.Odao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Orderlist;

@Component
public interface Odao extends JpaRepository<Orderlist, String> {

}
