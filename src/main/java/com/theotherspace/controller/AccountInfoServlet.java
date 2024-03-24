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
		//Aggiunto test
		boolean isLoggedIn = (request.getSession().getAttribute("loggedInUser") != null);
        request.setAttribute("isLoggedIn",isLoggedIn);
        
        if(isLoggedIn) {
            // Se l'utente è loggato, mostro solo username e logout nel dropdown
            String username = (String) request.getSession().getAttribute("loggedInUser");
            request.setAttribute("username", username);
        }
		
		User activeUser = BusinessLogic.findUserByUsername(LogInServlet.username);
		request.setAttribute("activeUser", activeUser);
		request.getRequestDispatcher("WEB-INF/private-jsp/AccountInfo.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long userId = Long.parseLong(request.getParameter("userId"));
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LocalDate dob = LocalDate.parse(request.getParameter("dateOfBirth"));
		
		if(username != LogInServlet.username) {
			LogInServlet.username = username;
		}
		
		User updatedUser = new User(userId,username,firstName,lastName,email,password,dob);
		BusinessLogic.updateUser(updatedUser);
		response.sendRedirect("AccountInfoServlet");
		
	}

}
