package com.mercury.demand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.*;

@Service
@Transactional
public class ConfigService {

	@Autowired
	@Qualifier("loginDao")
	Dao<Login, Integer> loginDao;
	
	public Trader getCurrentTrader(String username){
		Login login = loginDao.findBy("username", username);
		return login.getTrader();
	}
	
	public void config(String username, String password, String firstname, String lastname,
			String phone, String email, String address, String city, String state, String zipcode){
		Login login = loginDao.findBy("username", username);
		if(login == null) return;
		Trader trader = login.getTrader();
		if(password != null) login.setPassword(password);
		if(firstname != null) trader.setFirst_name(firstname);
		if(lastname != null) trader.setLast_name(firstname);
		if(phone != null) trader.setPhone(phone);
		if(email != null) trader.setEmail(email);
		if(address != null) trader.setAddress(address);
		if(city != null) trader.setCity(city);
		if(state != null) trader.setHome_state(state);
		if(zipcode != null) trader.setZipcode(zipcode);
		loginDao.save(login);
	}
}
