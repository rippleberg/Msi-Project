package com.mercury.demand.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.demand.persistence.model.TraderInfo;
import com.mercury.demand.service.AdminManageService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminManageService ams;
	
	@RequestMapping("/admin.htm")
	public String admin(){
		return "admin/admin";
	}
	
	@RequestMapping("/management.htm")
	public ModelAndView management(){	
		TraderInfo traderInfo = ams.getAllTraders();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/management");
		mav.addObject("traderInfo", traderInfo);
		return mav;
	}
	
	@RequestMapping("/deactive/{lid}.htm")
	public String deactive(){
		return "admin/management";
	}
}
