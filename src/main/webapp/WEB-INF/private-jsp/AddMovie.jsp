<%@page import="com.theotherspace.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Other Space</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath() + "/css/MovieSearchStyle.css"%>">
</head>
<body>
    <div id="container" class="container-fluid">
        <%
        String title = (String) request.getAttribute("title");
        String description = (String) request.getAttribute("description");
        String genre = (String) request.getAttribute("genre");
        String duration = (String) request.getAttribute("duration");
        String imageUrl = (String) request.getAttribute("imageUrl");
        Boolean over18 = (Boolean) request.getAttribute("over18");
        Long oldMovieId = (Long) request.getAttribute("oldMovieId");
        %>

        <form action="<%= request.getContextPath() %>/AddMovieServlet" method="POST" id="movie-to-add-container" class="container">

            <div class="add-element">
                <label for="add-title">Titolo:</label>
                <%
                if (title != null) {
                %>
                	<input type="text" name="add-title" class="add-title" value="<%=title %>" required>
                <%
                } else {
                %>
                	<input type="text" name="add-title" class="add-title" required>
                <%
                }
                %>
            </div>

            <div class="add-element" id="add-element-description">
                <label for="add-description">Descrizione:</label>
                <%
                if (description != null) {
                %>
           		     <textarea name="add-description" id="add-description" cols="30" rows="10" required><%=description %></textarea>
                <%
                } else {
                %>
              	 	 <textarea name="add-description" id="add-description" cols="30" rows="10" required></textarea>
                <%
                }
                %>
            </div>

            <div class="add-element">
                <label for="add-genre">Genere:</label>
                <%
                if (genre != null) {
                %>
                	<input type="text" name="add-genre" class="add-genre" value="<%=genre %>" required>
                <%
                } else {
                	%>
                	<input type="text" name="add-genre" class="add-genre" required>
                	<%
                }
                %>
            </div>
            
            <div class="add-element">
                <label for="add-duration">Durata:</label>
                <%
                if (duration != null) {
                %>
                	<input type="text" name="add-duration" class="add-duration" value="<%=duration %>" required>
                <%
                } else {
                	%>
                	<input type="text" name="add-duration" class="add-duration" required>
                	<%
                }
                %>
            </div>
            
            <div class="add-element">
                <label for="add-image-url">Percorso immagine:</label>
                <%
                if (imageUrl != null) {
                %>
                	<input type="text" name="add-image-url" class="add-image-url" value="<%=imageUrl %>" required>
                <%
                } else {
                %>
                	<input type="text" name="add-image-url" class="add-image-url" required>
                <%
                }
                %>
            </div>

            <div class="add-element">
                <label for="add-over-18">Per adulti:</label>
                <%
                if (over18) {
                %>
                	<input type="checkbox" name="add-over-18" id="add-over-18" checked>
                <%
                } else {
                	%>
                	<input type="checkbox" name="add-over-18" id="add-over-18">
                	<%
                }
                %>
            </div>

			<input name="oldMovieId" style="display:none" value="<%=oldMovieId %>">
            <input id="add-btn" type="submit" value="AGGIUNGI">

        </form>

        
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>