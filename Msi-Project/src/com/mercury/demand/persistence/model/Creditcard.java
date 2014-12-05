package com.mercury.demand.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CREDITCARD")
public class Creditcard implements Serializable{
	
	private static final long serialVersionUID = -5686131026301182945L;
	private int cid;
	private int lid;
	private String card_holder;
	private byte[] card_number;
	private int expire_month;
	private int expire_year;
	private int code;
	
	public Creditcard() {}
	public Creditcard(String card_holder, byte[] card_number, int expire_month, int expire_year, int code) {
		this.card_holder = card_holder;
		this.card_number = card_number;
		this.expire_month = expire_month;
		this.expire_year = expire_year;
		this.code = code;
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Column(name="CID")
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	@Column(name="CARD_HOLDER", nullable=false)
	public String getCard_holder() {
		return card_holder;
	}
	public void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
	}
	
	@Column(name="CARD_NUMBER", nullable=false)
	public byte[] getCard_number() {
		return card_number;
	}
	public void setCard_number(byte[] card_number) {
		this.card_number = card_number;
	}
	
	@Column(name="EXPIRE_MONTH", nullable=false)
	public int getExpire_month() {
		return expire_month;
	}
	public void setExpire_month(int expire_month) {
		this.expire_month = expire_month;
	}
	
	@Column(name="EXPIRE_YEAR", nullable=false)
	public int getExpire_year() {
		return expire_year;
	}
	public void setExpire_year(int expire_year) {
		this.expire_year = expire_year;
	}
	@Column(name="CODE", nullable=false)
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	@JoinColumn(name="LID")
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	
}
