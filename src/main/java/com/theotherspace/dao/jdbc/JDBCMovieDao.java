package com.theotherspace.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.theotherspace.dao.MovieDao;
import com.theotherspace.model.Movie;

public class JDBCMovieDao implements MovieDao {
	
	private static JDBCMovieDao instance;
	
	private JDBCMovieDao() {}
	
	public static JDBCMovieDao getInstance() {
		if (instance == null) {
			instance = new JDBCMovieDao();
		}
		return instance;
	}

	@Override
	public Movie findById(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from movie where id = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				Movie m = new Movie();
				
				long movieId = rs.getLong("id");
				long genreId = rs.getLong("genre_id");
				String title = rs.getString("title");
				int duration = rs.getInt("duration");
				String description = rs.getString("description");
				boolean over18 = rs.getBoolean("for_adults");
				
				m.setId(movieId);
				m.setGenreId(genreId);
				m.setTitle(title);
				m.setDuration(duration);
				m.setDescription(description);
				m.setOver18(over18);
				
				return m;
				
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Movie t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Movie t) {
		// TODO Auto-generated method stub
		
	}

}
