<%@page import="com.theotherspace.model.User"%>
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
    <title>Riepilogo acquisto</title>
</head>
<body>
	<div id="container" class="container-fluid">

        <header id="header" class="row justify-content-center">
            
            <div class="col-2 d-flex align-items-center justify-content-center">
                <a href="<%=request.getContextPath()%>/HomePageServlet"><img src="<%=request.getContextPath()%>/res/logo_other_space.png" alt="Logo The Other" id="logo_other_space"></a>
            </div>
            
            <nav class="col-3 d-flex align-items-center justify-content-evenly">

                <a href="<%=request.getContextPath()%>/HomePageServlet">HOME</a>
                

            </nav>

            <div class="col-2 d-flex align-items-center">

                <div class="dropdown">
                    
                    <button id="btn" class="btn btn-secondary dropdown-toggle d-flex justify-content-evenly align-items-center" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                      <span id="person-img" class="material-icons">&#xe7fd;</span>
                      <% if((Boolean)request.getAttribute("isLoggedIn")){ %>
                      	<p><%= ((User)request.getSession().getAttribute("activeUser")).getUsername() %></p>
                   	 <% }else{ %>
                   	 	<p>Login</p>
                     <% } %>
                     
                      
                    </button>
                    <ul class="dropdown-menu">
                    <% if((Boolean)request.getAttribute("isLoggedIn")){ %>
                    <!-- Se l'utente è loggato, mostra solo l'username e l'opzione Logout -->
                      <li><a class="dropdown-item" href="<%= request.getContextPath() %>/MyAccountServlet">My account</a></li>
                      <li><a class="dropdown-item" href="<%= request.getContextPath() %>/LogoutServlet">Logout</a></li>
                    <% }else{ %>
                     <!-- Se l'utente non è loggato, mostra le opzioni Signin e Login -->
                      <li><a class="dropdown-item" href="<%= request.getContextPath() %>/SignUpServlet">Registrati</a></li>
                      <li><a class="dropdown-item" href="<%= request.getContextPath() %>/LogInServlet">Login</a></li>
                     <% } %>
                    </ul>

                </div>
                
            </div>
        </header>

        
        
        
    </div>
    <h1>Checkout Summary</h1>
    <% List<Ticket> blockedTicket = (List<Ticket>)request.getAttribute("blockedTicket");
    	boolean haveCard = (boolean)request.getAttribute("haveCard");
    %>
    <div class="checkout">
    		<%if(haveCard) {%>
	        	<p>L'acquisto aggiungera' 100 punti al saldo della tua carta</p>
        	<%} %>
        <p>Price for ticket: <%= request.getAttribute("price") %> x <%= request.getAttribute("ticketNumber") %></p>
        <p>Subtotal: <%=((double)request.getAttribute("price"))*((int)request.getAttribute("ticketNumber"))%> </p>
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
        <form action="<%= request.getContextPath() %>/ConfirmationServlet" method="post">
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
		    <%if(haveCard) {%>
		    	<p>Un saldo inferiore ai 1000 punti comporterà uno sconto di 1 euro ogni 100 punti</p>
		    	<p>Un saldo pari a 1000 punti comporterà l'emissione di un biglietto omaggio</p>
		    	<p>Utilizza il saldo della tua card</p>
	        	<input type="checkbox" name="usePoints" value="true">
	        	<p></p>

        	<%} %>
		    <button type="submit">Confirm Checkout</button>
		</form>

    </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    
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
    <div class="footer-content">
    	<div style="display: flex">
	    	<p>This website uses TMDB and the TMDB APIs but is not endorsed, certified, or otherwise approved by TMDB.</p>
	    	<img style="height: 1.5vw; margin-left: 19.5px; margin-top: 20px" src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_square_1-5bdc75aaebeb75dc7ae79426ddd9be3b2be1e342510f8202baf6bffa71d7f5c4.svg">
    	</div>
    </div>
</footer>
</html>
