package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.theotherspace.model.Screening;
import com.theotherspace.model.Theater;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class BookinServlet
 */
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LocalDateTime screeningDateTimeVariable;
	long idVariable;
	long showing_idVariable;
	double price;
	Long theaterVariableId;
	static int seatsVariable;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int hour = screeningDateTimeVariable.getHour();

        if (hour < 12) {
            price = 9.99;
        } else {
        	price = 12.99;
        }
        
        request.setAttribute("username", LogInServlet.username);
		request.setAttribute("seatsVariable", seatsVariable);
		request.setAttribute("screeningDateTimeVariable", screeningDateTimeVariable);
		request.setAttribute("price", price);
		request.setAttribute("showing_idVariable", showing_idVariable);
		request.getRequestDispatcher("html/Booking.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User activeUser = BusinessLogic.findUserByUsername(LogInServlet.username);
		//long screeningId =request.getParameter("screeningId");
		//Screening screeningReservation = BusinessLogic.findScreeningById(screeningId);
		Screening screeningReservation = BusinessLogic.findScreeningById(1);
		LocalDateTime screeningDateTime = screeningReservation.getDateTime();
		screeningDateTimeVariable = screeningDateTime;
		theaterVariableId = screeningReservation.getTheaterId();
		idVariable = activeUser.getId();
		showing_idVariable = screeningReservation.getId();
		Theater theaterVariable = BusinessLogic.findTheaterById(theaterVariableId);
		seatsVariable = theaterVariable.getSeats();
		response.sendRedirect("BookingServlet");
		
		
		

	}

}
