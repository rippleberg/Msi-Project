package com.mercury.demand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.Trader;

@Service
@Transactional
public class AdminManageService {

	@Autowired
	@Qualifier("traderDao")
	Dao<Trader, Integer> traderDao;
	
	public List<Trader> getAllTraders(){
		return traderDao.findAll();
	}
	
	public void active(int lid){
		Trader trader = traderDao.findById(lid);
		if(trader.getActive().equals("1"))
			trader.setActive("0");
		else trader.setActive("1");
		traderDao.save(trader);
	}
}
