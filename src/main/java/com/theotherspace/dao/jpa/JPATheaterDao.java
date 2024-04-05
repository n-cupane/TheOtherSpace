package com.theotherspace.dao.jpa;

import java.util.List;

import com.theotherspace.dao.TheaterDao;
import com.theotherspace.model.Theater;
import com.theotherspace.model.Ticket;
import com.theotherspace.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JPATheaterDao implements TheaterDao {
	
	private static JPATheaterDao instance;
	
	private JPATheaterDao() {}
	
	public static JPATheaterDao getInstance() {
		if (instance == null) {
			instance = new JPATheaterDao();
		}
		return instance;
	}

	@Override
	public Theater findById(long id) {
		EntityManager em = JPADaoFactory.getEntityManager();
		return em.find(Theater.class, id);
	}

	@Override
	public List<Theater> findAll() {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select r from room r");
		return q.getResultList();
	}

	@Override
	public void update(Theater t) {
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
	public void insert(Theater t) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(t);
		tr.commit();
	}

	@Override
	public Theater findTheaterByNumber(int theaterNumber) {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select r from room r where r.number = :x");
		q.setParameter("x", theaterNumber);
		try {
			return (Theater) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
