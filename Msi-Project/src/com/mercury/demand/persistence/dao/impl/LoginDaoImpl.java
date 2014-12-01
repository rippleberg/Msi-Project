package com.mercury.demand.persistence.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mercury.demand.persistence.dao.LoginDao;
import com.mercury.demand.persistence.model.Login;
@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	@Qualifier("loginSessionFactory")
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Login getLoginByLid(int lid) {
		// TODO Auto-generated method stub
		return (Login)this.getCurrentSession().get(Login.class, lid); 
	}

	@Override
	public Login getLoginByUsername(String username) {
		// TODO Auto-generated method stub
		Criteria ct = this.getCurrentSession().createCriteria(Login.class);
		return (Login)ct.add(Restrictions.eq("username", username)).uniqueResult();
	}
}
