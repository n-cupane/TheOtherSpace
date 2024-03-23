package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.theotherspace.model.Screening;
import com.theotherspace.model.Ticket;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class MyTicketServlet
 */
public class MyTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	List<Ticket> userTicket = new ArrayList<>();
	
	List<Screening> screeningUser = new ArrayList<>();
	
	Long userId;
	
	String userFirstName;
	
	String userLastName;
	
	String userActriveUsername;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyTicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		//Passo alla jsp la lista dei ticket
		request.setAttribute("userId", userId);
		request.setAttribute("userFirstName", userFirstName);
		request.setAttribute("userLastName", userLastName);
		request.setAttribute("userTicket", userTicket);
		request.setAttribute("screeningUser", screeningUser);
		
		request.getRequestDispatcher("WEB-INF/private-jsp/MyTicket.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Prelevo l'utente
		userActriveUsername = LogInServlet.username;
		// Costruisco l'utente loggato tramite la business logic
		User activeUser = BusinessLogic.findUserByUsername(LogInServlet.username);
		
		// Prelevo l'id dell'utente con nome e cognome
		
		userFirstName = activeUser.getFirstName();
		
		userLastName = activeUser.getLastName();
		
		userId = activeUser.getId();
		
		// Ricavo i ticket legati all'utente
		userTicket = BusinessLogic.findAllTicketsOfUserOrderByDate(userId);
		
		// Ricavo le proiezioni in base ai ticket dell'utente
		
		for(Ticket userTicket : userTicket) {
			Long ticketScreeningID = userTicket.getScreeningId();
			Screening ticketScreening = BusinessLogic.findScreeningById(ticketScreeningID);
			screeningUser.add(ticketScreening);
		}
		
		response.sendRedirect("MyTicketServlet");
		
	}

}
