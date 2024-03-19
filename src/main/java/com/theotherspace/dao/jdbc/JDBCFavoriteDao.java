package com.theotherspace.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.theotherspace.dao.FavoriteDao;
import com.theotherspace.dao.GenreDao;
import com.theotherspace.model.Favorite;
import com.theotherspace.model.Genre;

public class JDBCFavoriteDao implements FavoriteDao {
	
	private static JDBCFavoriteDao instance;
	private JDBCFavoriteDao() {};
	public static JDBCFavoriteDao getInstance() {
		if (instance == null) {
			instance = new JDBCFavoriteDao();
		}
		return instance;
	}
	@Override
	public Favorite findById(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from user_movie where id = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Favorite f = new Favorite();
				
				long favoriteId = rs.getLong("id");
				long userId = rs.getLong("user_id");
				long movieId = rs.getLong("movie_id");
				
				f.setId(favoriteId);
				f.setUserId(userId);
				f.setMovieId(movieId);
				
				return f;
				
			} else {
				return null;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Favorite> findAll() {
		
		List<Favorite> favoritesToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			Statement s = c.createStatement();
			String query = "select * from user_movie";
			ResultSet rs = s.executeQuery(query);
			
			while (rs.next()) {
				Favorite f = new Favorite();
				
				long favoriteId = rs.getLong("id");
				long userId = rs.getLong("user_id");
				long movieId = rs.getLong("movie_id");
				
				f.setId(favoriteId);
				f.setUserId(userId);
				f.setMovieId(movieId);
				
				favoritesToReturn.add(f);
			}
			
			return (favoritesToReturn.size()>0) ? favoritesToReturn : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void update(Favorite t) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("update user_movie set "
					+ "user_id = ?, "
					+ "movie_id = ? "
					+ "where id = ?");
			
			ps.setLong(1, t.getUserId());
			ps.setLong(2, t.getMovieId());
			ps.setLong(3, t.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void delete(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("delete from user_movie where id = ?");
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void insert(Favorite t) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("insert into user_movie"
					+ "(user_id, movie_id) "
					+ "values(?, ?)");
			ps.setLong(1, t.getUserId());
			ps.setLong(2, t.getMovieId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
