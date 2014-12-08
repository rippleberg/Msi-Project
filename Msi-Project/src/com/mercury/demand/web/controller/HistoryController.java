package com.mercury.demand.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public @ResponseBody List<TransInfo> showHistory(ModelMap model) {
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
}
