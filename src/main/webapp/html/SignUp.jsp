<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Signin</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/SignUpStyle.css">
    </head>
    <body>
        <div class="container_total">
            <div class="container_signup">
                <h2 id="title">SIGN UP</h2>
                <form action="http://localhost:8080/TheOtherSpace/SignUpServlet" method="POST">
                    <div class="container_input">
                        <input type="text" id="name" name="firstName" placeholder="Nome"  required><br><br>
                        <input type="text" id="surname" name="lastName" placeholder="Cognome"  required><br><br>
                        <input type="text" id="username" name="username" placeholder="Username"  required><br><br>
                        <input type="text" id="email" name="email" placeholder="E-mail" required><br><br>
                        <input type="password" id="password" name="password" placeholder="Password" ><br><br>
                        <input type="date" id="dob" name="dob"><br><br>
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