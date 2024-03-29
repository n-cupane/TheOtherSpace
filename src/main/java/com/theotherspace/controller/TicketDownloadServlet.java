package com.theotherspace.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class TicketDownloadServlet
 */
public class TicketDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	long ticketMovieId;
    
    String screeningDateTimeString;
    
    double ticketPrice;
    
    int ticketSeat;
    
    // Recupero il nome del film
    
    String ticketMovieTitle;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
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
        
        
        request.setAttribute("screeningTicketTime", screeningDateTimeString);
        request.setAttribute("ticketPrice", ticketPrice);
        request.setAttribute("ticketSeat", ticketSeat);
        request.setAttribute("ticketMovieTitle", ticketMovieTitle);
        
        request.getRequestDispatcher("WEB-INF/private-jsp/TicketDownload.jsp").forward(request, response);
        
        ticketMovieId = 0;
        screeningDateTimeString = null;
        ticketPrice = 0.0; // O imposta a un valore di default
        ticketSeat = 0; // O imposta a un valore di default
        ticketMovieTitle = null;
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
		
		
        
        // Prelevo i dati del biglietto dal form 
        
         ticketMovieId = Long.parseLong(request.getParameter("movieId"));
        
         screeningDateTimeString = request.getParameter("screeningDateTime");
         
         String modifiedDate = screeningDateTimeString.replace("T", " ");
         
         screeningDateTimeString = modifiedDate;
        
         ticketPrice = Double.parseDouble(request.getParameter("price"));
        
         ticketSeat = Integer.parseInt(request.getParameter("seat"));
        
        // Recupero il nome del film
        
         ticketMovieTitle = BusinessLogic.findMovieById(ticketMovieId).getTitle();
         
         
         response.sendRedirect("TicketDownloadServlet");
        
        
        
        
	}

}
