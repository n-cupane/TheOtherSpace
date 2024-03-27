package com.theotherspace.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

import com.theotherspace.model.User;
import com.theotherspace.utilities.BusinessLogic;

/**
 * Servlet implementation class AcceptCardServlet
 */
public class AcceptCardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptCardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Metodo per generare una stringa casuale di 5 caratteri
    public static String generateRandomString() {
        // Stringa contenente tutti i caratteri da cui scegliere
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Lunghezza della stringa casuale
        int length = 5;

        // Creazione di un oggetto Random
        Random random = new Random();

        // Array di caratteri per memorizzare la stringa casuale
        char[] randomString = new char[length];

        // Generazione della stringa casuale
        for (int i = 0; i < length; i++) {
            // Generazione di un indice casuale per selezionare un carattere dalla stringa chars
            int index = random.nextInt(chars.length());

            // Aggiunta del carattere selezionato all'array randomString
            randomString[i] = chars.charAt(index);
        }

        // Creazione della stringa a partire dall'array di caratteri
        return new String(randomString);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String randomString = generateRandomString();
        User activeUser = BusinessLogic.findUserByUsername(LogInServlet.username);
        BusinessLogic.updateUserCard(activeUser, "");
        BusinessLogic.updateUserCard(activeUser, randomString);
        response.sendRedirect("AccountInfoServlet");
        
    }

   
 
}
