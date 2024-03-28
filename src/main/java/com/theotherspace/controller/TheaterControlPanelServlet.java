package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.theotherspace.model.Theater;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class TheaterControlPanelServlet
 */
public class TheaterControlPanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheaterControlPanelServlet() {
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

		List<Theater> theaters = BusinessLogic.findAllTheaters();
		request.setAttribute("theaters", theaters);
		request.getRequestDispatcher("WEB-INF/private-jsp/TheaterControlPanel.jsp").forward(request, response);
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
		
		int theaterToRemoveId = Integer.valueOf(request.getParameter("theaterId"));
		BusinessLogic.deleteTheater(theaterToRemoveId);
		List<Theater> theaters = BusinessLogic.findAllTheaters();
		request.setAttribute("theaters", theaters);
		request.getRequestDispatcher("WEB-INF/private-jsp/TheaterControlPanel.jsp").forward(request, response);
	}

}
