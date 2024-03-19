package com.theotherspace.utilities;

import java.sql.SQLException;
import java.util.List;

import com.theotherspace.dao.DaoFactory;
import com.theotherspace.model.Favorite;
import com.theotherspace.model.Genre;
import com.theotherspace.model.Movie;
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

	
	
//	METODI MOVIE
	
	public static Movie findMovieById(long id) {
		return DaoFactory.getDaoFactory().getMovieDao().findById(id);
	}
	
	public static List<Movie> findAllMovies() {
		return DaoFactory.getDaoFactory().getMovieDao().findAll();
	}
	
	public static void updateMovie(Movie m) {
		DaoFactory.getDaoFactory().getMovieDao().update(m);
	}
	
	public static void deleteMovie(long id) {
		DaoFactory.getDaoFactory().getMovieDao().delete(id);
	}
	
	public static void addMovie(Movie m) {
		DaoFactory.getDaoFactory().getMovieDao().insert(m);
	}
	
	public static Movie findMovieByTitle(String title) {
		return DaoFactory.getDaoFactory().getMovieDao().findMovieByTitle(title);
	}
	
	public static List<Movie> findAllMoviesForGenre(Genre g) {
		return DaoFactory.getDaoFactory().getMovieDao().findAllMoviesForGenre(g);
	}
	
	
	
//	METODI GENRE
	
	public static Genre findGenreById(long id) {
		return DaoFactory.getDaoFactory().getGenreDao().findById(id);
	}
	
	public static List<Genre> findAllGenres() {
		return DaoFactory.getDaoFactory().getGenreDao().findAll();
	}
	
	public static void updateGenre(Genre g) {
		DaoFactory.getDaoFactory().getGenreDao().update(g);
	}
	
	public static void deleteGenre(long id) {
		DaoFactory.getDaoFactory().getGenreDao().delete(id);
	}
	
	public static void addGenre(Genre g) {
		DaoFactory.getDaoFactory().getGenreDao().insert(g);
	}
	
	public static Genre findGenreByName(String name) {
		return DaoFactory.getDaoFactory().getGenreDao().findGenreByName(name);
	}
	
	
	
//	METODI FAVORITE
	
	public static Favorite findFavoriteById(long id) {
		return DaoFactory.getDaoFactory().getFavoriteDao().findById(id);
	}
	
	public static List<Favorite> findAllFavorites() {
		return DaoFactory.getDaoFactory().getFavoriteDao().findAll();
	}
	
	public static void updateFavorite(Favorite f) {
		DaoFactory.getDaoFactory().getFavoriteDao().update(f);
	}
	
	public static void deleteFavorite(long id) {
		DaoFactory.getDaoFactory().getFavoriteDao().delete(id);
	}
	
	public void addFavorite(Favorite f) {
		DaoFactory.getDaoFactory().getFavoriteDao().insert(f);
	}
} 
