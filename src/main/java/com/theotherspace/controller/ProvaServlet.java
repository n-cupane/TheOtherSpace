package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.theotherspace.dao.jpa.JPADaoFactory;
import com.theotherspace.model.Review;
import com.theotherspace.model.Screening;
import com.theotherspace.model.Theater;
import com.theotherspace.model.Ticket;
import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;
import com.theotherspace.utilities.SendMail;

/**
 * Servlet implementation class ProvaServlet
 */
public class ProvaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProvaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		//Controllo Aggiuntivo
//		if(request.getSession().getAttribute("activeUser")==null) {
//			response.sendRedirect("LogInServlet");
//			return;
//		}
//		
//		boolean isLoggedIn = (request.getSession().getAttribute("activeUser") != null);
//        request.setAttribute("isLoggedIn",isLoggedIn);
//        
//        if(isLoggedIn) {
//            // Se l'utente è loggato, mostro solo username e logout nel dropdown
//            User activeUser =  (User)request.getSession().getAttribute("activeUser");
//            String activeUserUsername = activeUser.getUsername();
//            request.setAttribute("username", activeUserUsername);
//        } 
//		
//		request.getRequestDispatcher("html/Prova.jsp").forward(request, response);
		
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
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
