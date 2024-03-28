package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.theotherspace.model.Review;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class ConfirmReviewServlet
 */
public class ConfirmReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LocalDate reviewDate = LocalDate.now();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Controllo Aggiuntivo
		if(request.getSession().getAttribute("activeUser")==null) {
			response.sendRedirect("LogInServlet");
			return;
		}
		
		// TODO Auto-generated method stub
		String textReview = request.getParameter("reviewText");
		long userId = Long.parseLong(request.getParameter("userId"));
		long movieId = Long.parseLong(request.getParameter("movieId"));
		
		String ratingParameter = request.getParameter("rating");
        int ratingValue;
    	  switch(ratingParameter) {
       		case "a":
       			ratingValue = 5;
       			break;
       		case "b":
       			ratingValue = 4;
       			break;
       		case "c":
       			ratingValue = 3;
       			break;
       		case "d":
       			ratingValue = 2;
       			break;
       		case "e":
       			ratingValue = 1;
       			break;
       		default:
       			ratingValue = 0;
       			break;
       	} 
       	
    	System.out.println("ratingValue");
		
		Review userReview = new Review();
		userReview.setUser(BusinessLogic.findUserById(userId));
		userReview.setMovie(BusinessLogic.findMovieById(movieId));
		userReview.setText(textReview);
		userReview.setRating(ratingValue);
		userReview.setDate(reviewDate);
		
		BusinessLogic.addReview(userReview);
		
		response.sendRedirect("MyAccountServlet");
	}

}
