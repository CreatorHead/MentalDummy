package com.nullthinker.mentaldummy.model.dao;




import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nullthinker.mentaldummy.beans.User;

@Repository
public class MentalDummyHibernateImpl implements MentalDummyDAO {
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
			session.close();
		}
		return user;
	}

}
