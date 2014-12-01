package com.mercury.demand.persistence.model;

public class Stock {
	private String sid;
	private String name;
	private double price;
	private double change;
	private double percentChange;
	private double high;
	private double low;
	private int volumn;
	
	public Stock() {}
	public Stock(String sid){
		this.sid = sid;
	}
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(double percentChange) {
		this.percentChange = percentChange;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public int getVolumn() {
		return volumn;
	}

	public void setVolumn(int volumn) {
		this.volumn = volumn;
	}

}
