<%@page import="com.theotherspace.model.Ticket"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout Summary</title>
</head>
<body>
    <h1>Checkout Summary</h1>
    <% List<Ticket> blockedTicket = (List<Ticket>)request.getAttribute("blockedTicket");%>
    <div>
        <p>Price: <%= request.getAttribute("price") %></p>
        <p>Screening Date and Time: <%= request.getAttribute("screeningDateTimeVariable") %></p>
        <p>Ticket Number: <%= request.getAttribute("ticketNumber") %></p>
        <p>Seats:</p>
        <ul>
            <% 
                List<Integer> seats = (List<Integer>)request.getAttribute("seats");
                for (Integer seat : seats) { 
            %>
                <li>Seat: <%= seat %></li>
            <% } %>
        </ul>
        <form action="ConfirmationServlet" method="post">
            <input type="hidden" name="blockedTicket" value="<%= blockedTicket %>">
            <button type="submit">Confirm Checkout</button>
        </form>
    </div>
</body>
</html>
