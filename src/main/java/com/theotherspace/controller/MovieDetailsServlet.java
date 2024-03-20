package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.theotherspace.model.Movie;
import com.theotherspace.model.Screening;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class MovieDetailsServlet
 */
public class MovieDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		 Recupero l'id del film passato come parametro dalla home page e poi recupero il film stesso
//		 int movieId = Integer.parseInt(request.getParameter("movieId"));
		 Movie movieToDisplay = BusinessLogic.findMovieById(3); // SOSTITUISCI
		 
//		 Passo gli attributi da mostrare sulla jsp
		 request.setAttribute("movieTitle", movieToDisplay.getTitle());
		 request.setAttribute("over18", movieToDisplay.isOver18());
		 request.setAttribute("movieDescription", movieToDisplay.getDescription());
		 request.setAttribute("movieGenre", BusinessLogic.findGenreById(movieToDisplay.getGenreId()).getName() );
		 List<Screening> screeningsOfMovie = BusinessLogic.findAllScreningsByMovieId(3); // SOSTITUISCI
		 request.setAttribute("screeningsOfMovie", screeningsOfMovie);
		 
		 request.getRequestDispatcher("html/MovieDetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long screeningId = Long.parseLong(request.getParameter("movie-screening"));
		System.out.println("Screening id: " + screeningId);
	}

}
