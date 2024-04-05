package com.theotherspace.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public Ticket findById(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from ticket where id = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
			
				Ticket t = new Ticket();
				
				long ticketId = rs.getLong("id");
				long userId = rs.getLong("user_id");
				long screeningId = rs.getLong("showing_id");
				double price = rs.getDouble("price");
				int seat = rs.getInt("seat");
				
				t.setId(ticketId);
				t.setUserId(userId);
				t.setScreeningId(screeningId);
				t.setPrice(price);
				t.setSeat(seat);
				
				return t;
				
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Ticket> findAll() {
		
		List<Ticket> ticketsToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from ticket");
			
			while (rs.next()) {
				
				Ticket t = new Ticket();
				
				long ticketId = rs.getLong("id");
				long userId = rs.getLong("user_id");
				long screeningId = rs.getLong("showing_id");
				double price = rs.getDouble("price");
				int seat = rs.getInt("seat");
				
				t.setId(ticketId);
				t.setUserId(userId);
				t.setScreeningId(screeningId);
				t.setPrice(price);
				t.setSeat(seat);
				
				ticketsToReturn.add(t);
			}
			
			return (ticketsToReturn.size()>0) ? ticketsToReturn : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(Ticket t) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("update ticket set user_id = ?," + 
					"showing_id = ?," +
					"price = ?," +
					"seat = ?" +
					" where id = ?");
			
			ps.setLong(1, t.getUserId());
			ps.setLong(2, t.getScreeningId());
			ps.setDouble(3, t.getPrice());
			ps.setInt(4, t.getSeat());
			ps.setLong(5, t.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("delete from ticket where id = ?");
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void insert(Ticket t) {

		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement(
					"insert into ticket(user_id, showing_id, price, seat) values(?, ?, ?, ?)");
			ps.setLong(1, t.getUserId());
			ps.setLong(2, t.getScreeningId());
			ps.setDouble(3, t.getPrice());
			ps.setInt(4, t.getSeat());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Ticket> findAllTicketsOfUser(long userId) {
		
		List<Ticket> ticketsToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from ticket where user_id = ?");
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Ticket t = new Ticket();
				long ticketId = rs.getLong("id");
				long screeningId = rs.getLong("showing_id");
				double price = rs.getDouble("price");
				int seat = rs.getInt("seat");
				
				t.setId(ticketId);
				t.setUserId(userId);
				t.setScreeningId(screeningId);
				t.setPrice(price);
				t.setSeat(seat);
				
				ticketsToReturn.add(t);
				
			}
			
			return (ticketsToReturn.size()>0) ? ticketsToReturn : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Ticket> findAllTicketsForScreening(long screeningId) {
		
		List<Ticket> ticketsToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from ticket where showing_id = ?");
			ps.setLong(1, screeningId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Ticket t = new Ticket();
				long ticketId = rs.getLong("id");
				long userId = rs.getLong("user_id");
				double price = rs.getDouble("price");
				int seat = rs.getInt("seat");
				
				t.setId(ticketId);
				t.setUserId(userId);
				t.setScreeningId(screeningId);
				t.setPrice(price);
				t.setSeat(seat);
				
				ticketsToReturn.add(t);
				
			}
			
			return (ticketsToReturn.size()>0) ? ticketsToReturn : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Ticket> findAllTicketsForMovie(long movieId) {
		
		List<Ticket> ticketsToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from ticket " +
					"join showing on showing_id = showing.id " +
					"join movie on movie_id = movie.id " + 
					"where movie.id = ?");
			ps.setLong(1, movieId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Ticket t = new Ticket();
				
				long ticketId = rs.getLong("id");
				long userId = rs.getLong("user_id");
				long screeningId = rs.getLong("showing_id");
				double price = rs.getDouble("price");
				int seat = rs.getInt("seat");
				
				t.setId(ticketId);
				t.setUserId(userId);
				t.setScreeningId(screeningId);
				t.setPrice(price);
				t.setSeat(seat);
				
				ticketsToReturn.add(t);
				
			}
			
			return (ticketsToReturn.size()>0) ? ticketsToReturn : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Ticket> findAllTicketsOfUserOrderByDate(long userId) {
		
		List<Ticket> ticketsToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from ticket JOIN SHOWING ON SHOWING_ID=SHOWING.ID where user_id = ? ORDER BY SHOWING.DATE_TIME");
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Ticket t = new Ticket();
				long ticketId = rs.getLong("id");
				long screeningId = rs.getLong("showing_id");
				double price = rs.getDouble("price");
				int seat = rs.getInt("seat");
				
				t.setId(ticketId);
				t.setUserId(userId);
				t.setScreeningId(screeningId);
				t.setPrice(price);
				t.setSeat(seat);
				
				ticketsToReturn.add(t);
				
			}
			
			return (ticketsToReturn.size()>0) ? ticketsToReturn : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
