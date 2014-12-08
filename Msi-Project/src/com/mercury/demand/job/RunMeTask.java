package com.mercury.demand.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.mercury.demand.service.TransactionService;

public class RunMeTask {
	
	@Autowired
	TransactionService ts;
	
	public void printMe() {
		System.out.println("Spring 3 + Quartz 1.8.6");
		ts.saveToDatabase();
	}
	

}
