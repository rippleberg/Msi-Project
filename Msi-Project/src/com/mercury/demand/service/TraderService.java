package com.mercury.demand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.Trader;

@Service
@Transactional
public class TraderService {
	
	@Autowired
	@Qualifier("traderDao")
	Dao<Trader, Integer> traderDao;
	
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
		return traderDao.findBy("username", username);
	}
}
