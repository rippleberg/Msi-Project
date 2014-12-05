package com.mercury.demand.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mercury.demand.persistence.model.Trader;
import com.mercury.demand.persistence.model.Trans;
import com.mercury.demand.service.TraderService;
import com.mercury.demand.service.TransactionService;

public class RunMeTask {
	
	@Autowired
	TransactionService ts;
	
	public void printMe() {
		System.out.println("Spring 3 + Quartz 1.8.6");
		ts.saveToDatabase();
	}
	

}
