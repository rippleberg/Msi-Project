package com.mercury.demand.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BalanceController {
	
	@RequestMapping(value="/app/balance.htm", method = RequestMethod.GET)
	public ModelAndView viewBalance(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/app/balance");
		return mav;
	}
}
