package com.mercury.demand.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="STOCKS")
public class Stocks implements Serializable{

	private static final long serialVersionUID = -8764591500290828720L;
	private String sid;
	private Set<Trader> traders;
	
	

	public Stocks() {
		traders = new HashSet<Trader>();
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
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="stocks")
	public Set<Trader> getTraders() {
		return traders;
	}
	public void setTraders(Set<Trader> traders) {
		this.traders = traders;
	}
	
	public void addTrader(Trader trader) {
		traders.add(trader);
	}
	public void removeTrader(Trader trader) {
		traders.remove(trader);
	}
	
	
	
}
