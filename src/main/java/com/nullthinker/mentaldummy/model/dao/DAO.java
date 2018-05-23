package com.nullthinker.mentaldummy.model.dao;

import java.util.List;

import javax.validation.Valid;

import com.nullthinker.mentaldummy.beans.QuestionPaper;
import com.nullthinker.mentaldummy.beans.Subject;
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
	
	/**
	 * Find User by Confirmation Token
	 * @param token
	 * @return
	 */
	public User findByConfirmationToken(String token);
	
	/**
	 * To Update the User
	 * @param user
	 * @return
	 */
	public User updateUser(User user);
	
	/**
	 * To Get the list of subjects and there sub-topics
	 * @return
	 */
	public List<Subject> getSubjects();
	
	/**
	 * To insert questions into the database
	 * @param qsp
	 * @return
	 */
	public boolean insertQuestions(QuestionPaper qsp);
}
