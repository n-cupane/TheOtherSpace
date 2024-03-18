package com.theotherspace.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import com.theotherspace.dao.UserDao;
import com.theotherspace.model.User;

public class JDBCUserDao implements UserDao {
	
	private static JDBCUserDao instance;
	
	private JDBCUserDao() {}
	
	public static JDBCUserDao getInstance() {
		if (instance == null) {
			instance = new JDBCUserDao();
		}
		return instance;
	}

	@Override
	public User findById(long id) throws SQLException {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			Statement s = c.createStatement();
			
			String query = "select * from user where id =" + id;
			
			ResultSet rs = s.executeQuery(query);
			
			if (rs.next()) {
				User u = new User();
				
				long userId = rs.getLong("id");
				String username = rs.getString("username");
				String firstName = rs.getString("name");
				String lastName = rs.getString("surname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				LocalDate dob = rs.getDate("dob").toLocalDate(); 
				
				u.setId(userId);
				u.setUsername(username);
				u.setFirstName(firstName);
				u.setLastName(lastName);
				u.setEmail(email);
				u.setPassword(password);
				u.setDateOfBirth(dob);
				
				return u;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
