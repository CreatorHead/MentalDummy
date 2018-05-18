package com.nullthinker.mentaldummy.mode.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nullthinker.mentaldummy.model.dao.DAOHibernateImpl;

/*
 * For Spring Security UserDetailsSerive
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	DAOHibernateImpl database;
	
	@Override @Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		return null;
	}

}
