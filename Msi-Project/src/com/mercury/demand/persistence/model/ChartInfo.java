package com.mercury.demand.persistence.model;

public class ChartInfo {
	
	private String sid;
	private double price;
	private int quantity;
	private int totalBought;
	private int totalSold;
	
	public ChartInfo() {}
	public ChartInfo(String sid, double price, int quantity, int totalBought, int totalSold) {
		this.sid = sid;
		this.price = price;
		this.quantity = quantity;
		this.totalBought = totalBought;
		this.totalSold = totalSold;
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
	public int getTotalBought() {
		return totalBought;
	}
	public void setTotalBought(int totalBought) {
		this.totalBought = totalBought;
	}
	public int getTotalSold() {
		return totalSold;
	}
	public void setTotalSold(int totalSold) {
		this.totalSold = totalSold;
	}
	
	
}
