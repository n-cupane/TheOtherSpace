package com.theotherspace.dao;

import java.sql.SQLException;
import java.util.List;

import com.theotherspace.model.Movie;
import com.theotherspace.model.User;

public interface UserDao extends GeneralDao<User> {
	
	abstract public User findUserByEmail(String email);
	
	abstract public List<User> findAllUsersForScreening(long screeningId);
	
	abstract public User findUserByUsername(String username);
	
	abstract public List<Movie> findAllFavoritesOfUser(long userId);
	
	abstract public void updateCardInsert(User user, String newCardCode);
	
	abstract public void updateCardPointInsert(User user, Integer newCardCode);
	
	

}
