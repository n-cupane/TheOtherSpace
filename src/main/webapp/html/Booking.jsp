<%@page import="java.util.List"%>
<%@page import="java.time.LocalDateTime"%>
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
    <title>Document</title>
</head>
<body>
	<div id="container" class="container-fluid">

        <header id="header" class="row justify-content-center">
            
            <div class="col-2 d-flex align-items-end justify-content-end">
                <a href="<%=request.getContextPath()%>/HomePageServlet"><img src="<%=request.getContextPath()%>/res/logo_other_space.png" alt="Logo The Other" id="logo_other_space"></a>
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
	            <div class="screen">
	            
	            </div>
    	</div>
    </div>
    <div class="container-room">
        <div class="seats-container">
            <form action="<%= request.getContextPath() %>/CheckOutServlet" method="post">
                <% 
                    int seatsVariable = (int)request.getAttribute("seatsVariable"); 
                	double price = (double)request.getAttribute("price");
                	String imgMovie = (String)request.getAttribute("imgMovie");
                	LocalDateTime screeningDateTimeVariable = (LocalDateTime)request.getAttribute("screeningDateTimeVariable");
                	Long showing_idVariable = (Long)request.getAttribute("showing_idVariable");
                	List<Integer> ticketForScreaningBlocked = (List<Integer>)request.getAttribute("ticketForScreaningBlocked");
                	
                    for (int i = 1; i <= seatsVariable; i++) {
                        boolean isBlocked = ticketForScreaningBlocked.contains(i); // Verifico se il posto è bloccato
                %>
                    <div class="seat">
                        <input type="checkbox" id="seat<%= i %>" name="seat<%= i %>" value="<%= i %>" <%= isBlocked ? "disabled" : "" %>>
                    </div>
                <% } %>
                <input type="hidden" name="price" value="<%= price %>">
    			<input type="hidden" name="screeningDateTimeVariable" value="<%= screeningDateTimeVariable %>">
    			<input type="hidden" name="showing_idVariable" value="<%= showing_idVariable %>">
    			<input type="hidden" name="seatsVariable" value="<%= seatsVariable %>">
                <button id="conferma" type="submit">Conferma Selezione</button>
            </form>
        </div>
    </div>
    <div class ="testo-img">
    	<div class="img">
    		<img alt="" src="<%=imgMovie%>">
    	</div>
    <div class="room-movie-info">
    	<p>Prezzo del singolo biglietto: <%= price %></p><br>
        <p>Orario e data della proiezione selezionata: <%= screeningDateTimeVariable %></p><br>
        <p><%= showing_idVariable %></p><br>
    </div>
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
</footer>
</html>
