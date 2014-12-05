package com.mercury.demand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.Login;
import com.mercury.demand.persistence.model.Trader;

@Service
@Transactional
public class TraderService {

	@Autowired
	@Qualifier("traderDao")
	Dao<Trader, Integer> traderDao;
	@Autowired
	@Qualifier("loginDao")
	Dao<Login, Integer> loginDao;
	
	public void save(Trader trader) {
		traderDao.save(trader);
	}
	
	public void delete(Trader trader) {
		traderDao.delete(trader);
	}
	
	public Trader getTrader(int lid) {
		return traderDao.findById(lid);
	}
	
	public Trader getTrader(String username) {
		return loginDao.findBy("username", username).getTrader();
	}
}
