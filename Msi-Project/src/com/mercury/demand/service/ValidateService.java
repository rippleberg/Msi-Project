package com.mercury.demand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.common.db.Dao;
import com.mercury.demand.persistence.model.Login;

@Service
@Transactional
public class ValidateService {
	@Autowired
	@Qualifier("loginDao")
	private Dao<Login, String> loginDao;
	
	public Dao<Login, String> getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(Dao<Login, String> loginDao) {
		this.loginDao = loginDao;
	}

	public boolean validate(String username) {
		List<Login> logins = loginDao.findAllBy("username", username);
		if(logins.size()==0) {
			return true;
		}
		else {
			return false;
		}
	}
}