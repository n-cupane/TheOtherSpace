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
import com.theotherspace.utilities.SendMail;

/**
 * Servlet implementation class ConfirmationServlet
 */
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	boolean haveCard = false;
	
	
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
		User userTicket = (User)request.getSession().getAttribute("activeUser");
        if(userTicket.getCardCode()!=null) {
        	userTicket.setCardPoints(100);
        	haveCard = true;
        }
        
        boolean usePoints = Boolean.parseBoolean(request.getParameter("usePoints"));
        
        int blockedTicketListSize = Integer.parseInt(request.getParameter("blockedTicketSize"));
        
		for(int i = 0; i<blockedTicketListSize; i++) {
			
			// Verifica se l'utente ha più di 1000 punti sulla carta e se vuole utilizzare i punti per lo sconto
		    if(userTicket.getCardPoints()>=1000 && haveCard && usePoints) {
		        // Aggiorna i punti della carta sottraendo 1000
		    	
		    	userTicket.setCardPoints(-1000);
		        blockedTicketPrice = 0.0; 
		        
		    } else if(userTicket.getCardPoints()<1000 && userTicket.getCardPoints()>0 && haveCard && usePoints) {
		        // Se l'utente ha meno di 1000 punti sulla carta e vuole utilizzarli per lo sconto
		    	int multiplier = (userTicket.getCardPoints()/100);
		    	System.out.println(userTicket.getCardPoints());
		    	
		    	double discount= 1.0;
		        // Calcola il prezzo del biglietto scontato
		    	double totalDiscount = discount * multiplier;
		        String blockedTicketPriceUnfixed = request.getParameter("blockedTicketPrice"+i);
		        double blockedTicketPricefixed = Double.parseDouble(blockedTicketPriceUnfixed);
		        System.out.println(blockedTicketPricefixed);
		        blockedTicketPrice = blockedTicketPricefixed-totalDiscount;
		        System.out.println(blockedTicketPrice);
		        //blockedTicketPrice = blockedTicketPriceUnfixed-discount;
		        userTicket.setCardPoints(-userTicket.getCardPoints());
		        System.out.println(userTicket.getCardPoints());
		        BusinessLogic.updateUser(userTicket);
		        // Reimposta lo sconto a 0 per il prossimo biglietto
		        
		        
		        
		    } else if(!haveCard && !usePoints) {
		    	blockedTicketPrice = Double.parseDouble(request.getParameter("blockedTicketPrice"+i));  //10
		    } else if(haveCard && !usePoints) {
		    	blockedTicketPrice = Double.parseDouble(request.getParameter("blockedTicketPrice"+i));  //10
		    } else if(userTicket.getCardPoints()==0) {
		    	blockedTicketPrice = Double.parseDouble(request.getParameter("blockedTicketPrice"+i));
		    }
		    
			int blockedTicketSeat = Integer.parseInt(request.getParameter("blockedTicketSeat"+i));
			Long blockedTicketUserId = Long.parseLong(request.getParameter("blockedTicketUserId"+i));
			Long blockedTicketScreeningId = Long.parseLong(request.getParameter("blockedTicketScreening"+i));
			Screening blockedTicketScreening = BusinessLogic.findScreeningById(blockedTicketScreeningId);
			Ticket blockedTicketConfirmed = new Ticket(userTicket,blockedTicketScreening,blockedTicketPrice,blockedTicketSeat);
			BusinessLogic.addTicket(blockedTicketConfirmed);
		}
		
		SendMail.sendConfirmation(userTicket);
        
        response.sendRedirect("MyNextTicketServlet");
	}

}
