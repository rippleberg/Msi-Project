package com.mercury.demand.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class RealTimePriceService {
	public double getRealTimePrice(String symbol){
		double price = 0.0;
		String yahoo_quote = "http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=snc1l1&e=.c";
		try {
			URL url = new URL(yahoo_quote);
			URLConnection urlconn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
			String content = in.readLine();
			content = content.replace((char)34, (char)32);
			String[] tokens = content.split(",");
			price = Double.parseDouble(tokens[tokens.length-1].trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}
}
