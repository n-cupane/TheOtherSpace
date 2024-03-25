package com.theotherspace.dao.jpa;

import java.util.List;

import com.theotherspace.dao.MovieDao;
import com.theotherspace.model.Genre;
import com.theotherspace.model.Movie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JPAMovieDao implements MovieDao{
	
	private static JPAMovieDao instance;
	
	private JPAMovieDao() {};
	
	public static JPAMovieDao getInstance() {
		if(instance == null) {
			instance = new JPAMovieDao();
		}
		return instance;
	}

	@Override
	public Movie findById(long id) {
		EntityManager em = JPADaoFactory.getEntityManager();
		return em.find(Movie.class,id);
	}

	@Override
	public List<Movie> findAll() {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select m from Movie m");
		return q.getResultList();
	}

	@Override
	public void update(Movie t) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.merge(t);
		tr.commit();
	}

	@Override
	public void delete(long id) {
		Movie movie = this.findById(id);
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(movie);
		tr.commit();
	}

	@Override
	public void insert(Movie t) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(t);
		tr.commit();
	}

	@Override
	public Movie findMovieByTitle(String title) {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select m from Movie m where movie.title=:x");
		q.setParameter("x", title);
		return (Movie)q.getSingleResult();
	}

	@Override
	public List<Movie> findAllMoviesForGenre(Genre g) {
	    EntityManager em = JPADaoFactory.getEntityManager();
	    Query q = em.createQuery("SELECT m FROM Movie m JOIN m.genre genre WHERE genre = :genre", Movie.class);
	    q.setParameter("genre", g);
	    return q.getResultList();
	}

}
