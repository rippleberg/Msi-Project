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
	@Autowired
	@Qualifier("traderDao")
	Dao<Trader, Integer> traderDao;
	
	public Trader getCurrentTrader(String username){
		Login login = loginDao.findBy("username", username);
		return login.getTrader();
	}
	
	public void config(int lid, String firstname, String lastname,
			String phone, String email, String address, String city, String state, String zipcode){
		Trader trader = traderDao.findById(lid);
		if(trader == null) return;
		if(firstname != null) trader.setFirst_name(firstname);
		if(lastname != null) trader.setLast_name(firstname);
		if(phone != null) trader.setPhone(phone);
		if(email != null) trader.setEmail(email);
		if(address != null) trader.setAddress(address);
		if(city != null) trader.setCity(city);
		if(state != null) trader.setHome_state(state);
		if(zipcode != null) trader.setZipcode(zipcode);
		traderDao.save(trader);
	}
	
	public void changePassword(int lid, String oldPwd, String newPwd){
		Login login = loginDao.findById(lid);
		if(login.getPassword().equals(oldPwd)){
			System.out.println("=====================");
			login.setPassword(newPwd);
			loginDao.save(login);
		}
	}
	public Login getLogin(String username){
		return loginDao.findBy("username", username);
	}
}
