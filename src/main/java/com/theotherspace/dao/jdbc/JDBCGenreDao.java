package com.theotherspace.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.theotherspace.dao.GenreDao;
import com.theotherspace.model.Genre;

public class JDBCGenreDao implements GenreDao {
	
	private static JDBCGenreDao instance;
	
	private JDBCGenreDao() {}
	
	public static JDBCGenreDao getInstance() {
		if (instance == null) {
			instance = new JDBCGenreDao();
		}
		return instance;
	}

	@Override
	public Genre findById(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from genre where id = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Genre g = new Genre();
				
				long genreId = rs.getLong("id");
				String genreName = rs.getString("name");
				
				g.setId(genreId);
				g.setName(genreName);
				
				return g;
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Genre> findAll() {
		
		List<Genre> genresToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			Statement s = c.createStatement();
			String query = "select * from genre";
			ResultSet rs = s.executeQuery(query);
			
			while (rs.next()) {
				Genre g = new Genre();
				
				long genreId = rs.getLong("id");
				String genreName = rs.getString("name");
				
				g.setId(genreId);
				g.setName(genreName);
				
				genresToReturn.add(g);
			}
			
			return (genresToReturn.size()>0) ? genresToReturn : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public void update(Genre t) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("update genre "
					+ "set name = ? "
					+ "where id = ?");
			
			ps.setString(1, t.getName());
			ps.setLong(2, t.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("delete from genre where id = ?");
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void insert(Genre t) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("insert into genre"
					+ "(name) "
					+ "values(?)");
			ps.setString(1, t.getName());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Genre findGenreByName(String genreName) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from genre where name = ?");
			ps.setString(1, genreName);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Genre g = new Genre();
				
				long genreId = rs.getLong("id");
				
				g.setId(genreId);
				g.setName(genreName);
				
				return g;
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
