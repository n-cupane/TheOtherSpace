<%@page import="com.theotherspace.model.Ticket"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Booking.css"/>
    <title>Checkout Summary</title>
</head>
<body>
	<div id="container" class="container-fluid">

        <header id="header" class="row justify-content-center">
            
            <div class="col-2 d-flex align-items-end justify-content-end">
                <img src="<%=request.getContextPath()%>/res/logo_other_space.png" alt="Logo The Other" id="logo_other_space">
            </div>
            
            <nav class="col-3 d-flex align-items-center justify-content-evenly">

                <a href="#container_movies">FILM</a>
                

            </nav>

            <div class="col-2 d-flex align-items-center">

                <div class="dropdown">
                    
                    <button id="btn" class="btn btn-secondary dropdown-toggle d-flex justify-content-evenly align-items-center" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                      <span id="person-img" class="material-icons">&#xe7fd;</span>
                      <% if((Boolean)request.getAttribute("isLoggedIn")){ %>
                      	<p><%= session.getAttribute("loggedInUser") %></p>
                   	 <% }else{ %>
                   	 	<p>Login</p>
                     <% } %>
                     
                      
                    </button>
                    <ul class="dropdown-menu">
                    <% if((Boolean)request.getAttribute("isLoggedIn")){ %>
                    <!-- Se l'utente è loggato, mostra solo l'username e l'opzione Logout -->
                      <li><a class="dropdown-item" href="/TheOtherSpace/MyAccountServlet">My account</a></li>
                      <li><a class="dropdown-item" href="/TheOtherSpace/LogoutServlet">Logout</a></li>
                    <% }else{ %>
                     <!-- Se l'utente non è loggato, mostra le opzioni Signin e Login -->
                      <li><a class="dropdown-item" href="/TheOtherSpace/SignUpServlet">Registrati</a></li>
                      <li><a class="dropdown-item" href="/TheOtherSpace/LogInServlet">Login</a></li>
                     <% } %>
                    </ul>

                </div>
                <span id="search-icon" class="material-icons">&#xe8b6;</span>
            </div>
        </header>

        
        
        
    </div>
    <h1>Checkout Summary</h1>
    <% List<Ticket> blockedTicket = (List<Ticket>)request.getAttribute("blockedTicket");%>
    <div class="checkout">
        <p>Price: <%= request.getAttribute("price") %></p>
        <p>Screening Date and Time: <%= request.getAttribute("screeningDateTimeVariable") %></p>
        <p>Ticket Number: <%= request.getAttribute("ticketNumber") %></p>
        <p>Seats:</p>
        <ul>
            <% 
                List<Integer> seats = (List<Integer>)request.getAttribute("seats");
                for (Integer seat : seats) { 
            %>
                <p>Seat: <%= seat %></p>
            <% } %>
        </ul>
        <form action="ConfirmationServlet" method="post">
		    <% 
		        int i = 0;
		        for (Ticket ticket : blockedTicket) { 
		    %>
		        <input type="hidden" name="blockedTicketSize" value="<%= blockedTicket.size() %>">
		        <input type="hidden" name="blockedTicketPrice<%= i %>" value="<%= ticket.getPrice() %>">
		        <input type="hidden" name="blockedTicketSeat<%= i %>" value="<%= ticket.getSeat() %>">
		        <input type="hidden" name="blockedTicketUserId<%= i %>" value="<%= ticket.getUser().getId() %>">
		        <input type="hidden" name="blockedTicketScreening<%= i %>" value="<%= ticket.getScreening().getId() %>">
		    <% 
		        i++;
		        } 
		    %>
		    <button type="submit">Confirm Checkout</button>
		</form>

    </div>
</body>
<footer>
    <div class="footer-content">
        <div class="contact-section">
            <h4>CONTATTI</h4>
            <ul>
                <li>FAQ e Contattaci</li>
            </ul>
        </div>
        <div class="space-cinema-section">
            <h4>THE OTHER SPACE CINEMA</h4>
            <ul>
                <li>Chi siamo</li>
                <li>PNRR</li>
                <li>Lavora con noi</li>
                <li>I nostri cinema</li>
            </ul>
        </div>
        <div class="legal-section">
            <h4>NOTE LEGALI</h4>
            <ul>
                <li>Privacy policy</li>
                <li>Cookie policy</li>
                <li>Condizioni di utilizzo</li>
                <li>Regolamenti</li>
            </ul>
        </div>
        <div class="social-section">
            <h4>SOCIAL</h4>
            <ul>
                <li>Facebook</li>
                <li>Youtube</li>
                <li>Twitter</li>
                <li>Instagram</li>
            </ul>
        </div>
        <div class="app-section">
            <h4>LA NOSTRA APP</h4>
            <p>Scopri di più</p>
        </div>
    </div>
</footer>
</html>
