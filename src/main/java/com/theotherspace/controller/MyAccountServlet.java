package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class MyAccountServlet
 */
public class MyAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	boolean haveCard=false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		boolean isLoggedIn = (request.getSession().getAttribute("activeUser") != null);
        request.setAttribute("isLoggedIn",isLoggedIn);
        
        if(isLoggedIn) {
            // Se l'utente è loggato, mostro solo username e logout nel dropdown
            User activeUser =  (User)request.getSession().getAttribute("activeUser");
            String activeUserUsername = activeUser.getUsername();
            request.setAttribute("username", activeUserUsername);
        } 
        
        User activeUser = (User)request.getSession().getAttribute("activeUser");
        
        if(activeUser.getCardCode()!=null) {
        	haveCard = true;
        } else {
        	haveCard = false;
        }
        
        request.setAttribute("activeUser", activeUser);
        request.setAttribute("haveCard", haveCard);
		request.getRequestDispatcher("WEB-INF/private-jsp/MyAccount.jsp").forward(request, response);
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
