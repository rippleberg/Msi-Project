package com.mercury.demand.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Autowired
	private TraderService trader_s;
	
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
	
	public void save(Trans trans) {
		transDao.save(trans);
	}
	
	public void delete(Trans trans) {
		transDao.delete(trans);
	}
	
	public Trans getTransByTid(int tid) {
		return transDao.findById(tid);
	}
	public List<Trans> getTransByLid(int lid) {
		return transDao.findAllBy("LID", lid);
	}
	
	public List<Trans> getTransBySid(String sid) {
		return transDao.findAllBy("SID", sid);
	}
	
	
	
	public List<Trans> getAllUncommittedTrans(){
		List<Trans> res = new ArrayList<Trans>();
		try {
			File file = new File("transactions.csv");
			if(!file.exists()) return null;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while(reader.ready()) {
				String str = reader.readLine();
				String[] strs = str.split(",");
				Trans tempTrans = new Trans();
				tempTrans.setSid(strs[1]);
				SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
				Date date = formatter.parse(strs[2]);
				tempTrans.setT_time(date);
				tempTrans.setPrice(Double.parseDouble(strs[3]));
				tempTrans.setQuantity(Integer.parseInt(strs[4]));
				tempTrans.setT_status(strs[6]);
				res.add(tempTrans);
			}
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return res;
	}
	
	public List<Trans> getAllCommittedTrans(){
		return transDao.findAll();
	}
	
	public void saveToDatabase() {
		Map<Trader, List<Trans>> transactions = new HashMap<Trader, List<Trans>>();
		try {
			File file = new File("transactions.csv");
			if(!file.exists()) return;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while(reader.ready()) {
				String str = reader.readLine();
				String[] strs = str.split(",");
				Trader tempTrader = trader_s.getTrader(Integer.parseInt(strs[0]));
				Trans tempTrans = new Trans();
				tempTrans.setSid(strs[1]);
				SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
				Date date = formatter.parse(strs[2]);
				tempTrans.setT_time(date);
				tempTrans.setPrice(Double.parseDouble(strs[3]));
				tempTrans.setQuantity(Integer.parseInt(strs[4]));
				tempTrans.setT_type("C");
				tempTrans.setT_status(strs[6]);
				
				if(!transactions.containsKey(tempTrader)) {
					transactions.put(tempTrader, new ArrayList<Trans>());
				}
				transactions.get(tempTrader).add(tempTrans);
			}
			reader.close();
			file.delete();
		}catch (Exception e) {
			e.printStackTrace();
		}
		List<Trader> traders = new ArrayList<Trader>(transactions.keySet());
		for(Trader trader:traders) {
			List<Trans> list = transactions.get(trader);
			for(Trans trans:list) {
				trader.addTrans(trans);
			}
			trader_s.save(trader);
		}
		
	}
}
