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
		
		long movieId = Long.parseLong(request.getParameter("movieId"));
		Movie movie = TMDBInterrogation.searchMovieById(movieId);
		
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
		
		BusinessLogic.addMovie(movie);
		
		response.sendRedirect("http://localhost:8080/TheOtherSpace/HomePageServlet");
	}

}
