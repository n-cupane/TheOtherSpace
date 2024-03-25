package com.theotherspace.dao.jpa;

import java.util.List;

import com.theotherspace.dao.GenreDao;
import com.theotherspace.model.Genre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JPAGenreDao implements GenreDao{
	

	
	private static JPAGenreDao instance;
	
	private JPAGenreDao() {};
	
	public static JPAGenreDao getInstance() {
		if (instance == null) {
			instance = new JPAGenreDao();
		}
		return instance;
	}
	
	

	@Override
	public Genre findById(long id) {
		EntityManager em = JPADaoFactory.getEntityManager();
		return em.find(Genre.class,id);
	}

	@Override
	public List<Genre> findAll() {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select g from Genre g");
		return q.getResultList();
	}

	@Override
	public void update(Genre t) {
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
	    Genre genre = this.findById(id);
	    tr.begin();
	    em.remove(genre);
	    tr.commit();
		
	}

	@Override
	public void insert(Genre t) {
		EntityManager em = JPADaoFactory.getEntityManager();
	    EntityTransaction tr = em.getTransaction();
	    tr.begin();
	    em.persist(t);
	    tr.commit();
		
	}

	@Override
	public Genre findGenreByName(String genreName) {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select g from Genre g where g.name =:x");
		q.setParameter("x", genreName);
		return (Genre) q.getSingleResult();
		
	}

}
