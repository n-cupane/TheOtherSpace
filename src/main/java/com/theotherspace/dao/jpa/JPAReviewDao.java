package com.theotherspace.dao.jpa;

import java.util.List;

import com.theotherspace.dao.ReviewDao;
import com.theotherspace.model.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JPAReviewDao implements ReviewDao{
	
	private static JPAReviewDao instance;
	
	private JPAReviewDao() {}
	
	public static JPAReviewDao getInstance() {
		if (instance == null) {
			instance = new JPAReviewDao();
		}
		return instance;
	}

	@Override
	public Review findById(long id) {
		EntityManager em = JPADaoFactory.getEntityManager();
		return em.find(Review.class, id);
		
	}

	@Override
	public List<Review> findAll() {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select r from review r");
		return q.getResultList();
	}

	@Override
	public void update(Review t) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.merge(t);
		tr.commit();
		
	}

	@Override
	public void delete(long id) {
		Review review = this.findById(id);
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(review);
		tr.commit();
		
	}

	@Override
	public void insert(Review t) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(t);
		tr.commit();
		
	}

}
