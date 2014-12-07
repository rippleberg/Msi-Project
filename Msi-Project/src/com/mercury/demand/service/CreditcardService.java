package com.mercury.demand.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.*;
import com.mercury.demand.persistence.model.Creditcard;
import com.mercury.demand.persistence.model.Login;
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
	@Autowired
	@Qualifier("loginDao")
	Dao<Login, Integer> loginDao;
	
	public void saveCreditcard(Creditcard creditcard, Trader trader){
		double balance=0;
		//creditcardDao.save(creditcard);
		trader.setBalance(balance);
		trader.addCreditcard(creditcard);
		traderDao.save(trader);
	}
	
	public List<Creditcard> getAllCards(String username){
		int lid = loginDao.findBy("username", username).getLid();
		return creditcardDao.findAllBy("lid", lid);
	}
	
	public void addCard(Creditcard newcard, int lid){
		List<Creditcard> cards = creditcardDao.findAllBy("lid", lid);
		newcard.setLid(lid);
		for(Creditcard card:cards){
			if(card.equals(newcard)) return;
		}
		creditcardDao.save(newcard);
	}
}
