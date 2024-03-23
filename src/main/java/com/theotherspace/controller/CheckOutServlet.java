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

import com.theotherspace.model.Ticket;
import com.theotherspace.utilities.BusinessLogic;


public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static List<Ticket> blockedTicket = new ArrayList<>();
	int ticketNumber = 0;
	List<Integer> seats = new ArrayList<>();
	LocalDateTime screeningDateTimeVariable;
	double price;
	
    public CheckOutServlet() {
        super();
       
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setAttribute("price", price);
			request.setAttribute("screeningDateTimeVariable", screeningDateTimeVariable);
			request.setAttribute("ticketNumber", ticketNumber);
			request.setAttribute("blockedTicket", blockedTicket);
			request.setAttribute("seats", seats);
			request.getRequestDispatcher("html/CheckOut.jsp").forward(request, response);
			
		}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        // Prelevo i dati dal form con valori hidden
        long screeningId = Long.parseLong(request.getParameter("showing_idVariable"));
        // Associo i valori alle variabili di classe
        price = Double.parseDouble(request.getParameter("price"));
        String screeningDateTimeString = request.getParameter("screeningDateTimeVariable");
        // Associo i valori alle variabili di classe
        screeningDateTimeVariable = LocalDateTime.parse(screeningDateTimeString);
        // Prelevo i posti nella sala dalla variabile statica di booking
        int seatsVariable = BookingServlet.seatsVariable;
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
        	long i = 1;
        	blockedTicket.add(new Ticket(i,BusinessLogic.findUserByUsername(LogInServlet.username).getId(),screeningId,price,seat));
        	i++;
        	ticketNumber++;
        }
        // carico la pagina
        response.sendRedirect("CheckOutServlet");
        
        
        

        
    }

}