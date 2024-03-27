package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.theotherspace.model.Theater;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class TheaterControlPanelEditServlet
 */
public class TheaterControlPanelEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheaterControlPanelEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long theaterId = Long.parseLong(request.getParameter("theaterId"));
		Theater theaterToEdit = BusinessLogic.findTheaterById(theaterId);
		request.setAttribute("theaterToEdit", theaterToEdit);
		request.getRequestDispatcher("WEB-INF/private-jsp/TheaterControlPanel-edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String errorMsg;
//		Recupero i dati dal form
		int number, seats;
		long theaterId;
		
		number = Integer.parseInt(request.getParameter("edit-number"));
		seats = Integer.parseInt(request.getParameter("edit-seats"));
		theaterId = Long.parseLong(request.getParameter("edit-id"));
		
		Theater oldTheater = BusinessLogic.findTheaterById(theaterId);
		
//		Creo la sala modificata
		Theater t = new Theater();
		t.setId(theaterId);
		t.setNumber(number);
		t.setSeats(seats);

//		Controllo che non esistano già altre sale con lo stesso numero
		if (BusinessLogic.findTheaterByNumber(number) != null && t.getNumber() != oldTheater.getNumber()) {
			errorMsg = "Esiste già un'altra sala con questo numero!";
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("theaterToEdit", oldTheater);
			request.getRequestDispatcher("WEB-INF/private-jsp/TheaterControlPanel-edit.jsp").forward(request, response);
		    return;
		} else {
			BusinessLogic.updateTheater(t);
			request.setAttribute("theaterToEdit", t);
			request.getRequestDispatcher("WEB-INF/private-jsp/TheaterControlPanel-edit.jsp").forward(request, response);
		    return;		    
		}
	
	}

}
