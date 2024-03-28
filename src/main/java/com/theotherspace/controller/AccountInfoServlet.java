package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class AccountInfoServlet
 */
public class AccountInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInfoServlet() {
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
		
		User activeUser = (User)request.getSession().getAttribute("activeUser");
		request.setAttribute("activeUser", activeUser);
		request.getRequestDispatcher("WEB-INF/private-jsp/AccountInfo.jsp").forward(request, response);

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
				
		long userId = Long.parseLong(request.getParameter("userId"));
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LocalDate dob = LocalDate.parse(request.getParameter("dateOfBirth"));

		User updatedUser = new User(userId,username,firstName,lastName,email,password,dob);
		
		request.getSession().setAttribute("activeUser",updatedUser);
		
		BusinessLogic.updateUser(updatedUser);
		response.sendRedirect("AccountInfoServlet");
		
	}

}
