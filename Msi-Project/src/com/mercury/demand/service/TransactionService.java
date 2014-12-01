package com.mercury.demand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.Trans;

@Service
@Transactional
public class TransactionService {
	
	@Autowired
	@Qualifier("transDao")
	Dao<Trans, Integer> transDao;
}
