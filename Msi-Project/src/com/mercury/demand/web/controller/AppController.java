package com.mercury.demand.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mercury.demand.persistence.model.Stock;
import com.mercury.demand.service.YahooFinance;

@Controller
@RequestMapping("/app")
public class AppController {

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
