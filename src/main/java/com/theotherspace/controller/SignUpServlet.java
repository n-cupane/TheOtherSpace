package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;
import com.theotherspace.utilities.SendMail;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String errorMsg;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.sendRedirect("html/SignUp.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		
//		Recupero i dati dal form di registrazione
		String firstName, lastName, username, email, password;
		LocalDate dob;
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		username = request.getParameter("username");
		email = request.getParameter("email");
		password = request.getParameter("password");
		String dobString = request.getParameter("dob");
		
//		Controlli per impedire all'utente di non inserire dati nel form
		if (firstName.isBlank() || lastName.isBlank() || username.isBlank() || email.isBlank() || password.isBlank() || dobString.isBlank()) {
			response.sendRedirect("SignUpServlet");
			return;
		}
		
		dob = LocalDate.parse(dobString);
		
		
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
		    User activeUser = BusinessLogic.findUserByEmail(u.getEmail());
		    // Imposta l'utente appena registrato come utente loggato nella sessione
		    request.getSession().setAttribute("activeUser", activeUser);
		    
		    SendMail.send(activeUser);
		    
		    // Dopo aver aggiunto l'utente e impostato la sessione, esegui la reindirizzazione alla home page
		    response.sendRedirect("HomePageServlet");
		    return;
		}
//		Se i controlli falliscono mostro il messaggio di errore
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher("html/SignUp.jsp").forward(request, response);
		
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
