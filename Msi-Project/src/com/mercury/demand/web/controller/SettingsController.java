package com.mercury.demand.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.service.TraderService;

@Controller
public class SettingsController {

	@Autowired
	private TraderService trader_s;
	@RequestMapping(value="app/settings.htm", method=RequestMethod.GET)
	public ModelAndView showSettings() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("app/settings");
		return mav;
	}
	
	@RequestMapping(value="app/changeProfile.htm", method = RequestMethod.POST)
	public String enroll(HttpServletRequest  request){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		String firstname = request.getParameter("r_firstname");
		String lastname = request.getParameter("r_lastname");
		String phone = request.getParameter("r_phone");
		String email = request.getParameter("r_email");
		String address = request.getParameter("r_address");
		String city = request.getParameter("r_city");
		String home_state = request.getParameter("r_state");
		String zipcode = request.getParameter("r_zipcode");
		if(firstname!=null) trader.setFirst_name(firstname);
		if(lastname!=null) trader.setFirst_name(lastname);
		if(phone!=null) trader.setPhone(phone);
		if(email!=null) trader.setEmail(email);
		if(address!=null) trader.setAddress(address);
		if(zipcode!=null) trader.setZipcode(zipcode);
		if(city!=null) trader.setCity(city);
		if(home_state!=null) trader.setHome_state(home_state);
		trader_s.save(trader);
		return "app/profile";
	}
}
