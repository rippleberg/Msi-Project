package com.mercury.demand.web.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.mercury.demand.persistence.model.Login;
import com.mercury.demand.persistence.model.Stocks;
import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.Trans;
import com.mercury.demand.service.AdminManageService;
import com.mercury.demand.service.ConfigService;
import com.mercury.demand.service.StocksService;
import com.mercury.demand.service.TraderService;
import com.mercury.demand.service.TransactionService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminManageService ams;
	@Autowired
	private TraderService ts;
	@Autowired
	private ConfigService cfs;
	@Autowired
	private StocksService sks;
	@Autowired
	private TransactionService txs;
	
	@RequestMapping("/admin.htm")
	public String admin(ModelMap model, Principal principal){
		model.addAttribute("loc", "admin");
		Login login = cfs.getLogin(principal.getName());
		model.addAttribute("username", login.getUsername());
		return "admin/admin";
	}
	
	@RequestMapping("/management.htm")
	public ModelAndView management(){	
		List<Trader> traders = ams.getAllTraders();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/management");
		mav.addObject("traders", traders);
		return mav;
	}

	@RequestMapping("/active/{lid}.htm")
	public ModelAndView deactive(@PathVariable("lid") String lid){
		ams.active(Integer.parseInt(lid));
		return new ModelAndView("redirect:" + "../management.htm");
	}
	
	@RequestMapping("/config/{lid}.htm")
	public ModelAndView config(@PathVariable("lid") String lid){
		ModelAndView mav = new ModelAndView();
		Trader trader = ts.getTrader(Integer.parseInt(lid));
		mav.setViewName("admin/config");
		mav.addObject("name", trader.getLogin().getUsername());
		mav.addObject("trader", trader);
		return mav;
	}
	
	@RequestMapping(value="/config/config.htm", method = RequestMethod.POST)
	public ModelAndView config(HttpServletRequest request){
		int lid = Integer.parseInt(request.getParameter("c_lid"));
		String firstname = request.getParameter("c_firstname");
		String lastname = request.getParameter("c_lastname");
		String phone = request.getParameter("c_phone");
		String email = request.getParameter("c_email");
		String address = request.getParameter("c_address");
		String city = request.getParameter("c_city");
		String state = request.getParameter("c_state");
		String zipcode = request.getParameter("c_zipcode");
		cfs.config(lid, firstname, lastname, phone, email, 
				address, city, state, zipcode);
		return new ModelAndView("redirect:" + lid + ".htm");
	}
	
	@RequestMapping("/settings.htm")
	public String changePassword(ModelMap model){
		return "admin/password";
	}
	
	@RequestMapping(value="/stocks.htm", method = RequestMethod.POST)
	public @ResponseBody List<Stocks> getStocks(){
		return sks.getStocks();
	}
	@RequestMapping(value="/addstocks.htm", method = RequestMethod.POST)
	public @ResponseBody List<Stocks> addStocks(@RequestBody Stocks stocks){
		sks.addStocks(stocks.getSid());
		return sks.getStocks();
	}
	@RequestMapping(value="/rmstocks.htm", method = RequestMethod.POST)
	public @ResponseBody List<Stocks> removeStock(@RequestBody Stocks stocks){
		sks.removeStocks(stocks.getSid());
		return sks.getStocks();
	}
	@RequestMapping(value="/untrans.htm", method = RequestMethod.POST)
	public @ResponseBody List<Trans> getUnComTrans(){
		return txs.getAllUncommittedTrans();
	}
	@RequestMapping(value="/cotrans.htm", method = RequestMethod.POST)
	public @ResponseBody List<Trans> getComTrans(){
		return txs.getAllCommittedTrans();
	}
	@RequestMapping(value="/maketrans.htm", method = RequestMethod.POST)
	public @ResponseBody List<Trans> makeTrans(){
		txs.saveToDatabase();
		return txs.getAllCommittedTrans();
	}
}
