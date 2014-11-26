package com.mercury.demand.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
	
	@RequestMapping("/login.htm")
	public String login(ModelMap model) {
		return "security/login";
	}
	
	/*@RequestMapping(value="/content/main.htm", method = RequestMethod.GET)
	public ModelAndView mainPage() {	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content/hello");
		mav.addObject("title", "Hello, welcome to Customized Spring Security");
		return mav;
	}*/
	
	@RequestMapping("/index.htm")
	public String index(){
		return "security/index";
	}
	
	@RequestMapping("/register.htm")
	public String register(){
		return "security/register";
	}
	
	@RequestMapping(value="/enroll.htm", method = RequestMethod.POST)
	public String enroll(HttpServletRequest  request){
		return "";
	}
}
