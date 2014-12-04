package com.mercury.demand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.TraderInfo;

@Service
@Transactional
public class AdminManageService {

	@Autowired
	@Qualifier("traderDao")
	Dao<Trader, Integer> traderDao;
	
	public TraderInfo getAllTraders(){
		TraderInfo res = new TraderInfo();
		res.setTraders(traderDao.findAll());
		return res;
	}
	
	public void active(int lid){
		Trader trader = traderDao.findById(lid);
		if(trader.getActive().equals("1"))
			trader.setActive("0");
		else trader.setActive("1");
		traderDao.save(trader);
	}
}