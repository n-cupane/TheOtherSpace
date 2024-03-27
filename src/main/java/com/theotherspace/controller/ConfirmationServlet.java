package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.theotherspace.model.Screening;
import com.theotherspace.model.Ticket;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class ConfirmationServlet
 */
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int blockedTicketListSize = Integer.parseInt(request.getParameter("blockedTicketSize"));
		for(int i = 0; i<blockedTicketListSize; i++) {
			double blockedTicketPrice = Double.parseDouble(request.getParameter("blockedTicketPrice"+i));
			int blockedTicketSeat = Integer.parseInt(request.getParameter("blockedTicketSeat"+i));
			Long blockedTicketUserId = Long.parseLong(request.getParameter("blockedTicketUserId"+i));
			Long blockedTicketScreeningId = Long.parseLong(request.getParameter("blockedTicketScreening"+i));
			User blockedTicketUser = BusinessLogic.findUserById(blockedTicketUserId);
			Screening blockedTicketScreening = BusinessLogic.findScreeningById(blockedTicketScreeningId);
			Ticket blockedTicketConfirmed = new Ticket(blockedTicketUser,blockedTicketScreening,blockedTicketPrice,blockedTicketSeat);
			BusinessLogic.addTicket(blockedTicketConfirmed);
		}
	}

}
