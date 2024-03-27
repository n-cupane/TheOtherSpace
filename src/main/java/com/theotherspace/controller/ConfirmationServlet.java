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
       
	boolean haveCard = false;
	
	double discount= 0.0;
	
	double blockedTicketPrice;
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
		
		
		
		
		//verifico se l'utente ha una card
        User userTicket = BusinessLogic.findUserByUsername(LogInServlet.username);
        if(userTicket.getCardCode()!=null) {
        	BusinessLogic.updateUserCardPoint(userTicket, 100);
        	haveCard = true;
        }
        
        boolean usePoints = Boolean.parseBoolean(request.getParameter("usePoints"));
        
        int blockedTicketListSize = Integer.parseInt(request.getParameter("blockedTicketSize"));
        
		for(int i = 0; i<blockedTicketListSize; i++) {
			blockedTicketPrice = Double.parseDouble(request.getParameter("blockedTicketPrice"+i));  //10
			// Verifica se l'utente ha più di 1000 punti sulla carta e se vuole utilizzare i punti per lo sconto
		    if(userTicket.getCardPoints()>=1000 && haveCard && usePoints) {
		        // Aggiorna i punti della carta sottraendo 1000
		    	System.out.print(userTicket.getCardPoints());
		    	userTicket.setCardPoints(-1000);
		    	System.out.print(userTicket.getCardPoints());
		        blockedTicketPrice = 0.0; 
		    } else if(userTicket.getCardPoints()<1000 && haveCard && usePoints) {
		        // Se l'utente ha meno di 1000 punti sulla carta e vuole utilizzarli per lo sconto
		        for (Integer j = 100; userTicket.getCardPoints() - j >= 0; j += 100) { 
		            // Calcola lo sconto in base ai punti sulla carta
		            discount += 1.0;
		            userTicket.setCardPoints(userTicket.getCardPoints()-j);
		            
		        } 
		        // Calcola il prezzo del biglietto scontato
		        blockedTicketPrice = Double.parseDouble(request.getParameter("blockedTicketPrice"+i))-discount;
		        // Reimposta lo sconto a 0 per il prossimo biglietto
		        discount=0.0;
		    }
			int blockedTicketSeat = Integer.parseInt(request.getParameter("blockedTicketSeat"+i));
			Long blockedTicketUserId = Long.parseLong(request.getParameter("blockedTicketUserId"+i));
			Long blockedTicketScreeningId = Long.parseLong(request.getParameter("blockedTicketScreening"+i));
			User blockedTicketUser = BusinessLogic.findUserById(blockedTicketUserId);
			Screening blockedTicketScreening = BusinessLogic.findScreeningById(blockedTicketScreeningId);
			Ticket blockedTicketConfirmed = new Ticket(blockedTicketUser,blockedTicketScreening,blockedTicketPrice,blockedTicketSeat);
			BusinessLogic.addTicket(blockedTicketConfirmed);
		}
        
        response.sendRedirect("AccountInfoServlet");
	}

}
