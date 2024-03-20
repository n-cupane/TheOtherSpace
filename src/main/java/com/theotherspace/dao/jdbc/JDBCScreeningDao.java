package com.theotherspace.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.theotherspace.dao.ScreeningDao;
import com.theotherspace.model.Review;
import com.theotherspace.model.Screening;

public class JDBCScreeningDao implements ScreeningDao{
	
private static JDBCScreeningDao instance;
	
	private JDBCScreeningDao() {}
	
	public static JDBCScreeningDao getInstance() {
		if (instance == null) {
			instance = new JDBCScreeningDao();
		}
		return instance;
	}

	@Override
	public Screening findById(long id) {
		try (Connection c = JDBCDaoFactory.getConnection())
		{
			Statement s = c.createStatement();
			
			String query = "select * from showing where id =" + id;
			
			ResultSet rs = s.executeQuery(query);
			
			if(rs.next()) {
				Screening sc = new Screening();
				
				Long screeningId = rs.getLong("id");
				Long theaterId = rs.getLong("room_id");
				Long movie_Id = rs.getLong("movie_id");
				LocalDate date = rs.getDate("date_time").toLocalDate();
				LocalTime time = rs.getTime("date_time").toLocalTime();
				
				LocalDateTime dateTime = date.atTime(time);
				
			
				sc.setId(screeningId);
				sc.setTheaterId(theaterId);
				sc.setMovieId(movie_Id);
				sc.setDateTime(dateTime);
				
				return sc;
				
				
			} else {
				return null;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Screening> findAll() {
		List<Screening> screeningToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) 
		{
			Statement s = c.createStatement();
			
			String query = "select * from showing";
			
			ResultSet rs = s.executeQuery(query);
			
			while(rs.next())
			{
				Screening sc = new Screening();
				
				Long screeningId = rs.getLong("id");
				Long theaterId = rs.getLong("room_id");
				Long movie_Id = rs.getLong("movie_id");
				LocalDate date = rs.getDate("date_time").toLocalDate();
				LocalTime time = rs.getTime("date_time").toLocalTime();
				
				LocalDateTime dateTime = date.atTime(time);
				
			
				sc.setId(screeningId);
				sc.setTheaterId(theaterId);
				sc.setMovieId(movie_Id);
				sc.setDateTime(dateTime);
				
				screeningToReturn.add(sc);
			}
			
			return (screeningToReturn.size()>0) ? screeningToReturn : null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(Screening sc) {
	    try (Connection c = JDBCDaoFactory.getConnection()) {
	    	PreparedStatement ps = c.prepareStatement("update showing set room_id = ?, movie_id = ?, date_time = ? where id = ?");
	        
	        ps.setLong(1, sc.getTheaterId());
	        ps.setLong(2, sc.getMovieId());
	        ps.setObject(3, sc.getDateTime());
	        ps.setLong(4, sc.getId());
	        
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void delete(long id) {
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("delete from screening where id = ?");
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void insert(Screening sc) {
	    String query = "INSERT INTO screening (room_id, movie_id, date_time) VALUES (?, ?, ?)";
	    try (Connection c = JDBCDaoFactory.getConnection();
	         PreparedStatement ps = c.prepareStatement(query)) {

	        ps.setLong(1, sc.getTheaterId());
	        ps.setLong(2, sc.getMovieId());
	        ps.setObject(3, sc.getDateTime());

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public List<Screening> findAllScreeningsByMovieId(long movieId) {

		List<Screening> screeningToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from showing where movie_id = ?");
			ps.setLong(1, movieId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Screening sc = new Screening();
				
				Long screeningId = rs.getLong("id");
				Long theaterId = rs.getLong("room_id");
				Long movie_Id = rs.getLong("movie_id");
				LocalDate date = rs.getDate("date_time").toLocalDate();
				LocalTime time = rs.getTime("date_time").toLocalTime();
				
				LocalDateTime dateTime = date.atTime(time);
				
			
				sc.setId(screeningId);
				sc.setTheaterId(theaterId);
				sc.setMovieId(movie_Id);
				sc.setDateTime(dateTime);
				
				screeningToReturn.add(sc);
			}
			
			return (screeningToReturn.size()>0) ? screeningToReturn : null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
