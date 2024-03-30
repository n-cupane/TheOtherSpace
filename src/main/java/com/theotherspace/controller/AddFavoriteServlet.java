package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class AddFavoriteServlet
 */
public class AddFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long movieId = Long.parseLong(request.getParameter("movieId"));
		User activeUser = (User) request.getSession().getAttribute("activeUser");
		long activeUserId = activeUser.getId();
		
		if (!activeUser.getMovies().contains(BusinessLogic.findMovieById(movieId))) {
			BusinessLogic.addToFavorites(activeUser.getId(), movieId);
		} else {
			BusinessLogic.removeFromFavorites(activeUser.getId(), movieId);
		}
		
		activeUser = BusinessLogic.findUserById(activeUserId);
		
		request.getSession().setAttribute("activeUser", activeUser);
		response.sendRedirect("HomePageServlet");
		
	}

}
