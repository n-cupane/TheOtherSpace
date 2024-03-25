package com.theotherspace.dao.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.theotherspace.dao.DaoFactory;
import com.theotherspace.dao.GenreDao;
import com.theotherspace.dao.MovieDao;
import com.theotherspace.dao.ReviewDao;
import com.theotherspace.dao.ScreeningDao;
import com.theotherspace.dao.TheaterDao;
import com.theotherspace.dao.TicketDao;
import com.theotherspace.dao.UserDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JPADaoFactory extends DaoFactory {

	
	protected static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("TheOtherSpace").createEntityManager();
	}
	
	@Override
	public UserDao getUserDao() {
		return JPAUserDao.getInstance();
	}
	
	@Override
	public TicketDao getTicketDao() {
		return JPATicketDao.getInstance();
	}

	@Override
	public MovieDao getMovieDao() {
		return JPAMovieDao.getInstance();
	}
	
	@Override
	public ReviewDao getReviewDao() {
		return JPAReviewDao.getInstance();
	}

	@Override
	public ScreeningDao getScreeningDao() {
		return JPAScreeningDao.getInstance();
	}

	@Override
	public TheaterDao getTheaterDao() {
		return JPATheaterDao.getInstance();
	}

	@Override
	public GenreDao getGenreDao() {
		return JPAGenreDao.getInstance();
	}

	@Override
	public FavoriteDao getFavoriteDao() {
		return JPAFavoriteDao.getInstance();
	}
}
