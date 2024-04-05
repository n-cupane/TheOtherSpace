package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.theotherspace.model.Movie;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class MovieControlPanelServlet
 */
public class MovieControlPanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieControlPanelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Movie> movies = BusinessLogic.findAllMovies();
		request.setAttribute("movies", movies);
		request.getRequestDispatcher("WEB-INF/private-jsp/MovieControlPanel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		int movieToRemoveId = Integer.valueOf(request.getParameter("movieId"));
		BusinessLogic.deleteMovie(movieToRemoveId);
		List<Movie> movies = BusinessLogic.findAllMovies();
		request.setAttribute("movies", movies);
		request.getRequestDispatcher("WEB-INF/private-jsp/MovieControlPanel.jsp").forward(request, response);
	}

}
