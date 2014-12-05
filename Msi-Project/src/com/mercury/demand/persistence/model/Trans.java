package com.mercury.demand.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="TRANS")
public class Trans implements Serializable{
	
	private static final long serialVersionUID = -796868397858674727L;
	private int tid;
	private int lid;
	private String sid;
	private Date t_time;
	private double price;
	private int quantity;
	private String t_type;
	private String t_status;
	
	public Trans() {}
	public Trans(String sid, Date t_time, double price, int quantity, String t_type, String t_status) {
		this.sid = sid;
		this.t_time = t_time;
		this.price = price;
		this.quantity = quantity;
		this.t_type = t_type;
		this.t_status = t_status;
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Column(name="TID")
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	@Column(name="T_TIME", nullable=false)
	public Date getT_time() {
		return t_time;
	}
	public void setT_time(Date t_time) {
		this.t_time = t_time;
	}
	
	@Column(name="PRICE", nullable=false)
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name="QUANTITY", nullable=false)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="T_TYPE", nullable=false)
	public String getT_type() {
		return t_type;
	}
	public void setT_type(String t_type) {
		this.t_type = t_type;
	}
	
	@Column(name="T_STATUS", nullable=false)
	public String getT_status() {
		return t_status;
	}
	public void setT_status(String t_status) {
		this.t_status = t_status;
	}
	
	@JoinColumn(name="LID")
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	
	@Column(name="SID")
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}

}
