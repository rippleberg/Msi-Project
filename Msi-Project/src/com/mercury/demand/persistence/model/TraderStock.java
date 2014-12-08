package com.mercury.demand.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="TRADER_STOCKS")
public class TraderStock {
	private int lid;
	private String sid;
	private double price;
	private int quantity;
	
	public TraderStock() {}
	public TraderStock(String sid) {
		this.sid = sid;
	}
	public TraderStock(String sid, double price, int quantity) {
		this.sid = sid;
		this.price = price;
		this.quantity = quantity;
	}
	
	@JoinColumn(name="LID")
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	
	@Id
	@Column(name="SID")
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	@Column(name="PRICE")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name="QUANTITY")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public int hashCode() {
		if(sid==null) {
			return 0;
		}else {
			return sid.hashCode();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TraderStock)) {
			return false;
		}
		TraderStock ts = (TraderStock) obj;
		return this.sid==null?false:this.sid.equals(ts.getSid());
	}
}
