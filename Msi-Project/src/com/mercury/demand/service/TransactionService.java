package com.mercury.demand.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.Trans;
@Service
@Transactional
public class TransactionService {
	
	
	@Autowired
	@Qualifier("transDao")
	Dao<Trans, Integer> transDao;
	
	public void makeTransaction(Trader trader, Trans tran) {
		try {
			File file = new File("transactions.csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.append(trader.getLid()+",").append(tran.getSid()+",").append(tran.getT_time()+",")
			.append(tran.getPrice()+",").append(tran.getQuantity()+",").append(tran.getT_type()+",").append(tran.getT_status());
			writer.newLine();
			writer.flush();
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
