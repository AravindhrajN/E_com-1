package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderlist")
public class Orderlist {
	@Id
	String orderid;
	String refid;

	public Orderlist() {

	}

	public Orderlist(String orderid, String refid) {
		this.orderid = orderid;
		this.refid = refid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

}
