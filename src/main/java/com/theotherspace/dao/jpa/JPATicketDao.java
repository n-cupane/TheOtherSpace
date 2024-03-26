package com.theotherspace.dao.jpa;

import java.util.List;

import com.theotherspace.dao.TicketDao;
import com.theotherspace.model.Movie;
import com.theotherspace.model.Screening;
import com.theotherspace.model.Ticket;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JPATicketDao implements TicketDao{
	
	private static JPATicketDao instance;

	private JPATicketDao() {}
	
	public static JPATicketDao getInstance() {
		if (instance == null) {
			instance = new JPATicketDao();
		}
		return instance;
	}

	@Override
	public Ticket findById(long id) {
		EntityManager em = JPADaoFactory.getEntityManager();
		return em.find(Ticket.class, id);
	}

	@Override
	public List<Ticket> findAll() {
		EntityManager em = JPADaoFactory.getEntityManager();
		Query q = em.createQuery("select t from ticket t");
		return q.getResultList();
	}

	@Override
	public void update(Ticket t) {
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
	public void insert(Ticket t) {
		EntityManager em = JPADaoFactory.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(t);
		tr.commit();
		
	}

	@Override
	public List<Ticket> findAllTicketsOfUser(long userId) {
		EntityManager em = JPADaoFactory.getEntityManager();
		User u = BusinessLogic.findUserById(userId);
		Query q = em.createQuery("select t from ticket t where t.user = :x");
		q.setParameter("x", u);
		return (List<Ticket>) q.getResultList();
	}

	@Override
	public List<Ticket> findAllTicketsForScreening(long screeningId) {
		EntityManager em = JPADaoFactory.getEntityManager();
		Screening s = BusinessLogic.findScreeningById(screeningId);
		Query q = em.createQuery("select t from ticket t join t.screening s where s = :x");
		q.setParameter("x", s);
		return (List<Ticket>) q.getResultList();
	}

	@Override
	public List<Ticket> findAllTicketsForMovie(long movieId) {
		EntityManager em = JPADaoFactory.getEntityManager();
		Movie m = BusinessLogic.findMovieById(movieId);
		Query q = em.createQuery("select t from ticket t join t.movie m where m = :x");
		q.setParameter("x", m);
		return (List<Ticket>) q.getResultList();
	}

	@Override
	public List<Ticket> findAllTicketsOfUserOrderByDate(long userId) {
	    EntityManager em = JPADaoFactory.getEntityManager();
	    User u = BusinessLogic.findUserById(userId);
	    
	    Query q = em.createQuery("SELECT t FROM ticket t JOIN FETCH t.screening s JOIN t.user u WHERE u = :user ORDER BY s.dateTime");
	    q.setParameter("user", u);
	    
	    return q.getResultList();
	}

}
