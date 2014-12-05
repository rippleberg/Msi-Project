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
import com.mercury.demand.service.TraderService;
import com.mercury.demand.service.TransactionService;

@Controller
public class BalanceController {
	
	@Autowired
	private TraderService trader_s;
	
	public TraderService getTrader_s() {
		return trader_s;
	}

	public void setTrader_s(TraderService trader_s) {
		this.trader_s = trader_s;
	}
	
	@Autowired
	private TransactionService trans_s;
	
	public TransactionService getTrans_s() {
		return trans_s;
	}
	public void setTrans_s(TransactionService trans_s) {
		this.trans_s = trans_s;
	}

	@RequestMapping(value="/app/balance.htm", method = RequestMethod.GET)
	public ModelAndView viewBalance(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/app/balance");
		return mav;
	}
	
	@RequestMapping(value="app/buyStock.htm", method = RequestMethod.POST)
	public ModelAndView buyStock(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String sid = request.getParameter("symbol");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		Trans tran = new Trans(sid, new Date(), )
		trans_s.makeTransaction(trader, tran);
		mav.setViewName("/app/balance");
		return mav;
	}
}
