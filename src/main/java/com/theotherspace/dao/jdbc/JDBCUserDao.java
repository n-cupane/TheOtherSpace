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
	public User findById(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from user where id = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
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
		
		List<User> usersToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			Statement s = c.createStatement();
			
			String query = "select * from user";
			
			ResultSet rs = s.executeQuery(query);
			
			while (rs.next()) {
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
				
				usersToReturn.add(u);
			}
			
			return (usersToReturn.size()>0) ? usersToReturn : null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(User t) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("update user set username = ?," + 
					"name = ?," +
					"surname = ?," +
					"email = ?," +
					"password = ?," +
					"dob = ?" +
					" where id = ?");
			
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getFirstName());
			ps.setString(3, t.getLastName());
			ps.setString(4, t.getEmail());
			ps.setString(5, t.getPassword());
			ps.setDate(6, Date.valueOf(t.getDateOfBirth()));
			ps.setLong(7, t.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(long id) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("delete from user where id = ?");
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(User t) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement(
					"insert into user(username, name, surname, email, password, dob) values(?, ?, ?, ?, ?, ?)");
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getFirstName());
			ps.setString(3, t.getLastName());
			ps.setString(4, t.getEmail());
			ps.setString(5, t.getPassword());
			ps.setDate(6, Date.valueOf(t.getDateOfBirth()));
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User findUserByEmail(String email) {
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from user where email = ?");
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User u = new User();
				
				long userId = rs.getLong("id");
				String username = rs.getString("username");
				String firstName = rs.getString("name");
				String lastName = rs.getString("surname");
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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> findAllUsersForScreening(long screeningId) {
		
		List<User> usersToReturn = new ArrayList<>();
		
		try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from user "
					+ "join ticket on user.id = user_id "
					+ "join showing on showing_id = showing.id "
					+ "where showing.id = ?");
			ps.setLong(1, screeningId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
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
				
				usersToReturn.add(u);
			}
			
			return (usersToReturn.size()>0) ? usersToReturn : null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public User findUserByUsername(String username) {
try (Connection c = JDBCDaoFactory.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement("select * from user where username = ?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User u = new User();
				
				long userId = rs.getLong("id");
				String email = rs.getString("email");
				String firstName = rs.getString("name");
				String lastName = rs.getString("surname");
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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
