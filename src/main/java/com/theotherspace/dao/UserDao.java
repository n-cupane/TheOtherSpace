package com.theotherspace.dao;

import com.theotherspace.model.User;

public interface UserDao extends GeneralDao<User> {
	
	abstract public User findUserByEmail(String email);

}
