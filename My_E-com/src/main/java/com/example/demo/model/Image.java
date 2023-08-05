package com.example.demo.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "imagedata")
public class Image {
	@Id
	private int id;
	private String name;
	private String contentype;
	@Lob
	private byte[] data;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentype() {
		return contentype;
	}

	public void setContentype(String contentType) {
		this.contentype = contentType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
