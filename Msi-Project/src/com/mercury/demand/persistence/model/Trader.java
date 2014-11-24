package com.mercury.demand.persistence.model;

//import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trader")
public class Trader implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2748948831285204760L;
	private int lid;
	private String firstName;
	private String lastName;
	private String address;
	private String state;
	private int zipcode;
	private String country;
	private String phone;
	private String email;
	private int balance;
	private char active;
	private Login login;
	private boolean enabled;
	// Constructors
	/** default constructor */
    public Trader() {}

    // Property accessors
   
	public Trader(String firstName, String lastName, String address,String state, 
		int zipcode, String country, String phone, String email,int balance,char active){
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.phone = phone;
		this.email = email;
		this.active=active;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}


	
}













    

