<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrazione</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/SignUpStyle.css">
    </head>
    <body>
        <div class="container_total">
            <div class="container_signup">
                <h2 id="title">SIGN UP</h2>
                <form action="<%= request.getContextPath() %>/SignUpServlet" method="POST">
                    <div class="container_input">
                    	<label for="firstName">Nome:</label>
                        <input type="text" id="name" name="firstName" autofocus required>
                        <label for="lastName">Cognome:</label>
                        <input type="text" id="surname" name="lastName"  required>
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username"  required>
                        <label for="email">Email:</label>
                        <input type="text" id="email" name="email" required>
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required >
                        <label for="dob">Data di nascita:</label>
                        <input type="date" id="dob" name="dob" required>
                    </div>
                    <div class="container_button">
                        <button type="submit" id="button_signin">Registrati</button>
                    </div>
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
        
    </body>
</html>