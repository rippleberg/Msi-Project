package com.mercury.demand.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.Trans;
import com.mercury.demand.persistence.model.TransInfo;
import com.mercury.demand.service.TraderService;
import com.mercury.demand.service.TransactionService;

@Controller
public class HistoryController {
	
	@Autowired
	private TraderService trader_s;
	
	@Autowired
	private TransactionService trans_s;
	
	@RequestMapping(value="/app/history.htm", method = RequestMethod.GET)
	public ModelAndView ViewHistory(){
		ModelAndView mav = new ModelAndView();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		mav.addObject("shownName", 
				trader.getFirst_name());
		mav.setViewName("/app/history");
		return mav;
	}
	
	@RequestMapping(value="/app/showHistory.htm", method=RequestMethod.POST)
	public @ResponseBody List<TransInfo> showHistory(HttpServletRequest request, String symbol) {
		System.out.println("showHistory has been executed");
		List<TransInfo> transInfoList = new ArrayList<TransInfo>();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		List<Trans> dbList = trans_s.getAllCommittedTrans();
		List<Trans> csvList = trans_s.getAllUncommittedTrans();
		if(dbList==null) dbList = new ArrayList<Trans>();
		if(csvList==null) csvList = new ArrayList<Trans>();
		for(Trans tran:dbList) {
			if(tran.getLid()==trader.getLid()) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		for(Trans tran:csvList) {
			if(tran.getLid()==trader.getLid()) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		return transInfoList;
	}
	
	@RequestMapping(value="/app/showHistoryBySymbol.htm", method=RequestMethod.POST)
	public @ResponseBody List<TransInfo> showHistoryBySymbol(HttpServletRequest request, String symbol) {
		System.out.println("showHistoryBySymbol has been executed");
		List<TransInfo> transInfoList = new ArrayList<TransInfo>();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		List<Trans> dbList = trans_s.getAllCommittedTrans();
		List<Trans> csvList = trans_s.getAllUncommittedTrans();
		if(dbList==null) dbList = new ArrayList<Trans>();
		if(csvList==null) csvList = new ArrayList<Trans>();
		for(Trans tran:dbList) {
			if(tran.getLid()==trader.getLid()&&tran.getSid().equalsIgnoreCase(symbol)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		for(Trans tran:csvList) {
			if(tran.getLid()==trader.getLid()&&tran.getSid().equalsIgnoreCase(symbol)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		return transInfoList;
	}
	
	@RequestMapping(value="/app/showHistoryByType.htm", method=RequestMethod.POST)
	public @ResponseBody List<TransInfo> showHistoryByType(HttpServletRequest request, String type) {
		System.out.println("showHistoryByType has been executed");
		List<TransInfo> transInfoList = new ArrayList<TransInfo>();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		List<Trans> dbList = trans_s.getAllCommittedTrans();
		List<Trans> csvList = trans_s.getAllUncommittedTrans();
		if(dbList==null) dbList = new ArrayList<Trans>();
		if(csvList==null) csvList = new ArrayList<Trans>();
		for(Trans tran:dbList) {
			if(tran.getLid()==trader.getLid()&&tran.getT_type().equalsIgnoreCase(type)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		for(Trans tran:csvList) {
			if(tran.getLid()==trader.getLid()&&tran.getT_type().equalsIgnoreCase(type)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		return transInfoList;
	}
	
	@RequestMapping(value="/app/showHistoryByPrice.htm", method=RequestMethod.POST)
	public @ResponseBody List<TransInfo> showHistoryByPrice(HttpServletRequest request, String priceHigh, String priceLow) {
		System.out.println("showHistoryByPrice has been executed");
		List<TransInfo> transInfoList = new ArrayList<TransInfo>();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		Double high=0.0;
		Double low=0.0;
		if(priceHigh!=null) high = Double.parseDouble(priceHigh);
		if(priceLow!=null) low = Double.parseDouble(priceLow);
		List<Trans> dbList = trans_s.getAllCommittedTrans();
		List<Trans> csvList = trans_s.getAllUncommittedTrans();
		if(dbList==null) dbList = new ArrayList<Trans>();
		if(csvList==null) csvList = new ArrayList<Trans>();
		for(Trans tran:dbList) {
			if(tran.getLid()==trader.getLid()&&(tran.getPrice()>=low&&tran.getPrice()<=high)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		for(Trans tran:csvList) {
			if(tran.getLid()==trader.getLid()&&(tran.getPrice()>=low&&tran.getPrice()<=high)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		return transInfoList;
	}
	
	@RequestMapping(value="/app/showHistoryByVolume.htm", method=RequestMethod.POST)
	public @ResponseBody List<TransInfo> showHistoryByVolume(HttpServletRequest request, String volumeHigh, String volumeLow) {
		System.out.println("showHistoryByPrice has been executed");
		List<TransInfo> transInfoList = new ArrayList<TransInfo>();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		int high=0;
		int low=0;
		if(volumeHigh!=null) high = Integer.parseInt(volumeHigh);
		if(volumeLow!=null) low = Integer.parseInt(volumeLow);
		List<Trans> dbList = trans_s.getAllCommittedTrans();
		List<Trans> csvList = trans_s.getAllUncommittedTrans();
		if(dbList==null) dbList = new ArrayList<Trans>();
		if(csvList==null) csvList = new ArrayList<Trans>();
		for(Trans tran:dbList) {
			if(tran.getLid()==trader.getLid()&&(tran.getQuantity()>=low&&tran.getQuantity()<=high)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		for(Trans tran:csvList) {
			if(tran.getLid()==trader.getLid()&&(tran.getQuantity()>=low&&tran.getQuantity()<=high)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		return transInfoList;
	}
	
	@RequestMapping(value="/app/showHistoryByStatus.htm", method=RequestMethod.POST)
	public @ResponseBody List<TransInfo> showHistoryByStatus(HttpServletRequest request, String status) {
		System.out.println("showHistoryByStatus has been executed");
		List<TransInfo> transInfoList = new ArrayList<TransInfo>();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		List<Trans> dbList = trans_s.getAllCommittedTrans();
		List<Trans> csvList = trans_s.getAllUncommittedTrans();
		if(dbList==null) dbList = new ArrayList<Trans>();
		if(csvList==null) csvList = new ArrayList<Trans>();
		for(Trans tran:dbList) {
			if(tran.getLid()==trader.getLid()&&tran.getT_status().equalsIgnoreCase(status)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		for(Trans tran:csvList) {
			if(tran.getLid()==trader.getLid()&&tran.getT_status().equalsIgnoreCase(status)) {
				TransInfo tranInfo = new TransInfo(tran.getSid(), tran.getT_time(), tran.getPrice(), tran.getQuantity(), tran.getT_type(), tran.getT_status());
				transInfoList.add(tranInfo);
			}
		}
		return transInfoList;
	}
}
