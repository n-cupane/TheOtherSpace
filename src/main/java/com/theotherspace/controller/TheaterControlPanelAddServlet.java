package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.theotherspace.model.Theater;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class TheaterControlPanelAddServlet
 */
public class TheaterControlPanelAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheaterControlPanelAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/private-jsp/TheaterControlPanel-add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errorMsg;
		int number, seats;
		
		number = Integer.parseInt(request.getParameter("add-number"));
		seats = Integer.parseInt(request.getParameter("add-seats"));
		
		Theater t = new Theater();
		t.setNumber(number);
		t.setSeats(seats);
		
		if (BusinessLogic.findTheaterByNumber(number) == null) {
			BusinessLogic.addTheater(t);
			request.getRequestDispatcher("WEB-INF/private-jsp/TheaterControlPanel-add.jsp").forward(request, response);
			return;
		} else {
			errorMsg = "Esiste già una sala con questo numero!";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("WEB-INF/private-jsp/TheaterControlPanel-add.jsp").forward(request, response);
			return;
		}
	}

}
