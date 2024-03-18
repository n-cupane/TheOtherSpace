package com.theotherspace.utilities;

import java.sql.SQLException;

import com.theotherspace.dao.DaoFactory;
import com.theotherspace.model.User;

public class BusinessLogic {
	
	public static User findUserById(long id) throws SQLException {
		return DaoFactory.getDaoFactory().getUserDao().findById(id);
	}

}
