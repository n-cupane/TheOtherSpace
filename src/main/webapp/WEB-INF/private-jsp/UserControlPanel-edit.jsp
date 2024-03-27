<%@page import="com.theotherspace.model.User"%>
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
	<%
		User user = (User) request.getAttribute("userToEdit");
	%>

    <div id="container" class="container-fluid">

        <div id="left-menu">

            <h3>Pannello di controllo</h3>
            <span id="below-h3"></span>
    
            <a href="http://localhost:8080/TheOtherSpace/UserControlPanelServlet" class="left-menu-element">
            <span class="material-icons">&#xe7ef;</span>
            <h5>Utenti</h5>
        </a>

        <a href="http://localhost:8080/TheOtherSpace/MovieControlPanelServlet" class="left-menu-element">
            <span class="material-icons">&#xe02c;</span>
            <h5>Film</h5>
        </a>

        <a href="http://localhost:8080/TheOtherSpace/TheaterControlPanelServlet" class="left-menu-element current">
            <span class="material-icons">&#xefed;</span>
            <h5>Sale</h5>
        </a>
    
            <a id="logout" href="">logout</a>
    
        </div>
        
        <div id="main-content">

            <form action="http://localhost:8080/TheOtherSpace/UserControlPanelEditServlet" method="POST" id="movie-to-add-container" class="container">

                <div class="add-element">
                    <label for="add-username">Username:</label>
                    
                        <input value="<%=user.getUsername() %>" type="text" name="add-username" class="add-username" required>
                    
                </div>

                

                <div class="add-element">
                    <label for="add-first-name">Nome:</label>
                    
                        <input value="<%=user.getFirstName() %>" type="text" name="add-first-name" class="add-first-name" required>
                
                </div>
                
                <div class="add-element">
                    <label for="add-last-name">Cognome:</label>
                
                        <input value="<%=user.getLastName() %>" type="text" name="add-last-name" class="add-last-name" required>
    
                </div>
                
                <div class="add-element">
                    <label for="add-email">Email:</label>
                
                        <input value="<%=user.getEmail() %>" type="text" name="add-email" class="add-email" required>

                </div>

                <div class="add-element">
                    <label for="add-password">Password:</label>
                
                        <input value="<%=user.getPassword() %>" type="text" name="add-password" class="add-password" required>

                </div>

                <div class="add-element">
                    <label for="add-dob">Data di nascita:</label>
                
                        <input value="<%=user.getDateOfBirth() %>" type="date" name="add-dob" class="add-dob" required>

                </div>
                
                <input name="add-id" type="text" value="<%=user.getId()%>" style="display:none">

                <input id="add-btn" type="submit" value="CONFERMA MODIFICHE">
                
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