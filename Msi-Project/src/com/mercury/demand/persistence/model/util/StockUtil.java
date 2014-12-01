package com.mercury.demand.persistence.model.util;

import java.util.List;

import com.mercury.demand.persistence.model.Stock;

public class StockUtil {

	public static void implementStock(Stock stock, List<String> info){
		stock.setSid(info.get(0));
		stock.setName(info.get(1));
		stock.setPrice(Double.parseDouble(info.get(2)));
		stock.setChange(Double.parseDouble(info.get(3)));
		String pcString = info.get(4);
		stock.setPercentChange(Double.parseDouble(pcString.substring(0, pcString.length()-1)));
		stock.setVolumn(Integer.parseInt(info.get(6)));
		String[] hl = info.get(5).split("-");
		hl[0] = hl[0].substring(0, hl[0].length() - 1);
		hl[1] = hl[1].substring(1);
		stock.setLow(Double.parseDouble(hl[0]));
		stock.setHigh(Double.parseDouble(hl[1]));
	}
}
