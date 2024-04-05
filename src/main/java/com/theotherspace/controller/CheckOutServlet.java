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

import com.theotherspace.model.Movie;
import com.theotherspace.model.Screening;
import com.theotherspace.model.Ticket;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;


public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	List<Ticket> blockedTicket = new ArrayList<>();
	int ticketNumber = 0;
	List<Integer> seats = new ArrayList<>();
	LocalDateTime screeningDateTimeVariable;
	boolean haveCard = false;
	double price;

	
    public CheckOutServlet() {
        super();
       
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Test
		boolean isLoggedIn = (request.getSession().getAttribute("activeUser") != null);
        request.setAttribute("isLoggedIn",isLoggedIn);
        
        if(isLoggedIn) {
        	User activeUser =  (User)request.getSession().getAttribute("activeUser");
            String activeUserUsername = activeUser.getUsername();
            request.setAttribute("username", activeUserUsername);
          
        }  
        
      //Controllo Aggiuntivo
  		if(ticketNumber==0) {
  			response.sendRedirect("HomePageServlet");
  			return;
  		}
			
			request.setAttribute("price", price);
			request.setAttribute("screeningDateTimeVariable", screeningDateTimeVariable);
			request.setAttribute("ticketNumber", ticketNumber);
			request.setAttribute("blockedTicket", blockedTicket);
			request.setAttribute("seats", seats);
			request.setAttribute("haveCard", haveCard);
			request.getRequestDispatcher("html/CheckOut.jsp").forward(request, response);
			blockedTicket.clear();
			seats.clear();
			screeningDateTimeVariable = null;
			price = 0;
			haveCard = false;
			ticketNumber = 0;
			
			
			
		}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//Controllo Aggiuntivo
		if(request.getSession().getAttribute("activeUser")==null) {
			response.sendRedirect("LogInServlet");
			return;
		}
    	
        // Prelevo i dati dal form con valori hidden
        long screeningId = Long.parseLong(request.getParameter("showing_idVariable"));
        // Associo i valori alle variabili di classe
        price = Double.parseDouble(request.getParameter("price"));
        String screeningDateTimeString = request.getParameter("screeningDateTimeVariable");
        // Associo i valori alle variabili di classe
        screeningDateTimeVariable = LocalDateTime.parse(screeningDateTimeString);
        // Prelevo i posti nella sala dalla variabile statica di booking
        int seatsVariable = Integer.parseInt(request.getParameter("seatsVariable"));
        
        

        
        
        // effettuo i controlli per aggiungere i posti checkkati in una lista seats
        for (int i = 1; i <= seatsVariable; i++) {
        	
            String paramName = "seat" + i;
    
            String seatValue = request.getParameter(paramName);
            
            if (seatValue != null && !seatValue.isEmpty()) {
                int seatNumber = Integer.parseInt(seatValue);
                seats.add(seatNumber);
            }
        }
        //Aggiungo i ticket prenotati su una lista di ticket
        for(int seat : seats) {
        	User userTicket = (User)request.getSession().getAttribute("activeUser");
        	Screening userScreening = BusinessLogic.findScreeningById(screeningId);
        	
        	blockedTicket.add(new Ticket(userTicket,userScreening,price,seat));
        	ticketNumber++;
        }
        if(seats.isEmpty()) {
        	
        	//Test
    		boolean isLoggedIn = (request.getSession().getAttribute("activeUser") != null);
            request.setAttribute("isLoggedIn",isLoggedIn);
            
            if(isLoggedIn) {
            	User activeUser =  (User)request.getSession().getAttribute("activeUser");
                String activeUserUsername = activeUser.getUsername();
                request.setAttribute("username", activeUserUsername);
              
            }
            
            List<Ticket> ticketForScreaningBlocked = BusinessLogic.findAllTicketsForScreening(screeningId);
            List<Integer> ticketForScreaningInt = new ArrayList();
            
            for(Ticket ticket : ticketForScreaningBlocked) {
            	ticketForScreaningInt.add(ticket.getSeat());
            }
            
            
            
            String showing_idVariable_string = (String)request.getParameter("showing_idVariable");
            Long showing_idVariable = Long.parseLong(showing_idVariable_string);
            
            String imgMovie = (String)request.getParameter("imgMovie");
            
        	request.setAttribute("ticketForScreaningBlocked", ticketForScreaningInt);
            request.setAttribute("username", ((User)request.getSession().getAttribute("activeUser")).getUsername());
    		request.setAttribute("seatsVariable", seatsVariable);
    		request.setAttribute("screeningDateTimeVariable", screeningDateTimeVariable);
    		request.setAttribute("price", price);
    		request.setAttribute("imgMovie", imgMovie);
    		request.setAttribute("showing_idVariable", showing_idVariable);
    		request.getRequestDispatcher("html/Booking.jsp").forward(request, response);
    		
    		
        }
        
      //verifico se l'utente ha una card
        User userTicket = (User)request.getSession().getAttribute("activeUser");
        if(userTicket.getCardCode()!=null) {
        	haveCard = true;
        }
        
        
        
       
        
        // carico la pagina
        response.sendRedirect("CheckOutServlet");
        
        
        

        
    }

}
