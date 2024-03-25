package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ReviewServlet
 */
public class ReviewServlet extends HttpServlet {
	
	long movieId;
	long userId;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("movieId", movieId);
		request.setAttribute("userId", userId);
		request.getRequestDispatcher("/html/Review.jsp").forward(request, response);
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera i dati della recensione dalla richiesta
        movieId = Long.parseLong(request.getParameter("movieId"));
        userId = Long.parseLong(request.getParameter("userId"));
        response.sendRedirect("ReviewServlet");
    }
}
