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

import com.theotherspace.dao.ReviewDao;
import com.theotherspace.model.Review;
import com.theotherspace.model.User;


//Creato il JDBCReviewDao

public class JDBCReviewDao implements ReviewDao{
	
	private static JDBCReviewDao instance;
	
	private JDBCReviewDao() {}
	
	public static JDBCReviewDao getInstance() {
		if (instance == null) {
			instance = new JDBCReviewDao();
		}
		return instance;
	}

	
	// Aggiunto il metodo findById
	@Override
	public Review findById(long id) {
		try (Connection c = JDBCDaoFactory.getConnection())
		{
			Statement s = c.createStatement();
			
			String query = "select * from review where id =" + id;
			
			ResultSet rs = s.executeQuery(query);
			
			if(rs.next()) {
				Review r = new Review();
				
				Long reviewId = rs.getLong("id");
				Long user_Id = rs.getLong("user_id");
				Long movie_Id = rs.getLong("movie_id");
				String text = rs.getString("text");
				int rating = rs.getInt("rating");
				LocalDate date = rs.getDate("date").toLocalDate();
				
				r.setId(reviewId);
				r.setUserId(user_Id);
				r.setMovieId(movie_Id);
				r.setText(text);
				r.setRating(rating);
				r.setDate(date);
				
				return r;
				
				
			} else {
				return null;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Review> findAll() {
		
		List<Review> reviewsToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) 
		{
			Statement s = c.createStatement();
			
			String query = "select * from review";
			
			ResultSet rs = s.executeQuery(query);
			
			while(rs.next())
			{
				Review r = new Review();
				
				Long reviewId = rs.getLong("id");
				Long user_Id = rs.getLong("user_id");
				Long movie_Id = rs.getLong("movie_id");
				String text = rs.getString("text");
				int rating = rs.getInt("rating");
				LocalDate date = rs.getDate("date").toLocalDate();
				
				r.setId(reviewId);
				r.setUserId(user_Id);
				r.setMovieId(movie_Id);
				r.setText(text);
				r.setRating(rating);
				r.setDate(date);
				
				reviewsToReturn.add(r);
			}
			
			return (reviewsToReturn.size()>0) ? reviewsToReturn : null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(Review rv) {
	    try (Connection c = JDBCDaoFactory.getConnection()) {
	        PreparedStatement ps = c.prepareStatement("update review set text = ?, rating = ? where id = ?");
	        ps.setString(1, rv.getText());
	        ps.setInt(2, rv.getRating());
	        ps.setLong(3, rv.getId());
	        
	        ps.executeUpdate();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void delete(long id) {
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("delete from review where id = ?");
			
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void insert(Review rv) {
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement(
					"insert into review(user_id, movie_id, text, rating, date) values(?, ?, ?, ?, ?)");
			ps.setLong(1, rv.getUserId());
			ps.setLong(2, rv.getMovieId());
			ps.setString(3, rv.getText());
			ps.setInt(4, rv.getRating());
			ps.setDate(5, Date.valueOf(rv.getDate()));
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
}
