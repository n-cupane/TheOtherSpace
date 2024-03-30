package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.theotherspace.model.Screening;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class ScreeningControlPanelEditServlet
 */
public class ScreeningControlPanelEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScreeningControlPanelEditServlet() {
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
		
		long screeningId = Long.parseLong(request.getParameter("screeningId"));
		Screening screeningToEdit = BusinessLogic.findScreeningById(screeningId);
		request.setAttribute("screeningToEdit", screeningToEdit);
		request.getRequestDispatcher("WEB-INF/private-jsp/ScreeningControlPanel-edit.jsp").forward(request, response);
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
		
		String errorMsg;
		
		long movieId, theaterId, screeningId;
		LocalDateTime dateTime;
		
		screeningId = Long.parseLong(request.getParameter("edit-screening-id"));
		movieId = Long.parseLong(request.getParameter("edit-movie-id"));
		theaterId = Long.parseLong(request.getParameter("edit-theater-id"));
		dateTime = LocalDateTime.parse(request.getParameter("edit-date-time"));
		
		Screening oldScreening = BusinessLogic.findScreeningById(screeningId);
		
		Screening s = new Screening();
		s.setId(screeningId);
		s.setMovie(BusinessLogic.findMovieById(movieId));
		s.setTheater(BusinessLogic.findTheaterById(theaterId));
		s.setDateTime(dateTime);
		
//		Aggiungere controllo di disponibilità della sala nello slot orario
		if (BusinessLogic.timeSlotIsFree(theaterId, movieId, dateTime)) {
			BusinessLogic.updateScreening(s);
			request.setAttribute("screeningToEdit", s);
			request.getRequestDispatcher("WEB-INF/private-jsp/ScreeningControlPanel-edit.jsp").forward(request, response);
		    return;	
		} else {
			errorMsg = "La sala non è disponibile nella data e nell'ora indicate";
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("screeningToEdit", oldScreening);
			request.getRequestDispatcher("WEB-INF/private-jsp/ScreeningControlPanel-edit.jsp").forward(request, response);
		    return;
		}
	}

}
