package com.mercury.demand.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AppController {

	@RequestMapping("/config.htm")
	public ModelAndView config() {
		return null;
	}
	
	@RequestMapping("/dashboard.htm")
	public ModelAndView dashboard(){
		return null;
	}
}
