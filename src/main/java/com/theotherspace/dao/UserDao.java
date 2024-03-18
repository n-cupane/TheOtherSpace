package com.theotherspace.dao;

import java.sql.SQLException;
import java.util.List;

import com.theotherspace.model.User;

public interface UserDao extends GeneralDao<User> {
	
	abstract public User findUserByEmail(String email);
	
	abstract public List<User> findAllUsersForScreening(long screeningId);

}
