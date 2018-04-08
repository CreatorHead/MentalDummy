package com.nullthinker.mentaldummy.model.dao;

import javax.validation.Valid;


import com.nullthinker.mentaldummy.beans.User;

/**
 * @author Aatish
 *	Data Access Layer for the Application
 */

public interface DAO {
	/**
	 * Used to create profile in the Application
	 * @param user
	 * @return
	 */
	public User createProfile(@Valid User user);
	
	/**
	 * Used to Login in the Application
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * Used to get the User by email
	 * @param user
	 * @return
	 */
	public User findByEmail(User user);
}
