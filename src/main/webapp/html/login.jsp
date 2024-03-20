<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/LoginStyle.css">
    </head>
    <body>
        <div class="container_total">
            <div class="container_login">
                <h2 id="title">LOGIN</h2>
                <form action="LogInServlet" method="POST">
                    <div class="container_input">
                        <input type="text" id="email" name="email" placeholder="E-mail" required><br><br>
                        <input type="password" id="password" name="password" placeholder="Password" ><br><br>
                    </div>
                    <div class="container_button">
                        <button type="submit" id="button_login">Login</button>
                    </div>
                </form>
            </div>
            <% boolean wrongCredential = (boolean)request.getAttribute("wrongCredential");
               if(wrongCredential){%>
               <p>Credenziali Errate</p>
               <%}%>
        </div>
        
    </body>
</html>