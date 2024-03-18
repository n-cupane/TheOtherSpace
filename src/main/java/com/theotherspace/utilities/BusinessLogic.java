package com.theotherspace.utilities;

import java.sql.SQLException;
import java.util.List;

import com.theotherspace.dao.DaoFactory;
import com.theotherspace.model.User;

public class BusinessLogic {
	
//	ATTENZIONE: Tutti i metodi seguenti restituiscono null se la query dà result set vuoto
//	Quindi ogni volta che si usa uno di questi metodi effettuare gli opportuni controlli
//	prima di utilizzare il valore ritornato per evitare NullPointerException
	
	
	
//	METODI USER
	
	public static User findUserById(long id) {
		return DaoFactory.getDaoFactory().getUserDao().findById(id);
	}
	
	public static List<User> findAllUsers() {
		return DaoFactory.getDaoFactory().getUserDao().findAll();
	}
	
	public static void updateUser(User u) {
		DaoFactory.getDaoFactory().getUserDao().update(u);
	}
	
	public static void deleteUser(long id) {
		DaoFactory.getDaoFactory().getUserDao().delete(id);
	}
	
	public static void addUser(User u) {
		DaoFactory.getDaoFactory().getUserDao().insert(u);
	}
	
	public static User findUserByEmail(String email) {
		return DaoFactory.getDaoFactory().getUserDao().findUserByEmail(email);
	}

}
