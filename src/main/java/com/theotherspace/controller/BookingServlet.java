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
import com.theotherspace.model.Theater;
import com.theotherspace.model.Ticket;
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
	List<Integer> ticketForScreaningBlocked = new ArrayList<>();
	
	
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
		
		//Prelevo l'orario della proiezione
		int hour = screeningDateTimeVariable.getHour();
		
		// In base all'orario associo un prezzo differente
        if (hour < 12) {
            price = 9.99;
        } else if (hour>=12 && hour<=18){
        	price = 12.99;
        } else {
        	price = 15.99;
        }
        
        // Fornisco i dati necessari al form per la prenotazione del posto
        request.setAttribute("ticketForScreaningBlocked", ticketForScreaningBlocked);
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
		
		// Prelevo l'utente attivo
		User activeUser = BusinessLogic.findUserByUsername(LogInServlet.username);
		
		// Prelevo dalla Servlet di descrizione film l'id della proiezione selezionata
		long screeningId =request.getParameter("movie-screening");
		// Prelevo i dati della prenotazione utilizzando la business logic
		Screening screeningReservation = BusinessLogic.findScreeningById(screeningId);
		
		
		// Prelevo l'orario della proiezione dalla screening appena caricata
		LocalDateTime screeningDateTime = screeningReservation.getDateTime();
		// associo la data e l'orario della proiezione ad una variabile della classe
		screeningDateTimeVariable = screeningDateTime;
		// Prelevo la sala per la proiezione e la associo alla variabile di classe
		theaterVariableId = screeningReservation.getTheaterId();
		// Prelevo l'id dell'utente attivo e lo associo ad una variabile
		idVariable = activeUser.getId();
		// Associo l'id della proiezione ad una variabile
		showing_idVariable = screeningReservation.getId();
		// Prelevo la sala con tutti i relativi dati
		Theater theaterVariable = BusinessLogic.findTheaterById(theaterVariableId);
		// Prelevo i posti della sala e li associo ad una variabile di classe
		seatsVariable = theaterVariable.getSeats();
		//  Prelevo i ticket già prenotati e faccio i controlli
		List<Ticket> ticketForScreaning = BusinessLogic.findAllTicketsForScreening(showing_idVariable);
		// Effettuo i controlli sulla presenza dei ticket già prenotati e li aggiungo alla lista se presenti
		if(ticketForScreaning!=null) {
			for(Ticket ticket : ticketForScreaning) {
				int seatBlocked = ticket.getSeat();
				ticketForScreaningBlocked.add(seatBlocked);
				
			}
		}
		
		
		//Faccio una chiamata get per caricare la pagina
		response.sendRedirect("BookingServlet");
		
		
		

	}

}
