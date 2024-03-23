<%@page import="com.theotherspace.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica Dati Utente</title>
</head>
<body>
	<a href="AccountInfoServlet">Vai a AccountInfoServlet</a>
	<a href="MyNextTicketServlet">Vai a MyNextTicket</a>
    <h2>Modifica Dati Utente</h2>
    <% User activeUser = (User)request.getAttribute("activeUser"); %>
    <form action="AccountInfoServlet" method="post">
        <input type="hidden" name="userId" value="<%= activeUser.getId() %>">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= activeUser.getUsername() %>"><br>
        
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="<%= activeUser.getFirstName() %>"><br>
        
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="<%= activeUser.getLastName() %>"><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= activeUser.getEmail() %>"><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="<%= activeUser.getPassword() %>"><br>
        
        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" value="<%= activeUser.getDateOfBirth() %>"><br>
        
        <button type="submit">Salva le modifiche</button>
    </form>
    <div>
    	<h1>Cancellazione dell'account</h1>
	    <p>Se vuoi cancellare il tuo account, 
	    <a href="AccountDeletionServlet" onclick="return confirm('Sei sicuro di voler cancellare il tuo account?')">clicca qui</a>
	    </p>
	    <p>Eliminando il tuo account non potrai usufruire dei servizi The Other Space Cinema.</p>
    </div>
</body>
</html>
