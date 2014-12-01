package com.mercury.demand.persistence.dao;

import com.mercury.demand.persistence.model.Login;

public interface LoginDao {
	public Login getLoginByLid(int lid);
	public Login getLoginByUsername(String username);
}
