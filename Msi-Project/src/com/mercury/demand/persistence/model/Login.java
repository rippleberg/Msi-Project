package com.mercury.demand.persistence.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="LOGIN")
public class Login implements Serializable{
	
	private static final long serialVersionUID = 2201370792533438115L;
	private int lid;
	private String username;
	private String password;
	private String authority;
	private Trader trader;
	
	
	public Login() {}
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public Login(String username, String password, String authority) {
		this.username = username;
		this.password = password;
		this.authority = authority;
	}
	@Id
	@Column(name="LID", unique=true, nullable=false)
	@GeneratedValue(generator="gen")
	@GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="trader"))
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	
	@Column(name="USERNAME", unique=true, nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="PASSWORD", nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="AUTHORITY", nullable=false)
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@OneToOne
	@PrimaryKeyJoinColumn
	public Trader getTrader() {
		return trader;
	}
	public void setTrader(Trader trader) {
		this.trader = trader;
	}
}
