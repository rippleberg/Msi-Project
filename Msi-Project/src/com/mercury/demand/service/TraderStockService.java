package com.mercury.demand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.TraderStock;

@Service
@Transactional
public class TraderStockService {
	
	@Autowired
	@Qualifier("traderStockDao")
	Dao<TraderStock, String> traderStockDao;
	
	public void save(TraderStock traderStock) {
		traderStockDao.save(traderStock);
	}
	
	public void delete(TraderStock traderStock) {
		traderStockDao.delete(traderStock);
	}
	
	public TraderStock getStock(String sid) {
		return traderStockDao.findById(sid);
	}
	
	public List<TraderStock> getStocks(int lid) {
		return traderStockDao.findAllBy("LID", lid);
	}
}
