<%@page import="com.theotherspace.model.User"%>
<%@page import="com.theotherspace.utilities.BusinessLogic"%>
<%@page import="com.theotherspace.model.Screening"%>
<%@page import="com.theotherspace.model.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Ticket</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/MyNextTicket.css"/>
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
                      	<p><%= ((User)request.getSession().getAttribute("activeUser")).getUsername() %></p>
                   	 <% }else{ %>
                   	 	<p>Login</p>
                     <% }  %>
                     
                      
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
                <span id="search-icon" class="material-icons">&#xe8b6;</span>
            </div>
        </header>

        
        
        <div class="container_movies" id="container_movies">
	            <a href="<%= request.getContextPath() %>/MyAccountServlet">La mia Card</a>
	            <a href="<%= request.getContextPath() %>/AccountInfoServlet">Le mie info</a>
				<a href="<%= request.getContextPath() %>/MyNextTicketServlet">Le mie proiezioni</a>
    	</div>
    </div>
    <div class="container">
        <h1>Lista Ticket</h1>
        
        <h2>Proiezioni Non Ancora Avvenute</h2>
        <div class="tickets-not-occurred">
            <% 
            	// Prelevo la lista di ticket associati all'utente
                List<Ticket> userTicket = (List<Ticket>)request.getAttribute("userTicket");
        		// Prelevo la lista delle proiezioni legate al ticket
                List<Screening> screeningUser = (List<Screening>)request.getAttribute("screeningUser");
        		// Prelevo il nome e il cognome dell'utente
        		// Prelevo il nome e il cognome dell'utente
                String userFirstName = (String)request.getAttribute("userFirstName");
                String userLastName = (String)request.getAttribute("userLastName");
        		// Prelevo l'id dell'utente
                long userId = (long)request.getAttribute("userId");
                
                //eseguoi i controlli sulle liste
                if(userTicket != null && screeningUser != null) {
                    for(int i = 0; i < userTicket.size(); i++) {
                        Ticket ticket = userTicket.get(i);
                        Screening screeningTicket = BusinessLogic.findScreeningById(ticket.getScreening().getId());
                        if (screeningTicket != null && ticket != null) {
                            LocalDateTime screeningDateTime = screeningTicket.getDateTime();
                            LocalDateTime currentDateTime = LocalDateTime.now();
                            if (screeningDateTime.isAfter(currentDateTime)) { // Proiezione non ancora avvenuta
            %>
            <div class="ticket">
			    <form id="myNextTicket" action="<%= request.getContextPath() %>/AltraServlet" method="POST"> 
			        <label for="userFirstName">User First Name:</label>
			        <input type="text" id="userFirstName" name="userFirstName" value="<%= userFirstName %>" readonly><br>
			        
			        <label for="userLastName">User Last Name:</label>
			        <input type="text" id="userLastName" name="userLastName" value="<%= userLastName %>" readonly><br>
			        
			        <label for="screeningDateTime">Screening Date Time:</label>
			        <input type="text" id="screeningDateTime" name="screeningDateTime" value="<%= screeningDateTime %>" readonly><br>
			        
			        <label for="price">Prezzo:</label>
			        <input type="text" id="price" name="price" value="<%= ticket.getPrice() %>" readonly><br>
			        
			        <label for="seat">Posto:</label>
			        <input type="text" id="seat" name="seat" value="<%= ticket.getSeat() %>" readonly><br>
			        
			        <input type="hidden" name="movieId" value="<%= screeningTicket.getMovie().getId() %>">
			        <button type="submit">Dettagli biglietto</button>
			    </form>
			</div>

            <% 
                            }
                        }
                    }
                } else {
            %>
            <p>Nessun ticket disponibile.</p>
            <% 
                }
            %>
        </div>
        
        <h2>Proiezioni Avvenute</h2>
        <div class="tickets-occurred">
            <% 
                if(userTicket != null && screeningUser != null) {
                    for(int i = 0; i < userTicket.size(); i++) {
                        Ticket ticket = userTicket.get(i);
                        Screening screeningTicket = BusinessLogic.findScreeningById(ticket.getScreening().getId());
                        if (screeningTicket != null && ticket != null) {
                            LocalDateTime screeningDateTime = screeningTicket.getDateTime();
                            LocalDateTime currentDateTime = LocalDateTime.now();
                            if (screeningDateTime.isBefore(currentDateTime)) { // Proiezione già avvenuta
            %>
            <div class="ticket">
			    <form id="myNextTicket" action="<%= request.getContextPath() %>/AltraServlet" method="POST"> 
			        <label for="userFirstName">User First Name:</label>
			        <input type="text" id="userFirstName" name="userFirstName" value="<%= userFirstName %>" readonly><br>
			        
			        <label for="userLastName">User Last Name:</label>
			        <input type="text" id="userLastName" name="userLastName" value="<%= userLastName %>" readonly><br>
			        
			        <label for="screeningDateTime">Screening Date Time:</label>
			        <input type="text" id="screeningDateTime" name="screeningDateTime" value="<%= screeningDateTime %>" readonly><br>
			        
			        <label for="price">Prezzo:</label>
			        <input type="text" id="price" name="price" value="<%= ticket.getPrice() %>" readonly><br>
			        
			        <label for="seat">Posto:</label>
			        <input type="text" id="seat" name="seat" value="<%= ticket.getSeat() %>" readonly><br>
			        
			        <input type="hidden" name="movieId" value="<%= screeningTicket.getMovie().getId() %>">
			        <button type="submit">Dettagli biglietto</button>
			    </form>
			    <form action="<%= request.getContextPath() %>/ReviewServlet" method ="POST">
			        <input type="hidden" name="userId" value="<%= userId %>">
			        <input type="hidden" name="movieId" value="<%= screeningTicket.getMovie().getId() %>">
			        <button type="submit">Scrivi Recensione</button>
			    </form>
			</div>

            <% 
                            }
                        }
                    }
                } else {
            %>
            <p>Nessun ticket disponibile.</p>
            <% 
                }
            %>
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

</html>
