package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.theotherspace.model.Screening;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class ScreeningControlPanelServlet
 */
public class ScreeningControlPanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScreeningControlPanelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		if (((User)request.getSession().getAttribute("activeUser")).getId() != 1) {
			response.sendRedirect("HomePageServlet");
			return;
		}

		List<Screening> screenings = BusinessLogic.findAllScreenings();
		request.setAttribute("screenings", screenings);
		request.getRequestDispatcher("WEB-INF/private-jsp/ScreeningControlPanel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		int screeningToRemoveId = Integer.valueOf(request.getParameter("screeningId"));
		BusinessLogic.deleteScreening(screeningToRemoveId);
		List<Screening> screenings = BusinessLogic.findAllScreenings();
		request.setAttribute("screenings", screenings);
		request.getRequestDispatcher("WEB-INF/private-jsp/ScreeningControlPanel.jsp").forward(request, response);
	}

}
