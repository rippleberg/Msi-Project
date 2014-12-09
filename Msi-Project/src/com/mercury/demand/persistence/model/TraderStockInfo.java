package com.mercury.demand.persistence.model;

public class TraderStockInfo {
	
	private String sid;
	private double price;
	private int quantity;
	
	public TraderStockInfo() {}
	public TraderStockInfo(String sid, double price, int quantity) {
		this.sid = sid;
		this.price = price;
		this.quantity = quantity;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
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
	
	
}
