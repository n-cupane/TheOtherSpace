<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Recupero Password</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/LoginStyle.css">
    </head>
    <body>
        <div class="container_total">
            <div class="container_login">
                <h2 id="title">RECUPERO PASSWORD</h2>
                <form action="<%= request.getContextPath() %>/PasswordForgottenServlet" method="POST">
                    <div class="container_input">
                        <input type="text" id="email" name="email" placeholder="E-mail" required><br><br>
                    </div>
                    <div class="container_button">
                        <button type="submit" id="button_login">Reset password</button>
                    </div>
                </form>
            </div>
        </div>
        
    </body>
</html>