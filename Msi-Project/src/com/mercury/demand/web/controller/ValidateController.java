package com.mercury.demand.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mercury.demand.service.ValidateService;

@Controller
@RequestMapping("/validate")
public class ValidateController {

	@Autowired
	private ValidateService vs;
	
	@RequestMapping(value="/username")
	@ResponseBody
	public String validateUsername(String username) {
		if(vs.validate(username)) {
			return "success";
		}
		else {
			return "failure";
		}
	}
}