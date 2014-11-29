package com.mercury.demand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.*;
import com.mercury.demand.persistence.model.Login;
import com.mercury.demand.persistence.model.Trader;

@Service
@Transactional
public class RegisterService {

	@Autowired
	@Qualifier("loginDao")
	Dao<Login, Integer> loginDao;
	
	@Autowired
	@Qualifier("traderDao")
	Dao<Trader, Integer> traderDao;
	
	//Dao<Trader> td;
	
	public void register(Login login, Trader trader){
		loginDao.save(login);
		traderDao.save(trader);
	}
}
