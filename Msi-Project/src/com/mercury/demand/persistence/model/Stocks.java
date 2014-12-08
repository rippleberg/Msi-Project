package com.mercury.demand.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="STOCKS")
public class Stocks implements Serializable{
	private static final long serialVersionUID = -8764591500290828720L;
	private String sid;
	
	

	public Stocks() {
	}
	public Stocks(String sid){
		this();
		this.sid = sid;
	}
	
	@Id
	@Column(name="SID")
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
	
}
