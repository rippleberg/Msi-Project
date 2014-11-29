package com.mercury.demand.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.common.db.Dao;
import com.mercury.common.db.HibernateDao;
import com.mercury.demand.persistence.model.*;
import com.mercury.demand.service.RegisterService;

@Controller
public class SecurityController {

	@Autowired
	private RegisterService rs;
	
	public RegisterService getRs() {
		return rs;
	}

	public void setRs(RegisterService rs) {
		this.rs = rs;
	}
	
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
	
	@RequestMapping(value="/register.htm", method = RequestMethod.POST)
	public String enroll(HttpServletRequest  request){
		String username = request.getParameter("r_username");
		String password = request.getParameter("r_password");
		Login login = new Login(username, password);
		String firstname = request.getParameter("r_firstname");
		String lastname = request.getParameter("r_lastname");
		int phone = Integer.parseInt(request.getParameter("r_phone"));
		String email = request.getParameter("r_email");
		String address = request.getParameter("r_address");
		String city = request.getParameter("r_city");
		String home_state = request.getParameter("r_state");
		int zipcode = Integer.parseInt(request.getParameter("r_zipcode"));
		Trader trader = new Trader(firstname, lastname, email);
		trader.setPhone(phone);
		trader.setAddress(address);
		trader.setZipcode(zipcode);
		//trader.setCity(city);
		trader.setHome_state(home_state);
		rs.register(login, trader);
		return "security/index";
	}
	
	@RequestMapping("/test.htm")
	public String test(){
		Login login = new Login("test", "test");
		Dao<Login, Integer> loginDao = new HibernateDao<Login, Integer>(Login.class);
		loginDao.save(login);
		return "/security/index";
	}
}
