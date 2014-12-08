package com.mercury.demand.web.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.demand.persistence.model.Creditcard;
import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.Trans;
import com.mercury.demand.service.CreditcardService;
import com.mercury.demand.service.RealTimePriceService;
import com.mercury.demand.service.StocksService;
import com.mercury.demand.service.TraderService;
import com.mercury.demand.service.TransactionService;

@Controller
public class BalanceController {
	
	@Autowired
	CreditcardService ccs;
	
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
	
	@Autowired
	private StocksService stock_service;
	

	public StocksService getStock_service() {
		return stock_service;
	}

	public void setStock_service(StocksService stock_service) {
		this.stock_service = stock_service;
	}
	
	@Autowired
	private RealTimePriceService price_s;
	
	public RealTimePriceService getPrice_s() {
		return price_s;
	}

	public void setPrice_s(RealTimePriceService price_s) {
		this.price_s = price_s;
	}

	@RequestMapping(value="/app/balance.htm", method=RequestMethod.GET)
	public ModelAndView viewBalance(Principal principal){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/app/balance");
		String username = principal.getName();
		Trader trader = trader_s.getTrader(username);
		mav.addObject("shownName", 
				trader.getFirst_name() + " " + trader.getLast_name());
		return mav;
	}
	
	@RequestMapping(value="app/addBalance.htm", method=RequestMethod.POST)
	public ModelAndView addBalance(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String card_holder = request.getParameter("Card_holder");
		byte[] card_number = request.getParameter("Card_number").getBytes();
		int expire_month = Integer.parseInt(request.getParameter("Expire_month"));
		int expire_year = Integer.parseInt(request.getParameter("Expire_year"));
		int code = Integer.parseInt(request.getParameter("Code"));
		double balance = Double.parseDouble(request.getParameter("Amount"));
		Creditcard creditcard = new Creditcard(card_holder, card_number, expire_month, expire_year, code);
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		trader.addCreditcard(creditcard);
		trader.setBalance(balance);
		trader_s.save(trader);
		mav.setViewName("/app/balance");
		return mav;
	}
	
	@RequestMapping(value="app/allcards.htm", method = RequestMethod.POST)
	public @ResponseBody List<Creditcard> getAllCards(Principal principal){
		String username = principal.getName();
		return ccs.getAllCards(username);
	}
	
	@RequestMapping(value="app/addcard.htm", method = RequestMethod.POST)
	public @ResponseBody List<Creditcard> addCreditcard(
			@RequestParam(value = "card_number") String cardNo,
			Principal principal){
		String username = principal.getName();
		System.out.println("=======================================");
		System.out.println(cardNo);
		//int lid = trader_s.getTrader(username).getLid();
		//ccs.addCard(card, lid);
		//return ccs.getAllCards(username);
		return new ArrayList<Creditcard>();
	}
	
	@RequestMapping(value="app/addAmount.htm", method = RequestMethod.POST)
	public ModelAndView addAmount(HttpServletRequest request, Principal principal){
		double balance = Double.parseDouble(request.getParameter("bal"));
		ccs.addBalance(balance, principal.getName());
		return new ModelAndView("redirect:portfolio.htm");
	}
	
}
