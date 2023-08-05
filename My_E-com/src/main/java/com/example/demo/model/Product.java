package com.example.demo.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "products")
public class Product {
	@Id
	private int id;
	private String Pname;
	private int Pprice;
	private String pdes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product() {

	}

	public Product(int id, String Pname, int Pprice, String pdes) {
		this.id = id;
		this.Pname = Pname;
		this.Pprice = Pprice;
		this.pdes = pdes;
	}

	public String getPname() {
		return Pname;
	}

	public void setPname(String pname) {
		Pname = pname;
	}

	public int getPprice() {
		return Pprice;
	}

	public void setPprice(int pprice) {
		Pprice = pprice;
	}

	public String getPdes() {
		return pdes;
	}

	public void setPdes(String pdes) {
		this.pdes = pdes;
	}

}
