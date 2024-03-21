package com.theotherspace.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class LogInServlet
 */
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Costruisco delle variabili statiche per i controlli
	
	
	public static String username; // Mi permette di risalire all'utente loggato
	
	
	public static boolean logged = false; // Switch boolean per lo stato logged
	
	
	boolean wrongCredential = false; // Switch boolean per il controllo sulla correttezza delle credenziali
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Fornisco come attributo il controllo sulle credenziali errate per gli elementi da mostrare sulla login.jsp
		request.setAttribute("wrongCredential", wrongCredential);
		request.getRequestDispatcher("html/login.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Ricavo dal form i due input mail e password
		
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Creo un booleano interno al doPost per il controllo dell'avvenuto login 
        
        boolean loginSuccess = false;
        
        // Creo una lista prelevando gli utenti presenti nel DB
        
        List<User> users = BusinessLogic.findAllUsers();

        // Per ogni utente nel DB verifico se le credenziali inserite appratengono ad uno di essi
        
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loginSuccess = true;
                username = user.getUsername();
                break;
            } 
        }
        // Nel caso di avvenuto Login modifico i boolean di controllo e reindirizzo nella Home Servlet
        
        if (loginSuccess) {
        	logged=true;
        	wrongCredential = false;
        	request.getSession().setAttribute("loggedInUser", username);
            response.sendRedirect("HomePageServlet");   
        } else {   // Nel caso in cui le credenziali inserite non risultino corrette invio l'utente alla servlet di login con l'errore caricato 
        	wrongCredential = true; // modifica il boolean di controllo per far apparire il messaggio nella jsp
            response.sendRedirect("LogInServlet");
            return;
        }
	}

}
