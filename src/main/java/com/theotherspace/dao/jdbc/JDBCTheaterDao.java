package com.theotherspace.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.theotherspace.dao.TheaterDao;
import com.theotherspace.model.Review;
import com.theotherspace.model.Theater;

public class JDBCTheaterDao implements TheaterDao{
	
	private static JDBCTheaterDao instance;
	
	private JDBCTheaterDao() {}
	
	public static JDBCTheaterDao getInstance() {
		if (instance == null) {
			instance = new JDBCTheaterDao();
		}
		return instance;
	}

	@Override
	public Theater findById(long id) {
		try (Connection c = JDBCDaoFactory.getConnection())
		{
			Statement s = c.createStatement();
			
			String query = "select * from room where id =" + id;
			
			ResultSet rs = s.executeQuery(query);
			
			if(rs.next()) {
				Theater tt = new Theater();
				
				Long reviewId = rs.getLong("id");
				int seats = rs.getInt("seats");
				int room_number = rs.getInt("room_number");
				
				tt.setId(reviewId);
				tt.setNumber(room_number);
				tt.setSeats(seats);
				
				return tt;
				
				
			} else {
				return null;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Theater> findAll() {
		List<Theater> theaterToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) 
		{
			Statement s = c.createStatement();
			
			String query = "select * from room";
			
			ResultSet rs = s.executeQuery(query);
			
			while(rs.next())
			{
				Theater tt = new Theater();
				
				Long reviewId = rs.getLong("id");
				int seats = rs.getInt("seats");
				int room_number = rs.getInt("room_number");
				
				tt.setId(reviewId);
				tt.setNumber(room_number);
				tt.setSeats(seats);
				
				
				theaterToReturn.add(tt);
			}
			
			return (theaterToReturn.size()>0) ? theaterToReturn : null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(Theater tt) {
		try (Connection c = JDBCDaoFactory.getConnection()) {
	        PreparedStatement ps = c.prepareStatement("update room set seats = ?, room_number = ? where id = ?");
	        ps.setInt(1, tt.getSeats());
	        ps.setInt(2, tt.getNumber());
	        ps.setLong(3, tt.getId());
	        
	        ps.executeUpdate();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public void delete(long id) {
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("delete from room where id = ?");
			
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void insert(Theater tt) {
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement(
					"insert into room(seats, room_number) values(?, ?)");
			
			ps.setLong(1, tt.getSeats());
			ps.setLong(2, tt.getNumber());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	

	

	

}
