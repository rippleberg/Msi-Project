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

import com.mercury.demand.persistence.model.ChartInfo;
import com.mercury.demand.persistence.model.Stock;
import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.TraderStock;
import com.mercury.demand.persistence.model.TraderStockInfo;
import com.mercury.demand.persistence.model.Trans;
import com.mercury.demand.service.TraderService;
import com.mercury.demand.service.TraderStockService;
import com.mercury.demand.service.TransactionService;
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
	private TraderStockService stock_s;
	
	public TraderStockService getStock_s() {
		return stock_s;
	}

	public void setStock_s(TraderStockService stock_s) {
		this.stock_s = stock_s;
	}
	
	@Autowired
	private TransactionService trans_s;
	

	public TransactionService getTrans_s() {
		return trans_s;
	}

	public void setTrans_s(TransactionService trans_s) {
		this.trans_s = trans_s;
	}

	@RequestMapping(value="/app/portfolio.htm", method=RequestMethod.GET)
	public ModelAndView viewPortfolio(){
		ModelAndView mav = new ModelAndView();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		mav.addObject("shownName", 
				trader.getFirst_name());
		mav.setViewName("/app/portfolio");
		return mav;
	}
	
	@RequestMapping(value="/app/addSymbol.htm", method=RequestMethod.POST)
	public ModelAndView addSymbol(HttpServletRequest request) {
		System.out.println("Add symbol has been executed!!!!!!!!!!");
		ModelAndView mav = new ModelAndView();
		String sid = request.getParameter("symbol");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		System.out.println(trader.getFirst_name()+"\t"+trader.getLast_name()+"!!!!!!!!!!!!!!!!!");
		Set<TraderStock> traderStocks=trader.getStocks();
		TraderStock traderStock = new TraderStock(sid);
		traderStock.setLid(trader.getLid());
		if(!traderStocks.contains(traderStock)) {
			traderStock.setPrice(0.0);
			traderStock.setQuantity(0);
			stock_s.save(traderStock);
		}
		mav.setViewName("/app/portfolio");
		return mav;
	}
	
	@RequestMapping(value="/app/showPortfolio.htm", method=RequestMethod.POST)
	public @ResponseBody List<Stock> showPortfolio(HttpServletRequest request) {
		System.out.println("ShowPortfolio has been executed!!!!!!!!!!");
		List<Stock> stockList = new ArrayList<Stock>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		List<TraderStock> trader_stocks = new ArrayList<TraderStock>(trader.getStocks());
		for(TraderStock trader_stock:trader_stocks) {
			Stock tempStock = new Stock(trader_stock.getSid());
			stockList.add(tempStock);
		}
		YahooFinance.marketData(stockList);
		return stockList;
	}
	
	@RequestMapping(value="/app/showProperty.htm", method=RequestMethod.POST)
	public @ResponseBody List<TraderStockInfo> showProperty(HttpServletRequest request) {
		System.out.println("Show Property has been executed?????????????????");
		List<TraderStockInfo> infoList = new ArrayList<TraderStockInfo>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		List<TraderStock> stockList = new ArrayList<TraderStock>(trader.getStocks());
		for(TraderStock stock:stockList) {
			TraderStockInfo info = new TraderStockInfo(stock.getSid(), stock.getPrice(), stock.getQuantity());
			if(info.getQuantity()>0) {
				infoList.add(info);
			}
		}
		return infoList;
	}
	
	public ChartInfo findBySid(List<ChartInfo> infoList, String sid) {
		for(ChartInfo info:infoList) {
			if(info.getSid().equalsIgnoreCase(sid)) {
				return info;
			}
		}
		return null;
	}
	@RequestMapping(value="/app/drawChart.htm", method=RequestMethod.POST)
	public @ResponseBody List<ChartInfo> drawChart(HttpServletRequest request) {
		System.out.println("Draw chart has been executed?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?");
		List<ChartInfo> infoList = new ArrayList<ChartInfo>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		List<TraderStock> stockList = new ArrayList<TraderStock>(trader.getStocks());
		//Collect data for pieChart
		for(TraderStock stock:stockList) {
			ChartInfo info = new ChartInfo();
			info.setSid(stock.getSid());
			info.setQuantity(stock.getQuantity());
			info.setPrice(stock.getPrice());
			infoList.add(info);
		}
		//Collect data for barChart
		List<Trans> dbList = trans_s.getAllCommittedTrans();
		List<Trans> csvList = trans_s.getAllUncommittedTrans();
		if(dbList==null) dbList = new ArrayList<Trans>();
		if(csvList==null) csvList = new ArrayList<Trans>();

		for(Trans tran:dbList) {
			if(tran.getT_status().equalsIgnoreCase("D")) continue;
			if(tran.getLid()!=trader.getLid()) continue;
			ChartInfo info = findBySid(infoList, tran.getSid());
			if(info==null) continue;
			if(tran.getT_type().equalsIgnoreCase("B")) {
				info.setTotalBought(info.getTotalBought()+tran.getQuantity());
			}else {
				info.setTotalSold(info.getTotalSold()+tran.getQuantity());
			}
		}
		
		for(Trans tran:csvList) {
			if(tran.getT_status().equalsIgnoreCase("D")) continue;
			if(tran.getLid()!=trader.getLid()) continue;
			ChartInfo info = findBySid(infoList, tran.getSid());
			if(info==null) continue;
			if(tran.getT_type().equalsIgnoreCase("B")) {
				info.setTotalBought(info.getTotalBought()+tran.getQuantity());
			}else {
				info.setTotalSold(info.getTotalSold()+tran.getQuantity());
			}
		}
		
		for(ChartInfo info: infoList) {
			System.out.println(info.getSid()+"\t"+info.getPrice()+"\t"+info.getQuantity()+"\t"+info.getTotalBought()+"\t"+info.getTotalSold());
		}
		
//		for(ChartInfo info : infoList) {
//			System.out.println(info.getSid()+"\t"+info.getPrice()+"\t"+info.getQuantity()+"\t"+info.getTotalBought()+"\t"+info.getTotalSold());
//			if(info.getTotalBought()==0&&info.getTotalBought()==0) {
//				System.out.println("SID: "+info.getSid());
//				infoList.remove(info);
//			}
//		}
		return infoList;
	}
}
