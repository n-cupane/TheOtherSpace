package com.theotherspace.dao.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.theotherspace.dao.TicketDao;
import com.theotherspace.model.Ticket;

public class JDBCTicketDao implements TicketDao {
	
	private static JDBCTicketDao instance;
	
	private JDBCTicketDao() {}
	
	public static JDBCTicketDao getInstance() {
		if (instance == null) {
			instance = new JDBCTicketDao();
		}
		return instance;
	}

	@Override
	public Ticket findById(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ticket t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Ticket t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ticket> findAllTicketsOfUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
