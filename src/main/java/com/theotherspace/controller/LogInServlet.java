package com.theotherspace.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
	
	
	//public  String username; // Mi permette di risalire all'utente loggato ELIMINATO PER TEST
	
	
	//public  boolean logged = false; // Switch boolean per lo stato logged ELIMINATO PER TEST
	
	
	boolean wrongCredential = false; // Switch boolean per il controllo sulla correttezza delle credenziali
	
	public static void logIn (User u, HttpSession httpSession) {
		httpSession.setAttribute("activeUser", u);
	}
       
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
		
		
		//Test
		boolean isLoggedIn = (request.getSession().getAttribute("activeUser") != null);
        request.setAttribute("isLoggedIn",isLoggedIn);
        
       
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
        User activeUser = BusinessLogic.findUserByEmail(email);
       
        
        // Creo un booleano interno al doPost per il controllo dell'avvenuto login 
        
        boolean loginSuccess = false;
        
        // Creo una lista prelevando gli utenti presenti nel DB
        
        List<User> users = BusinessLogic.findAllUsers();

        // Per ogni utente nel DB verifico se le credenziali inserite appratengono ad uno di essi
        
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                loginSuccess = true;
                break;
            } 
        }
        // Nel caso di avvenuto Login modifico i boolean di controllo e reindirizzo nella Home Servlet
        
        if (loginSuccess) {
        	wrongCredential = false;
        	this.logIn(activeUser, request.getSession());
        	
        	if (activeUser.getId() == 1) {
        		response.sendRedirect("UserControlPanelServlet");
        		return;
        	}
        	
            response.sendRedirect("HomePageServlet");   
        } else {   // Nel caso in cui le credenziali inserite non risultino corrette invio l'utente alla servlet di login con l'errore caricato 
        	wrongCredential = true; // modifica il boolean di controllo per far apparire il messaggio nella jsp
            response.sendRedirect("LogInServlet");
            return;
        }
	}

}
