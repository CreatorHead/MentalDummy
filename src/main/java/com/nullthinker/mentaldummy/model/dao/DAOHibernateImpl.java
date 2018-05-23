package com.nullthinker.mentaldummy.model.dao;




import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nullthinker.mentaldummy.beans.QuestionPaper;
import com.nullthinker.mentaldummy.beans.Subject;
import com.nullthinker.mentaldummy.beans.User;
import com.nullthinker.mentaldummy.beans.UserConfirmationDetails;

@Repository
public class DAOHibernateImpl implements DAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * To create a Profile for a new User.
	 */
	@Override
	public User createProfile(User user) {
		
		Session session = null;
		try{ 
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(session != null)
			session.close();
		}
		return user;
	}

	/**
	 * To Login a User. The user has to provide email and password.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User login(User user) {
		
		Session session = null;
		
		try {
			String loginHql="From User user where user.email=:email"
					+ " and user.password=:password";
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			Query query = session.createQuery(loginHql);
			query.setParameter("email", user.getEmail());
			query.setParameter("password", user.getPassword());
			List<User> users = query.list();
			if(users.size() > 0) {
				user = users.get(0);
			}else {
				user = null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			user = null;
		}finally {
			if(session != null)
				session.close();
			
		}
		return user;
	}

	@Override
	public User findByEmail(User user) {
		try {
			Session session = sessionFactory.openSession();
			String loginHql="From User user where user.email=:email";
			session.getTransaction().begin();
			Query query = session.createQuery(loginHql);
			query.setParameter("email", user.getEmail());
			@SuppressWarnings("unchecked")
			List<User> users = query.list();
			if(users.size() > 0) {
				user = users.get(0);
				return user;
			}else {
				user = null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			user = null;
		}
		return null;
	}

	/**
	 * Finds a User By Confirmation Token
	 */
	@Override
	public User findByConfirmationToken(String token) {
		User user = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String qry="From User user, UserConfirmationDetails ucd"
					+ " where user.confirmationDetails=ucd.confirmationId "
					+ " and ucd.confirmationToken=:token";

			Query query = session.createQuery(qry);
			query.setParameter("token", token);
			@SuppressWarnings("unchecked")
			List<Object> users = query.list();
			Iterator<Object> itr = users.iterator();
			while(itr.hasNext()){
				   Object[] obj = (Object[]) itr.next();
				   user = (User)obj[0];  
				}
		} catch (HibernateException e) {
			e.printStackTrace();
			user = null;
		}finally{
			if(session != null) 
				session.close();
			
		}
		return user;
	}

	//TODO be careful while updating the user
	@Override
	public User updateUser(User user) {
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.getTransaction().begin();
			User u = session.get(User.class, user.getUserid());
			UserConfirmationDetails ucd = u.getConfirmationDetails();
			ucd.setEnabled(user.getConfirmationDetails().isEnabled());
			ucd.setConfirmationToken(user.getConfirmationDetails().getConfirmationToken());
			u.setEmail(user.getEmail());
			u.setFirstname(user.getFirstname());
			u.setGender(user.getGender());
			u.setIsActive(user.getIsActive());
			u.setLastname(user.getLastname());
			u.setPassword(user.getPassword());
			u.setRoles(user.getRoles());
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			user = null;
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return user;
	}

	
	@Override
	public List<Subject> getSubjects() {
		try {
			Session session = sessionFactory.openSession();
			String subjectQuery="From Subject";
			session.getTransaction().begin();
			Query query = session.createQuery(subjectQuery);
			@SuppressWarnings("unchecked")
			List<Subject> subjects = query.list();
			return subjects;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insertQuestions(QuestionPaper qsp) {
		try {
			Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			session.save(qsp);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
}
