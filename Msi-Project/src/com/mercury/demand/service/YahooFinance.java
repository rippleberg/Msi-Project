package com.mercury.demand.service;

import java.io.*;
import java.net.*;
import java.util.List;

import com.mercury.demand.persistence.model.Stock;
import com.mercury.demand.persistence.model.util.StockUtil;
import com.mercury.util.CsvParser;

public class YahooFinance {
	public static void getPrice(Stock stock) {
		String yahoo_quote = "http://finance.yahoo.com/d/quotes.csv?s=" + stock.getSid() + "&f=snl1c1p2mv&e=.c";
		try {
			URL url = new URL(yahoo_quote);
			URLConnection urlconn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
			String content = in.readLine();
			List<String> info = CsvParser.parse(content);
			StockUtil.implementStock(stock, info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void marketData(List<Stock> stocks) {
		for (Stock stock:stocks) {
			getPrice(stock);
		}
	}
}
