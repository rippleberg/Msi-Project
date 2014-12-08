package com.mercury.demand.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	private static final long serialVersionUID = -670738724585080943L;
	private int lid;
	private String first_name;
	private String last_name;
	private String phone;
	private String email;
	private String address;
	private String home_state;
	private String city;
	private String zipcode;
	private double balance;
	private String active;
	private Login login;
	private Set<Creditcard> cards;
	private Set<Trans> trans;
	private Set<TraderStock> stocks;
	
	public Trader() {
		cards = new HashSet<Creditcard>();
		trans = new HashSet<Trans>();
		stocks = new HashSet<TraderStock>();
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
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
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="ZIPCODE")
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Column(name="BALANCE")
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Column(name="ACTIVE")
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="lid")
	@Cascade(CascadeType.SAVE_UPDATE)
	public Set<Creditcard> getCards() {
		return cards;
	}
	public void setCards(Set<Creditcard> cards) {
		this.cards = cards;
	}
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="lid")
	@Cascade(CascadeType.SAVE_UPDATE)
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
	
	@OneToOne(mappedBy="trader")
	@Cascade(CascadeType.ALL)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="lid")
	@Cascade(CascadeType.SAVE_UPDATE)
	public Set<TraderStock> getStocks() {
		return stocks;
	}
	public void setStocks(Set<TraderStock> stocks) {
		this.stocks = stocks;
	}
	
	public void addStock(TraderStock stock) {
		stocks.add(stock);
		stock.setLid(lid);
	}
	
	public void removeStock(TraderStock stock) {
		stocks.remove(stock);
	}
	
	public TraderStock getTraderStock(String sid) {
		for(TraderStock stock:stocks) {
			if(stock.getSid().equals(sid)) {
				return stock;
			}
		}
		return null;
	}
	
}
