package com.example.demo.imagedao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Image;

@Component
public interface imagedao extends JpaRepository<Image, Integer> {

}
