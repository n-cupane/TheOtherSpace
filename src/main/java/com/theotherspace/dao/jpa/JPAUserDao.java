package com.theotherspace.dao.jpa;

import java.util.List;

import com.theotherspace.dao.UserDao;
import com.theotherspace.model.Screening;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JPAUserDao implements UserDao {
	
	private static JPAUserDao instance;
	
	private JPAUserDao() {}
	
	public static JPAUserDao getInstance() {
		if (instance == null) {
			instance = new JPAUserDao();
		}
		return instance;
	}

	@Override
	public User findById(long id) {
		EntityManager em = JPADaoFactory.getEntityManager();
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select u from user u");
		return q.getResultList();
	}

	@Override
	public void update(User t) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.merge(t);
		tr.commit();
	}

	@Override
	public void delete(long id) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(this.findById(id));
		tr.commit(); 
	}

	@Override
	public void insert(User t) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(t);
		tr.commit();
	}

	@Override
	public User findUserByEmail(String email) {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select u from user u where u.email = :x");
		q.setParameter("x", email);
		try {
			return (User) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<User> findAllUsersForScreening(long screeningId) {
		EntityManager em = JPADaoFactory.getEntityManager();
		Screening s = BusinessLogic.findScreeningById(screeningId);
		Query q = em.createQuery("select u from screening s join s.user u where s = :x");
		q.setParameter("x", s);
		return (List<User>) q.getResultList();
	}

	@Override
	public User findUserByUsername(String username) {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select u from user u where u.username = :x");
		q.setParameter("x", username);
		try {
			return (User) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	

}
