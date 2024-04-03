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
 * Servlet implementation class AccountDeletionServlet
 */
public class AccountDeletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeletionServlet() {
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
		//Test
				boolean isLoggedIn = (request.getSession().getAttribute("activeUser") != null);
		        request.setAttribute("isLoggedIn",isLoggedIn);
		        
		        if(isLoggedIn) {
		        	User activeUser =  (User)request.getSession().getAttribute("activeUser");
		            String activeUserUsername = activeUser.getUsername();
		            request.setAttribute("username", activeUserUsername);
		          
		        }
		        
	      User activeUser =  (User)request.getSession().getAttribute("activeUser");
	      activeUser.setPassword("deleted");
	      BusinessLogic.updateUser(activeUser);
	      request.getSession().invalidate();
	      response.sendRedirect("HomePageServlet");
	      
	      
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
