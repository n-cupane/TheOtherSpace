<%@page import="com.theotherspace.model.Theater"%>
<%@page import="com.theotherspace.utilities.BusinessLogic"%>
<%@page import="com.theotherspace.model.Movie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
    <link rel="stylesheet" href="<%=request.getContextPath() + "/css/ControlPanelAddUserStyle.css"%>">
</head>
<body>
    <div id="container" class="container-fluid">

        <div id="left-menu">

            <h3>Pannello di controllo</h3>
            <span id="below-h3"></span>
    
            <a href="<%= request.getContextPath() %>/UserControlPanelServlet" class="left-menu-element">
	            <span class="material-icons">&#xe7ef;</span>
	            <h5>Utenti</h5>
	        </a>
	
	        <a href="<%= request.getContextPath() %>/MovieControlPanelServlet" class="left-menu-element">
	            <span class="material-icons">&#xe02c;</span>
	            <h5>Film</h5>
	        </a>
	
	        <a href="<%= request.getContextPath() %>/TheaterControlPanelServlet" class="left-menu-element">
	            <span class="material-icons">&#xefed;</span>
	            <h5>Sale</h5>
	        </a>
	        
	        <a href="<%= request.getContextPath() %>/ScreeningControlPanelServlet" class="left-menu-element current">
            <span class="material-icons">&#xe04b;</span>
            <h5>Proiezioni</h5>
        </a>
    
            <a id="logout" href="<%=request.getContextPath() %>/LogoutServlet">logout</a>
    
        </div>
        
        <div id="main-content">

            <form action="<%= request.getContextPath() %>/ScreeningControlPanelAddServlet" method="POST" id="movie-to-add-container" class="container">

                <div class="add-element">
                    <label for="add-movie-id">Film:</label>
                    
                        <select name="add-movie-id" class="add-first-name" required>
                        <%
                        for (Movie movie: BusinessLogic.findAllMovies()) {
                        	%>
                        		<option value="<%=movie.getId() %>"><%=movie.getTitle() %></option>
                        	<%
                        }
                        %>                        
                        </select>
                </div>

                

                <div class="add-element">
                    <label for="add-theater-id">Sala:</label>
                    
                        <select name="add-theater-id" class="add-first-name" required>
                        <%
                        for (Theater theater: BusinessLogic.findAllTheaters()) {
                        	%>
                        		<option value="<%=theater.getId() %>"><%=theater.getNumber() %></option>
                        	<%
                        }
                        %>                        
                        </select>
                </div>
                
                <div class="add-element">
                    <label for="add-date-time">Data e ora:</label>
                    <input type="datetime-local" name="add-date-time" class="add-first-name" required>
                </div>

                <input id="add-btn" type="submit" value="AGGIUNGI PROIEZIONE">
                
                <%
				    	String errorMsg = (String) request.getAttribute("errorMsg");
                    	if (errorMsg != null) {
                    		%>
                        	<p id="error-msg"><%=errorMsg %></p>
                        	<%
                    	}
				    %>

            </form>
        </div>
        
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>