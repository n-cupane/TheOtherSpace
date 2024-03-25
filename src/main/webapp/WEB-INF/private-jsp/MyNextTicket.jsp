<%@page import="com.theotherspace.model.Screening"%>
<%@page import="com.theotherspace.model.User"%>
<%@page import="com.theotherspace.model.Ticket"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/MyAccount.css"/>
</head>
<body>
	<% Ticket userNextTicket = (Ticket) request.getAttribute("nextTicket"); 
	   User activeUser = (User) request.getAttribute("activeUser");
	   Screening nextScreening = (Screening) request.getAttribute("nextScreening");
	%>
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

        
        
        <div class="container_movies" id="container_movies">
	            <a href="AccountInfoServlet">Vai a AccountInfoServlet</a>
				<a href="MyNextTicketServlet">Vai a MyNextTicket</a>
    	</div>
    </div>
	<div class="container-next-ticket">
		<div class="nextTicket">
			<form id="myNextTicket" action="AltraServlet" method="POST"> 
			        <label for="userFirstName">User First Name:</label>
			        <input type="text" id="userFirstName" name="userFirstName" value="<%= activeUser.getFirstName() %>" readonly><br>
			        
			        <label for="userLastName">User Last Name:</label>
			        <input type="text" id="userLastName" name="userLastName" value="<%= activeUser.getLastName() %>" readonly><br>
			        
			        <label for="screeningDateTime">Screening Date Time:</label>
			        <input type="text" id="screeningDateTime" name="screeningDateTime" value="<%= nextScreening.getDateTime() %>" readonly><br>
			        
			        <label for="price">Prezzo:</label>
			        <input type="text" id="price" name="price" value="<%= userNextTicket.getPrice() %>" readonly><br>
			        
			        <label for="seat">Posto:</label>
			        <input type="text" id="seat" name="seat" value="<%= userNextTicket.getSeat() %>" readonly><br>
			        
			        <input type="hidden" name="movieId" value="<%= nextScreening.getMovieId() %>">
			        <button type="submit">Dettagli biglietto</button>
			    </form>
		</div>
		<div>
			<form action="MyTicketServlet" method="POST">
				<button type="submit">I miei Biglietti</button>
			</form>
		</div>
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
            <h4>THE SPACE CINEMA</h4>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</html>