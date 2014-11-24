package com.mercury.demand.persistence.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="creditcard")
public class Creditcard implements java.io.Serializable {
	
	private static final long serialVersionUID = 2748948831285204760L;
	private int cid;
	private int lid;
	private int card_name;
	private String expire_date;
	private int code;
	private String card_holder;
	
	
	 @Id
	 @Column(name="cid", nullable = false)
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getCard_name() {
		return card_name;
	}
	public void setCard_name(int card_name) {
		this.card_name = card_name;
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
	public String getCard_holder() {
		return card_holder;
	}
	public void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
	}
	
	
	

}