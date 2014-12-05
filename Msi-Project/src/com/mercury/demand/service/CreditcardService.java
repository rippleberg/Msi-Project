package com.mercury.demand.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.*;
import com.mercury.demand.persistence.model.Creditcard;
import com.mercury.demand.persistence.model.Trader;

@Service
@Transactional
public class CreditcardService {

	@Autowired
	@Qualifier("creditcardDao")
    Dao<Creditcard, Integer>creditcardDao;	
	@Autowired
	@Qualifier("traderDao")
	Dao<Trader, Integer> traderDao;
	
	public void creditcard(Creditcard creditcard, Trader trader){
		double balance=0;
		//creditcardDao.save(creditcard);
		trader.setBalance(balance);
		trader.addCreditcard(creditcard);
		traderDao.save(trader);
	}
}
