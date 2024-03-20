package com.theotherspace.utilities;

import java.sql.SQLException;
import java.util.List;

import com.theotherspace.dao.DaoFactory;
import com.theotherspace.model.Review;
import com.theotherspace.model.Screening;
import com.theotherspace.model.Theater;
import com.theotherspace.model.Ticket;
import com.theotherspace.model.User;

public class BusinessLogic {
	
//	ATTENZIONE: Tutti i metodi seguenti restituiscono null se la query dà result set vuoto
//	quindi ogni volta che si usa uno di questi metodi effettuare gli opportuni controlli
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
	
	public static List<User> findAllUsersForScreening(long screeningId) {
		return DaoFactory.getDaoFactory().getUserDao().findAllUsersForScreening(screeningId);
	}
	
	
	
//	METODI TICKET
	
	public static Ticket findTicketById(long id) {
		return DaoFactory.getDaoFactory().getTicketDao().findById(id);
	}
	
	public static List<Ticket> findAllTickets() {
		return DaoFactory.getDaoFactory().getTicketDao().findAll();
	}
	
	public static void updateTicket(Ticket t) {
		DaoFactory.getDaoFactory().getTicketDao().update(t);
	}
	
	public static void deleteTicket(long id) {
		DaoFactory.getDaoFactory().getTicketDao().delete(id);
	}
	
	public static void addTicket(Ticket t) {
		DaoFactory.getDaoFactory().getTicketDao().insert(t);
	}
	
	public static List<Ticket> findAllTicketsOfUser(long userId) {
		return DaoFactory.getDaoFactory().getTicketDao().findAllTicketsOfUser(userId);
	}
	
	public static List<Ticket> findAllTicketsForScreening(long screeningId) {
		return DaoFactory.getDaoFactory().getTicketDao().findAllTicketsForScreening(screeningId);
	}
	
	public static List<Ticket> findAllTicketsForMovie(long movieId) {
		return DaoFactory.getDaoFactory().getTicketDao().findAllTicketsForMovie(movieId);
	}
	
// 	METODI REVIEW
	
	public static Review findReviewById(long id) {
		return DaoFactory.getDaoFactory().getReviewDao().findById(id);
	}
	
	public static List<Review> findAllReviews() {
		return DaoFactory.getDaoFactory().getReviewDao().findAll();
	}
	
	public static void updateReview(Review rw) {
		DaoFactory.getDaoFactory().getReviewDao().update(rw);
	}
	
	public static void deleteReview(long id) {
		DaoFactory.getDaoFactory().getReviewDao().delete(id);
	}
	
	public static void addReview(Review rw) {
		DaoFactory.getDaoFactory().getReviewDao().insert(rw);
	}
	
// METODI THEATER
	
	public static Theater findTheaterById(long id) {
		return DaoFactory.getDaoFactory().getTheaterDao().findById(id);
	}
	
	public static List<Theater> findAllTheaters() {
		return DaoFactory.getDaoFactory().getTheaterDao().findAll();
	}
	
	public static void updateTheater(Theater tr) {
		DaoFactory.getDaoFactory().getTheaterDao().update(tr);
	}
	
	public static void deleteTheater(long id) {
		DaoFactory.getDaoFactory().getTheaterDao().delete(id);
	}
	
	public static void addTheater(Theater tr) {
		DaoFactory.getDaoFactory().getTheaterDao().insert(tr);
	}
	
// METODI SCREENING
	
	public static Screening findScreeningById(long id) {
		return DaoFactory.getDaoFactory().getScreeningDao().findById(id);
	}
	
	public static List<Screening> findAllScreenings() {
		return DaoFactory.getDaoFactory().getScreeningDao().findAll();
	}
	
	public static void updateScreening(Screening sc) {
		DaoFactory.getDaoFactory().getScreeningDao().update(sc);
	}
	
	public static void deleteScreening(long id) {
		DaoFactory.getDaoFactory().getScreeningDao().delete(id);
	}
	
	public static void addScreening(Screening sc) {
		DaoFactory.getDaoFactory().getScreeningDao().insert(sc);
	}
	
	
	

}
