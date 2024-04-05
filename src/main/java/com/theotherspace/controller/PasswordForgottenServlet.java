package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;
import com.theotherspace.utilities.SendMail;

/**
 * Servlet implementation class PasswordForgottenServlet
 */
public class PasswordForgottenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordForgottenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/private-jsp/PasswordForgotten.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		User userToUpdate = BusinessLogic.findUserByEmail(email);
		if (userToUpdate != null) {
			SendMail.sendPassword(userToUpdate);
		}
		
		request.getRequestDispatcher("WEB-INF/private-jsp/NewPasswordSent.jsp").forward(request, response);
	}

}
