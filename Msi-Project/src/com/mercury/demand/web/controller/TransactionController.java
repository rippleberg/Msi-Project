package com.mercury.demand.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.TraderStock;
import com.mercury.demand.persistence.model.Trans;
import com.mercury.demand.service.RealTimePriceService;
import com.mercury.demand.service.TraderService;
import com.mercury.demand.service.TraderStockService;
import com.mercury.demand.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	private TraderService trader_s;
	
	@Autowired
	private TransactionService trans_s;
	
	@Autowired
	private RealTimePriceService price_s;
	
	@Autowired
	private TraderStockService traderStock_s;
	
	@RequestMapping(value="app/transaction.htm", method=RequestMethod.GET)
	public ModelAndView viewTransaction() {
		ModelAndView mav = new ModelAndView();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		mav.addObject("shownName", 
				trader.getFirst_name());
		mav.setViewName("app/transaction");
		return mav;
	}
	
	
	@RequestMapping(value="app/buyStock.htm", method=RequestMethod.POST)
	public ModelAndView buyStock(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String sid = request.getParameter("buySymbol");
		int quantity = Integer.parseInt(request.getParameter("buyQuantity"));
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		double price = price_s.getRealTimePrice(sid);
		double balance = trader.getBalance()-quantity*price-5.00;
		//Add transaction
		Trans tran = new Trans(sid, new Date(), price, quantity, "B", "P");
		TraderStock traderStock = trader.getTraderStock(sid);
		if(traderStock==null) traderStock = new TraderStock(sid);
		traderStock.setPrice(price);
		traderStock.setQuantity(traderStock.getQuantity()+quantity);
		trader.removeStock(traderStock);
		trader.addStock(traderStock);
		//Update trader balance
		if(balance<0) {
			tran.setT_status("D");
		}else {
			trader.setBalance(balance);	
			traderStock.setPrice(price);
			traderStock.setQuantity(traderStock.getQuantity()+quantity);
		}
		trans_s.makeTransaction(trader, tran);
		trader_s.save(trader);
		mav.setViewName("/app/history");
		return mav;
	}
	
	@RequestMapping(value="app/sellStock.htm", method=RequestMethod.POST)
	public ModelAndView sellStock(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String sid = request.getParameter("sellSymbol");
		int quantity = Integer.parseInt(request.getParameter("sellQuantity"));
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		double price = price_s.getRealTimePrice(sid);
		double balance = trader.getBalance()+quantity*price-5.00;
		
		//Add Transaction
		Trans tran = new Trans(sid, new Date(), price, quantity, "S", "P");
		tran.setLid(trader.getLid());
		TraderStock traderStock = trader.getTraderStock(sid);
		if(traderStock==null||traderStock.getQuantity()<quantity) {
			tran.setT_status("D");
		}else {
			traderStock.setQuantity(traderStock.getQuantity()-quantity);
			traderStock.setPrice(price);
			trader.setBalance(balance);
		}
		
//		TraderStock traderStock = new TraderStock(sid);
//		Set<TraderStock> stocks = trader.getStocks();
		
//		if(stocks==null||!(stocks.contains(traderStock))) {
//			tran.setT_status("D");
//		}else {
//			traderStock=traderStock_s.getStock(sid);
//			if(traderStock.getQuantity()<quantity) {
//				tran.setT_status("D");
//			}else {
//				traderStock.setQuantity(traderStock.getQuantity()-quantity);
//				traderStock.setPrice(price);
//				trader.setBalance(balance);
//			}
//		}
		trans_s.makeTransaction(trader, tran);
		trader_s.save(trader);
		mav.setViewName("/app/history");
		return mav;
	}
}
