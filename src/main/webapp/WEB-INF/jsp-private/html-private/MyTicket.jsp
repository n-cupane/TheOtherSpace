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
</head>
<body>
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
                String userFirstName = (String)request.getAttribute("userFirstName");
                String userLastName = (String)request.getAttribute("userLastName");
        		// Prelevo l'id dell'utente
                long userId = (long)request.getAttribute("userId");
                
                //eseguoi i controlli sulle liste
                if(userTicket != null && screeningUser != null) {
                    for(int i = 0; i < userTicket.size(); i++) {
                        Ticket ticket = userTicket.get(i);
                        Screening screening = screeningUser.get(i);
                        if (screening != null && ticket != null) {
                            LocalDateTime screeningDateTime = screening.getDateTime();
                            LocalDateTime currentDateTime = LocalDateTime.now();
                            if (screeningDateTime.isAfter(currentDateTime)) { // Proiezione non ancora avvenuta
            %>
            <div class="ticket">
			    <form action="AltraServlet" method="POST"> <!-- Sostituisci "AltraServlet" con il nome della tua servlet di destinazione -->
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
			        
			        <input type="hidden" name="movieId" value="<%= screening.getMovieId() %>">
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
                        Screening screening = screeningUser.get(i);
                        if (screening != null && ticket != null) {
                            LocalDateTime screeningDateTime = screening.getDateTime();
                            LocalDateTime currentDateTime = LocalDateTime.now();
                            if (screeningDateTime.isBefore(currentDateTime)) { // Proiezione già avvenuta
            %>
            <div class="ticket">
			    <p>User First Name: <%= userFirstName %></p>
			    <p>User Last Name: <%= userLastName %></p>
			    <p>Screening Date Time: <%= screeningDateTime %></p>
			    <p>Prezzo: <%= ticket.getPrice() %></p>
			    <p>Posto: <%= ticket.getSeat() %></p>
			    <form action="ReviewServlet" method ="POST">
			        <input type="hidden" name="userId" value="<%= userId %>">
			        <input type="hidden" name="movieId" value="<%= screening.getMovieId() %>">
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
</body>
</html>
