package com.example.demo.Edao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Userdetails;

@Component
public interface Edao extends CrudRepository<Userdetails, String> {
}
