package com.mercury.demand.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.Trans;
import com.mercury.demand.service.TraderService;

public class RunMeTask {
	
	private TraderService trader_s;
	
	
	public void printMe() {
		System.out.println("Spring 3 + Quartz 1.8.6");
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
