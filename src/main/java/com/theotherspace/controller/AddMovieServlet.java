package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.theotherspace.model.Movie;
import com.theotherspace.tmdb.TMDBInterrogation;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class AddMovieServlet
 */
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isLoggedIn", true);
		Movie movie = new Movie();
		Long movieId = null;
		Long movieToEditId = null;
		Long oldMovieId = null;
		
		try {
			movieId = Long.parseLong(request.getParameter("movieId"));
		} catch(Exception e) {
			System.out.println("movieId is null > editing movie instead of adding it");
		}
		
		try {
			movieToEditId = Long.parseLong(request.getParameter("movieToEditId"));
		} catch(Exception e) {
			System.out.println("movieToEditId is null > adding movie instead of editing it");
		}
		
		
		
		
		if (movieId != null) {
			movie = TMDBInterrogation.searchMovieById(movieId);
		
		} else if (movieToEditId != null) {
			movie = BusinessLogic.findMovieById(movieToEditId);
			oldMovieId = movie.getId();
		}
		
		request.setAttribute("oldMovieId", oldMovieId);
		
		String title = movie.getTitle();
		String description = movie.getDescription();
		String genreString = BusinessLogic.findGenreById(movie.getGenre().getId()).getName();
		String duration = Integer.toString(movie.getDuration());
		String imageUrl = movie.getImageUrl();
		boolean over18 = movie.isOver18();
		
		request.setAttribute("title", title);
		request.setAttribute("description", description);
		request.setAttribute("genre", genreString);
		request.setAttribute("duration", duration);
		request.setAttribute("imageUrl", imageUrl);
		request.setAttribute("over18", over18);
		
		request.getRequestDispatcher("WEB-INF/private-jsp/AddMovie.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long oldMovieId = null;
		try {
			oldMovieId = Long.parseLong(request.getParameter("oldMovieId"));
		} catch (Exception e) {
			System.out.println("No old movie id");
		}
		
		String title = request.getParameter("add-title");
		String description = request.getParameter("add-description");
		String genre = request.getParameter("add-genre");
		String duration = request.getParameter("add-duration");
		String imageUrl = request.getParameter("add-image-url");
		boolean over18 = request.getParameterValues("add-over-18") != null;
		
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setDescription(description);
		movie.setGenreFromName(genre);
		movie.setDuration(Integer.parseInt(duration));
		movie.setImageUrl(imageUrl);
		movie.setOver18(over18);
		
		if (oldMovieId != null) {
			if (BusinessLogic.findMovieById(oldMovieId) != null) {
				movie.setId(oldMovieId);
				BusinessLogic.updateMovie(movie);
				System.out.println("BBBBBBBBBBBBBB" + BusinessLogic.findMovieByTitle(title).getTitle());
				response.sendRedirect("http://localhost:8080/TheOtherSpace/MovieControlPanelServlet");
				return;
			}
		}
		BusinessLogic.addMovie(movie);
		System.out.println("AAAAAAAAAAAAAA");
			
		
		
		response.sendRedirect("http://localhost:8080/TheOtherSpace/MovieControlPanelServlet");
	}

}
