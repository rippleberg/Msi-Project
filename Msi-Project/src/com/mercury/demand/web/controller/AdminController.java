package com.mercury.demand.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/admin.htm")
	public String admin(){
		return "admin/admin";
	}
	
	@RequestMapping("/management.htm")
	public String management(){
		return "admin/management";
	}
	
	@RequestMapping("/deactive/{lid}.htm")
	public String deactive(){
		return "admin/management";
	}
}
