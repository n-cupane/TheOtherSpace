package com.theotherspace.dao;

import com.theotherspace.dao.jdbc.JDBCDaoFactory;

public abstract class DaoFactory {
	
	private static DaoFactory instance;
	
	public static DaoFactory getDaoFactory() {
		
		if (instance == null) {
			instance = new JDBCDaoFactory();
		}
		return instance;
	}
	
//	Aggiungere getter astratti dei Dao specifici

	public abstract UserDao getUserDao();
	public abstract TicketDao getTicketDao();
	public abstract TheaterDao getTheaterDao();
    public abstract ScreeningDao getScreeningDao();
	public abstract ReviewDao getReviewDao();
	public abstract MovieDao getMovieDao();
	public abstract GenreDao getGenreDao();
	public abstract FavoriteDao getFavoriteDao();
	

}
