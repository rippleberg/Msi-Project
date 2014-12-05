package com.mercury.demand.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.demand.persistence.model.Stock;
import com.mercury.demand.persistence.model.Stocks;
import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.service.StocksService;
import com.mercury.demand.service.TraderService;
import com.mercury.demand.service.YahooFinance;

@Controller
public class PortfolioController {
	
	@Autowired
	private TraderService trader_s;
	
	public TraderService getTrader_s() {
		return trader_s;
	}

	public void setTrader_s(TraderService trader_s) {
		this.trader_s = trader_s;
	}
	
	@Autowired
	private StocksService stock_s;
	
	public StocksService getStock_s() {
		return stock_s;
	}

	public void setStock_s(StocksService stock_s) {
		this.stock_s = stock_s;
	}

	@RequestMapping(value="/app/portfolio.htm", method=RequestMethod.GET)
	public ModelAndView viewPortfolio(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/app/portfolio");
		return mav;
	}
	
	@RequestMapping(value="/app/addSymbol.htm", method=RequestMethod.POST)
	public ModelAndView addSymbol(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String sid = request.getParameter("symbol");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		Set<Stocks> traderStocks=trader.getStocks();
		Stocks stock = stock_s.getStockBySymbol(sid);
		if(!traderStocks.contains(stock)) {
			trader.addStock(stock);
		}
		trader_s.save(trader);
		mav.setViewName("/app/portfolio");
		return mav;
	}
	
	@RequestMapping(value="/app/showPortfolio.htm", method=RequestMethod.POST)
	public @ResponseBody List<Stock> showPortfolio(HttpServletRequest request) {
		List<Stock> stockList = new ArrayList<Stock>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		Set<Stocks> trader_stocks = trader.getStocks();
		for(Stocks trader_stock:trader_stocks) {
			Stock tempStock = new Stock(trader_stock.getSid());
			stockList.add(tempStock);
		}
		YahooFinance.marketData(stockList);
		return stockList;
	}
}