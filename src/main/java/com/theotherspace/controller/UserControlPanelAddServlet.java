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
 * Servlet implementation class UserControlPanelAddServlet
 */
public class UserControlPanelAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errorMsg;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserControlPanelAddServlet() {
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
		
		request.getRequestDispatcher("WEB-INF/private-jsp/UserControlPanel-add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
//		Recupero i dati dal form
		String firstName, lastName, username, email, password;
		LocalDate dob;
		firstName = request.getParameter("add-first-name");
		lastName = request.getParameter("add-last-name");
		username = request.getParameter("add-username");
		email = request.getParameter("add-email");
		password = request.getParameter("add-password");
		dob = LocalDate.parse(request.getParameter("add-dob"));
		
//		Creo il nuovo utente
		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setUsername(username);
		u.setEmail(email);
		u.setPassword(password);
		u.setDateOfBirth(dob);
		
		if(newUserOk(u)) {
		    // Se i controlli sul nuovo utente ritornano true, aggiungi l'utente al DB
		    BusinessLogic.addUser(u);
		    request.getRequestDispatcher("WEB-INF/private-jsp/UserControlPanel-add.jsp").forward(request, response);
		    return;
		}
		
//		Se i controlli falliscono mostro il messaggio di errore
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher("WEB-INF/private-jsp/UserControlPanel-add.jsp").forward(request, response);
		
	}
	
	public boolean newUserOk(User u) {
//		Controlli per la creazione e l'aggiunta di un nuovo utente:
//		- email unica
//		- username unico
//		- password di almeno 8 caratteri
		
//		Controllo unicità email
		if (BusinessLogic.findUserByEmail(u.getEmail()) != null) {
//			imposta messaggio di errore
			errorMsg = "L'email è già in uso!";
			return false;
		}
		
//		Controllo unicità username
		if (BusinessLogic.findUserByUsername(u.getUsername()) != null) {
//			imposta messaggio di errore
			errorMsg = "L'username è già in uso!";
			return false;
		}
		
//		Controllo lunghezza password
		if (u.getPassword().length() < 8) {
//			imposta messaggio di errore
			errorMsg = "La password deve essere di almeno 8 caratteri!";
			return false;
		}
		
		return true;
	}

}
