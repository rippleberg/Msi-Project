package com.mercury.demand.persistence.model;

public class Creditcard {
	private int cid;
	private String card_holder;
	private String card_number;
	private String expire_date;
	private int code;
	
	public Creditcard() {}
	public Creditcard(String card_holder, String card_number, String expire_date, int code) {
		this.card_holder = card_holder;
		this.card_number = card_number;
		this.expire_date = expire_date;
		this.code = code;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCard_holder() {
		return card_holder;
	}
	public void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
