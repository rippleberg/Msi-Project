package com.mercury.demand.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.service.TraderService;

@Controller
public class ProfileController {
	
	@Autowired
	private TraderService trader_s;
	
	@RequestMapping(value="/app/profile.htm", method=RequestMethod.GET)
	public ModelAndView viewProfile() {
		ModelAndView mav = new ModelAndView();
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Trader trader = trader_s.getTrader(username);
		mav.addObject("shownName", trader.getFirst_name());
		mav.addObject("firstName", trader.getFirst_name());
		mav.addObject("lastName",trader.getLast_name());
		mav.addObject("phone", trader.getPhone());
		mav.addObject("email", trader.getEmail());
		mav.addObject("address",trader.getAddress());
		mav.addObject("state", trader.getHome_state());
		mav.addObject("city", trader.getCity());
		mav.addObject("zipcode", trader.getZipcode());
		mav.addObject("balance", trader.getBalance());
		mav.setViewName("/app/profile");
		return mav;
	}
}
