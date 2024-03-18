package com.theotherspace.dao;

import java.sql.SQLException;

import com.theotherspace.model.User;

public interface UserDao extends GeneralDao<User> {
	
	abstract public User findUserByEmail(String email);

}
