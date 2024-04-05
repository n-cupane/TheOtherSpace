package com.theotherspace.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.theotherspace.dao.MovieDao;
import com.theotherspace.model.Genre;
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
				String imageUrl = rs.getString("img_url");
				
				m.setId(movieId);
				m.setGenreId(genreId);
				m.setTitle(title);
				m.setDuration(duration);
				m.setDescription(description);
				m.setOver18(over18);
				m.setImageUrl(imageUrl);
				
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
		
		List<Movie> moviesToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			Statement s = c.createStatement();
			
			String query = "select * from movie";
			
			ResultSet rs = s.executeQuery(query);
			
			while (rs.next()) {
				
				Movie m = new Movie();
				
				long movieId = rs.getLong("id");
				long genreId = rs.getLong("genre_id");
				String title = rs.getString("title");
				int duration = rs.getInt("duration");
				String description = rs.getString("description");
				boolean over18 = rs.getBoolean("for_adults");
				String imageUrl = rs.getString("img_url");
				
				m.setId(movieId);
				m.setGenreId(genreId);
				m.setTitle(title);
				m.setDuration(duration);
				m.setDescription(description);
				m.setOver18(over18);
				m.setImageUrl(imageUrl);
				
				moviesToReturn.add(m);				
			}
			
			return (moviesToReturn.size()>0) ? moviesToReturn : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(Movie t) {
	
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("update movie set genre_id = ?, "
					+ "title = ?, "
					+ "duration = ?, "
					+ "description = ?, "
					+ "for_adults = ?,"
					+ "img_url = ? "
					+ "where id = ?");
			
			ps.setLong(1, t.getGenreId());
			ps.setString(2, t.getTitle());
			ps.setInt(3, t.getDuration());
			ps.setString(4, t.getDescription());
			ps.setBoolean(5, t.isOver18());
			ps.setString(6, t.getImageUrl());
			ps.setLong(7, t.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("delete from movie where id = ?");
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void insert(Movie t) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("insert into movie("
					+ "genre_id, title, duration, description, for_adults, img_url) "
					+ "values (?, ?, ?, ?, ?, ?)");
			
			ps.setLong(1, t.getGenreId());
			ps.setString(2, t.getTitle());
			ps.setInt(3, t.getDuration());
			ps.setString(4, t.getDescription());
			ps.setBoolean(5, t.isOver18());
			ps.setString(6, t.getImageUrl());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Movie findMovieByTitle(String title) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from movie where title = ?");
			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Movie m = new Movie();
				
				long movieId = rs.getLong("id");
				long genreId = rs.getLong("genre_id");
				String movieTitle = rs.getString("title");
				int duration = rs.getInt("duration");
				String description = rs.getString("description");
				boolean over18 = rs.getBoolean("for_adults");
				String imageUrl = rs.getString("img_url");
				
				m.setId(movieId);
				m.setGenreId(genreId);
				m.setTitle(movieTitle);
				m.setDuration(duration);
				m.setDescription(description);
				m.setOver18(over18);
				m.setImageUrl(imageUrl);
				
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
	public List<Movie> findAllMoviesForGenre(Genre g) {

		List<Movie> moviesToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			Statement s = c.createStatement();
			
			String query = "select * from movie join genre on genre_id = genre.id where genre_id = " + g.getId();
			
			ResultSet rs = s.executeQuery(query);
			
			while (rs.next()) {
				
				Movie m = new Movie();
				
				long movieId = rs.getLong("id");
				long genreId = rs.getLong("genre_id");
				String title = rs.getString("title");
				int duration = rs.getInt("duration");
				String description = rs.getString("description");
				boolean over18 = rs.getBoolean("for_adults");
				String imageUrl = rs.getString("img_url");
				
				m.setId(movieId);
				m.setGenreId(genreId);
				m.setTitle(title);
				m.setDuration(duration);
				m.setDescription(description);
				m.setOver18(over18);
				m.setImageUrl(imageUrl);
				
				moviesToReturn.add(m);				
			}
			
			return (moviesToReturn.size()>0) ? moviesToReturn : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
