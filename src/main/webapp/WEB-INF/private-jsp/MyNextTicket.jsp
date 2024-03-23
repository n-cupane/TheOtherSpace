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
</head>
<body>
	<% Ticket userNextTicket = (Ticket) request.getAttribute("nextTicket"); 
	   User activeUser = (User) request.getAttribute("activeUser");
	   Screening nextScreening = (Screening) request.getAttribute("nextScreening");
	%>
	<div class="container">
		<div class="nextTicket">
			<form action="AltraServlet" method="POST"> 
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
</html>