package com.example.demo.model;

import java.util.LinkedList;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity
public class Cart {
	@Id
	private int id;
	private String Pname;
	private int Pprice;
	private String size;
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LinkedList<Cart> addproducts(Cart obj, LinkedList<Cart> list) {

		list.add(obj);

		return list;
	}

	public LinkedList<Cart> setquantitybyId(int id, int q, LinkedList<Cart> list) {
		for (Cart a : list) {
			if (a.id == id) {
				a.quantity = q;
				break;
			}
		}
		return null;
	}

	public LinkedList<Cart> removeElementbyId(int id, LinkedList<Cart> list) {
		for (Cart a : list) {
			if (a.id == id) {
				int index = list.indexOf(a);
				list.remove(index);
				break;
			}
		}
		return null;
	}

	public String check(int id, String size, LinkedList<Cart> l) {
		String c = "false";
		for (Cart a : l) {
			if ((a.id == id) && (a.size.equals(size))) {
				c = "true";
				System.out.print("check");
				break;
			}
		}
		return c;

	}

}
