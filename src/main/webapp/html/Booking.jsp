<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Booking.css">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <div class="seats-container">
            <form action="CheckOutServlet" method="post">
                <% 
                    int seatsVariable = (int)request.getAttribute("seatsVariable"); 
                	double price = (double)request.getAttribute("price");
                	LocalDateTime screeningDateTimeVariable = (LocalDateTime)request.getAttribute("screeningDateTimeVariable");
                	Long showing_idVariable = (Long)request.getAttribute("showing_idVariable");
                	
                    for (int i = 1; i <= seatsVariable; i++) { 
                %>
                    <div class="seat">
                        <input type="checkbox" id="seat<%= i %>" name="seat<%= i %>" value="<%= i %>">
                        
                    </div>
                <% } %>
                <input type="hidden" name="price" value="<%= price %>">
    			<input type="hidden" name="screeningDateTimeVariable" value="<%= screeningDateTimeVariable %>">
    			<input type="hidden" name="showing_idVariable" value="<%= showing_idVariable %>">
                <button type="submit">Submit</button>
            </form>
        </div>
        <p><%= price %></p>
        <p><%= screeningDateTimeVariable %></p>
        <p><%= showing_idVariable %></p>
    </div>
</body>
</html>


