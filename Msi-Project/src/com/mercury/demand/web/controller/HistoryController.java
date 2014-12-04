package com.mercury.demand.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoryController {
	
	@RequestMapping(value="/app/history.htm", method = RequestMethod.GET)
	public ModelAndView ViewHistory(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/app/history");
		return mav;
	}
}
