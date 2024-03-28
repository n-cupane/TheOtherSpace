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
 * Servlet implementation class UserControlPanelEditServlet
 */
public class UserControlPanelEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String errorMsg;
    private User oldUser;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserControlPanelEditServlet() {
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
		
		long userId = Long.parseLong(request.getParameter("userId"));
		User userToEdit = BusinessLogic.findUserById(userId);
		request.setAttribute("userToEdit", userToEdit);
		request.getRequestDispatcher("WEB-INF/private-jsp/UserControlPanel-edit.jsp").forward(request, response);
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
		
//		Recupero i dati dal form
		String firstName, lastName, username, email, password;
		LocalDate dob;
		long userId;
		firstName = request.getParameter("add-first-name");
		lastName = request.getParameter("add-last-name");
		username = request.getParameter("add-username");
		email = request.getParameter("add-email");
		password = request.getParameter("add-password");
		dob = LocalDate.parse(request.getParameter("add-dob"));
		userId = Long.parseLong(request.getParameter("add-id"));
		
		oldUser = BusinessLogic.findUserById(userId);
		
//		Creo l'utente modificato
		User u = new User();
		u.setId(userId);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setUsername(username);
		u.setEmail(email);
		u.setPassword(password);
		u.setDateOfBirth(dob);
		
		if(newUserOk(u)) {
		    // Se i controlli sul nuovo utente ritornano true, modifico l'utente nel DB
		    BusinessLogic.updateUser(u);
		    request.setAttribute("userToEdit", u);
		    request.getRequestDispatcher("WEB-INF/private-jsp/UserControlPanel-edit.jsp").forward(request, response);
		    return;
		}
//		Se i controlli falliscono mostro il messaggio di errore
		request.setAttribute("errorMsg", errorMsg);
		request.setAttribute("userToEdit", oldUser);
		request.getRequestDispatcher("WEB-INF/private-jsp/UserControlPanel-edit.jsp").forward(request, response);
		
	}
	
	public boolean newUserOk(User u) {
//		Controlli per la creazione e l'aggiunta di un nuovo utente:
//		- email unica
//		- username unico
//		- password di almeno 8 caratteri
		
//		Controllo unicità email
		if (BusinessLogic.findUserByEmail(u.getEmail()) != null && !u.getEmail().equals(oldUser.getEmail())) {
//			imposta messaggio di errore
			errorMsg = "L'email è già in uso!";
			return false;
		}
		
//		Controllo unicità username
		if (BusinessLogic.findUserByUsername(u.getUsername()) != null && !u.getUsername().equals(oldUser.getUsername())) {
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
