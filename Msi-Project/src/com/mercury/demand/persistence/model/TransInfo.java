package com.mercury.demand.persistence.model;

import java.util.Date;

public class TransInfo {
	
	private String sid;
	private Date t_time;
	private double price;
	private int quantity;
	private String t_type;
	private String t_status;
	
	public TransInfo() {}
	public TransInfo(String sid, Date t_time, double price, int quantity, String t_type, String t_status) {
		this.sid = sid;
		this.t_time = t_time;
		this.price = price;
		this.quantity = quantity;
		this.t_type = t_type;
		this.t_status = t_status;
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Date getT_time() {
		return t_time;
	}
	public void setT_time(Date t_time) {
		this.t_time = t_time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getT_type() {
		return t_type;
	}
	public void setT_type(String t_type) {
		this.t_type = t_type;
	}
	public String getT_status() {
		return t_status;
	}
	public void setT_status(String t_status) {
		this.t_status = t_status;
	}
	
	
}
