package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.theotherspace.model.Screening;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class ScreeningControlPanelAddServlet
 */
public class ScreeningControlPanelAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScreeningControlPanelAddServlet() {
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
		if (((User)request.getSession().getAttribute("activeUser")).getId() != 1) {
			response.sendRedirect("HomePageServlet");
			return;
		}
		request.getRequestDispatcher("WEB-INF/private-jsp/ScreeningControlPanel-add.jsp").forward(request, response);
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
		
		if (((User)request.getSession().getAttribute("activeUser")).getId() != 1) {
			response.sendRedirect("HomePageServlet");
			return;
		}
		
		String errorMsg;
		long movieId, theaterId;
		LocalDateTime dateTime;
		
		movieId = Long.parseLong(request.getParameter("add-movie-id"));
		theaterId = Long.parseLong(request.getParameter("add-theater-id"));
		dateTime = LocalDateTime.parse(request.getParameter("add-date-time"));
		
		Screening s = new Screening();
		s.setMovie(BusinessLogic.findMovieById(movieId));
		s.setTheater(BusinessLogic.findTheaterById(theaterId));
		s.setDateTime(dateTime);
		
//		Metti controlli per assicurarti che lo slot orario sia disponibile per la sala scelta
		if (true) {
			BusinessLogic.addScreening(s);
			request.getRequestDispatcher("WEB-INF/private-jsp/ScreeningControlPanel-add.jsp").forward(request, response);
			return;
		} else {
			errorMsg = "La sala scelta non è disponibile in questo orario!";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("WEB-INF/private-jsp/ScreeningControlPanel-add.jsp").forward(request, response);
			return;
		}
		
	}

}
