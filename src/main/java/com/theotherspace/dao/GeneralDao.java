package com.theotherspace.dao;

import java.sql.SQLException;
import java.util.List;

public interface GeneralDao<T> {

	public abstract T findById(long id) throws SQLException;
	List<T> findAll();
	void update(T t);
	void delete(long id);
	void insert(T t);
	
}
