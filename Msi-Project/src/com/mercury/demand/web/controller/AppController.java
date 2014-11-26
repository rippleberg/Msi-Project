package com.mercury.demand.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AppController {

	@RequestMapping("/config")
	public ModelAndView config() {
		return null;
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(){
		return null;
	}
}
