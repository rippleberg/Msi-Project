package com.mercury.demand.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mercury.demand.persistence.model.Creditcard;
import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.service.CreditcardService;
import com.mercury.demand.service.TransactionService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mercury.demand.persistence.model.Stock;
import com.mercury.demand.service.YahooFinance;

@Controller
@RequestMapping("/app")
public class AppController {

	@Autowired
	private CreditcardService cs;
	
	public CreditcardService getCs() {
		return cs;
	}

	public void setCs(CreditcardService cs) {
		this.cs = cs;
	}
	
	@Autowired
	private TransactionService ts;
	
	public TransactionService getTs() {
		return ts;
	}

	public void setTs(TransactionService ts) {
		this.ts = ts;
	}

	public void addCard(HttpServletRequest request){
		String card_holder = request.getParameter("Card_holder");
		byte[] card_number = request.getParameter("Card_number").getBytes();
		int expire_month = Integer.parseInt(request.getParameter("Expire_month"));
		int expire_year = Integer.parseInt(request.getParameter("Expire_year"));
		int code = Integer.parseInt(request.getParameter("Code"));
		Creditcard creditcard = new Creditcard(card_holder, card_number, expire_month, expire_year, code);
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = cs.getTrader(username);
		cs.creditcard(creditcard, trader);
	}
	
	@RequestMapping("/config.htm")
	public ModelAndView config() {
		return null;
	}
	
	@RequestMapping("/dashboard.htm")
	/*public ModelAndView dashboard(){
		return null;
	}*/
	public String dashboard(){
		return "app/dashboard";
	}
	
	@RequestMapping(value="/stock.htm", method = RequestMethod.GET)
	public @ResponseBody Stock getStockInfo (HttpServletRequest  request){
		Stock stock = new Stock(request.getParameter("sid"));
		YahooFinance.getPrice(stock);
		return stock;
	}
}
