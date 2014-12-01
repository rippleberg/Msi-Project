package com.mercury.demand.web.controller;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
=======

import javax.servlet.http.HttpServletRequest;

>>>>>>> refs/remotes/origin/StockInfo
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD

import com.mercury.demand.persistence.model.Creditcard;
import com.mercury.demand.service.CreditcardService;
=======
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mercury.demand.persistence.model.Stock;
import com.mercury.demand.service.YahooFinance;
>>>>>>> refs/remotes/origin/StockInfo

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
	
	
	public void enroll(HttpServletRequest request){
		
		
		String card_holder = request.getParameter("Card_holder");
		String card_number = request.getParameter("Card_number");
		int expire_month = Integer.parseInt(request.getParameter("Expire_month"));
		int expire_year = Integer.parseInt(request.getParameter("Expire_year"));
		int code = Integer.parseInt(request.getParameter("Code"));
		Creditcard creditcard = new Creditcard(card_holder,card_number,expire_month,expire_year,code);
		
		
		//return null;
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
