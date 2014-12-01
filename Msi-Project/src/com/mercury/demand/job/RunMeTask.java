package com.mercury.demand.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.Trans;
import com.mercury.demand.service.TraderService;
import com.mercury.demand.service.TransactionService;

public class RunMeTask {
	
	private TransactionService tran_s;
	
	private TraderService trader_s;
	
	
	public void printMe() {
		System.out.println("Spring 3 + Quartz 1.8.6");
	}
	
	public void toDatabase() {
		List<Trader> traders = new ArrayList<Trader>();
		List<Trans> trans = new ArrayList<Trans>(); 
		try {
			File file = new File("transactions.csv");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while(reader.ready()) {
				String str = reader.readLine();
				String[] strs = str.split(",");
				Trader tempTrader = trader_s.getTrader(Integer.parseInt(strs[0]));
				Trans tempTrans = new Trans();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
