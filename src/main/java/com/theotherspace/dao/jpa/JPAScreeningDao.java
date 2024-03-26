package com.theotherspace.dao.jpa;

import java.util.List;

import com.theotherspace.dao.ScreeningDao;
import com.theotherspace.model.Movie;
import com.theotherspace.model.Screening;
import com.theotherspace.model.Theater;
import com.theotherspace.utilities.BusinessLogic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JPAScreeningDao implements ScreeningDao{
	
	private static JPAScreeningDao instance;
	
	private JPAScreeningDao() {}
	
	public static JPAScreeningDao getInstance() {
		if (instance == null) {
			instance = new JPAScreeningDao();
		}
		return instance;
	}

	@Override
	public Screening findById(long id) {
		EntityManager em = JPADaoFactory.getEntityManager();
		return em.find(Screening.class, id);
	}

	@Override
	public List<Screening> findAll() {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select s from showing s");
		return q.getResultList();
	}

	@Override
	public void update(Screening t) {
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
	public void insert(Screening t) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(t);
		tr.commit();
	}

	@Override
	public List<Screening> findAllScreeningsByMovieId(long movieId) {
		EntityManager em = JPADaoFactory.getEntityManager();
		Movie m = BusinessLogic.findMovieById(movieId);
		Query q = em.createQuery("select s from showing s join s.movie m where m = :x");
		q.setParameter("x", m);
		return (List<Screening>) q.getResultList();
	}

}
