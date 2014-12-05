package com.mercury.demand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.Stocks;
import com.mercury.demand.persistence.model.StocksInfo;

@Service
@Transactional
public class StocksService {
	@Autowired
	@Qualifier("stocksDao")
	Dao<Stocks, String> stocksDao;
	
	public StocksInfo getStocksInfo(){
		StocksInfo res = new StocksInfo();
		res.setStocks(stocksDao.findAll());
		return res;
	}
	
	public void addStocks(String sid){
		Stocks stocks = new Stocks(sid);
		stocksDao.save(stocks);
	}
	
	public void removeStocks(String sid){
		System.out.println("====================");
		System.out.println(sid);
		Stocks stocks = stocksDao.findById(sid);
		if(stocks != null)
			stocksDao.delete(stocks);
	}
}
