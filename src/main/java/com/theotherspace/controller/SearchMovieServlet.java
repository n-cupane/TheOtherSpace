package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.theotherspace.model.Movie;
import com.theotherspace.tmdb.TMDBInterrogation;

/**
 * Servlet implementation class SearchMovieServlet
 */
public class SearchMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("isLoggedIn", true);
		request.getRequestDispatcher("WEB-INF/private-jsp/MovieSearch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isLoggedIn", true);
		String query = request.getParameter("movie-search-bar");
		List<Movie> moviesFound = TMDBInterrogation.searchMovies(query);
		request.setAttribute("moviesFound", moviesFound);
		request.getRequestDispatcher("WEB-INF/private-jsp/MovieSearch.jsp").forward(request, response);
		
	}

}
