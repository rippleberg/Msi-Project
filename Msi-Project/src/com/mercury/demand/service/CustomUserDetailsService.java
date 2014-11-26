package com.mercury.demand.service;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.demand.persistence.dao.*;
import com.mercury.demand.persistence.model.*;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService  implements UserDetailsService{
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private LoginDao ld;
		
	public LoginDao getLd() {
		return ld;
	}
	public void setLd(LoginDao ld) {
		this.ld = ld;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails user = null;  
		try {
			Login login = ld.getLoginByUsername(username);
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(login.getAuthority()));
			user = new User(
					login.getUsername(),
					login.getPassword(),
					true,
					true,
					true,
					true,
					authorities 
			);
		} catch (Exception e) {
			logger.error("Error in retrieving user" + e.getMessage());
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		return user;
	}		  
}
