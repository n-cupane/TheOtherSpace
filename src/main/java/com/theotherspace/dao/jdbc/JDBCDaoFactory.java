package com.theotherspace.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.theotherspace.dao.DaoFactory;
import com.theotherspace.dao.MovieDao;
import com.theotherspace.dao.TicketDao;
import com.theotherspace.dao.UserDao;

public class JDBCDaoFactory extends DaoFactory {

	static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String dbUrl = "jdbc:mysql://localhost:3306/theotherspace";
		String user = "root";
		String password = "root";
		return DriverManager.getConnection(dbUrl, user, password);
	}

	@Override
	public UserDao getUserDao() {
		return JDBCUserDao.getInstance();
	}
	
	@Override
	public TicketDao getTicketDao() {
		return JDBCTicketDao.getInstance();
	}

	@Override
	public MovieDao getMovieDao() {
		return JDBCMovieDao.getInstance();
	}

	
	
}
