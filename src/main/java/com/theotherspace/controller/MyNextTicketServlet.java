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
import com.theotherspace.model.Ticket;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class MyNextTicketServlet
 */
public class MyNextTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LocalDateTime activeUserDateTIme = LocalDateTime.now();
	
	Ticket nextTicket;
	
	Screening nextScreening;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyNextTicketServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Controllo Aggiuntivo
		if(request.getSession().getAttribute("activeUser")==null) {
			response.sendRedirect("LogInServlet");
			return;
		}
		
		boolean isLoggedIn = (request.getSession().getAttribute("activeUser") != null);
        request.setAttribute("isLoggedIn",isLoggedIn);
        
        if(isLoggedIn) {
            // Se l'utente è loggato, mostro solo username e logout nel dropdown
            User activeUser =  (User)request.getSession().getAttribute("activeUser");
            String activeUserUsername = activeUser.getUsername();
            request.setAttribute("username", activeUserUsername);
        } 
		
		
        User activeUser = (User)request.getSession().getAttribute("activeUser");
		long activeUserId = activeUser.getId();
		List<Ticket> activeUserTicketList = BusinessLogic.findAllTicketsOfUserOrderByDate(activeUserId);
		
		for(Ticket ticket : activeUserTicketList) {
			long screeningId = ticket.getScreening().getId();
			Screening nextScreening = BusinessLogic.findScreeningById(screeningId);
			this.nextScreening = nextScreening;
			if(nextScreening.getDateTime().isAfter(activeUserDateTIme)) {
				nextTicket = ticket;
				break;
			}
		}
		
		
		
		
		request.setAttribute("nextTicket", nextTicket);
		request.setAttribute("activeUser", activeUser);
		request.setAttribute("nextScreening", nextScreening);
		request.getRequestDispatcher("WEB-INF/private-jsp/MyNextTicket.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
