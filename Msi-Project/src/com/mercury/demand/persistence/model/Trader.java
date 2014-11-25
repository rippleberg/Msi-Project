package com.mercury.demand.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="TRADER")
public class Trader implements Serializable{
	
	private static final long serialVersionUID = 5138687906794194145L;
	private int lid;
	private String first_name;
	private String last_name;
	private int phone;
	private String email;
	private String address;
	private String home_state;
	private int zipcode;
	private int balance;
	private String active;
	
	private Login login;
	
	@OneToMany(mappedBy="lid")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<Creditcard> cards;
	
	@OneToMany(mappedBy="lid")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<Trans> trans;
	
	@OneToOne(mappedBy="trader")
	@Cascade(CascadeType.ALL)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public Trader() {
		cards = new HashSet<Creditcard>();
		trans = new HashSet<Trans>();
	}
	public Trader(String first_name, String last_name, String email) {
		this();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Column(name="LID")
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	@Column(name="FIRST_NAME", nullable=false)
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	@Column(name="LAST_NAME", nullable=false)
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@Column(name="PHONE")
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	@Column(name="EMAIL", unique=true, nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="HOME_STATE")
	public String getHome_state() {
		return home_state;
	}
	public void setHome_state(String home_state) {
		this.home_state = home_state;
	}
	
	@Column(name="ZIPCODE")
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	@Column(name="BALANCE")
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Column(name="ACTIVE")
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Set<Creditcard> getCards() {
		return cards;
	}
	public void setCards(Set<Creditcard> cards) {
		this.cards = cards;
	}
	public Set<Trans> getTrans() {
		return trans;
	}
	public void setTrans(Set<Trans> trans) {
		this.trans = trans;
	}
	public void addCreditcard(Creditcard card) {
		cards.add(card);
		card.setLid(lid);
	}
	public void removeCreditcard(Creditcard card) {
		cards.remove(card);
	}
	
	public void addTrans(Trans tran) {
		trans.add(tran);
		tran.setLid(lid);
	}
	public void removeTrans(Trans tran) {
		trans.remove(tran);
	}
	
	
}
