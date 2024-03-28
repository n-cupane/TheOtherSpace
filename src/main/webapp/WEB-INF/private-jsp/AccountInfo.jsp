<%@page import="com.theotherspace.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica Dati Utente</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/MyAccount.css"/>
</head>
<body>
<div id="container" class="container-fluid">

        <header id="header" class="row justify-content-center">
            
            <div class="col-2 d-flex align-items-end justify-content-end">
                <a href="<%=request.getContextPath()%>/HomePageServlet"><img src="<%=request.getContextPath()%>/res/logo_other_space.png" alt="Logo The Other" id="logo_other_space"></a>
            </div>
            
            <nav class="col-3 d-flex align-items-center justify-content-evenly">

                <a href="#container_movies">FILM</a>
                

            </nav>

            <div class="col-2 d-flex align-items-center">

                <div class="dropdown">
                    
                    <button id="btn" class="btn btn-secondary dropdown-toggle d-flex justify-content-evenly align-items-center" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                      <span id="person-img" class="material-icons">&#xe7fd;</span>
                      <% if((Boolean)request.getAttribute("isLoggedIn")){ %>
                      	<p><%= session.getAttribute("loggedInUser") %></p>
                   	 <% }else{ %>
                   	 	<p>Login</p>
                     <% } %>
                     
                      
                    </button>
                    <ul class="dropdown-menu">
                    <% if((Boolean)request.getAttribute("isLoggedIn")){ %>
                    <!-- Se l'utente è loggato, mostra solo l'username e l'opzione Logout -->
                      <li><a class="dropdown-item" href="<%= request.getContextPath() %>/MyAccountServlet">My account</a></li>
                      <li><a class="dropdown-item" href="<%= request.getContextPath() %>/LogoutServlet">Logout</a></li>
                    <% }else{ %>
                     <!-- Se l'utente non è loggato, mostra le opzioni Signin e Login -->
                      <li><a class="dropdown-item" href="<%= request.getContextPath() %>/SignUpServlet">Registrati</a></li>
                      <li><a class="dropdown-item" href="<%= request.getContextPath() %>/LogInServlet">Login</a></li>
                     <% } %>
                    </ul>

                </div>
                <span id="search-icon" class="material-icons">&#xe8b6;</span>
            </div>
        </header>

        
        
        <div class="container_movies" id="container_movies">
	            <a href="<%= request.getContextPath() %>/MyAccountServlet">La mia Card</a>
	            <a href="<%= request.getContextPath() %>/AccountInfoServlet">Le mie info</a>
				<a href="<%= request.getContextPath() %>/MyNextTicketServlet">Le mie proiezioni</a>
    	</div>
    </div>
    <div class="form">
    	<h2>Modifica Dati Utente</h2>
    <% User activeUser = (User)request.getAttribute("activeUser"); %>
    <form action="<%= request.getContextPath() %>/AccountInfoServlet" method="post">
        <input type="hidden" name="userId" value="<%= activeUser.getId() %>">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= activeUser.getUsername() %>"><br>
        
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="<%= activeUser.getFirstName() %>"><br>
        
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="<%= activeUser.getLastName() %>"><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= activeUser.getEmail() %>"><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="<%= activeUser.getPassword() %>"><br>
        
        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" value="<%= activeUser.getDateOfBirth() %>"><br>
        
        <button type="submit">Salva le modifiche</button>
    </form>
    <div class="delete">
    	<h1>Cancellazione dell'account</h1>
	    <p>Se vuoi cancellare il tuo account, 
	    <a href="<%= request.getContextPath() %>/AccountDeletionServlet" onclick="return confirm('Sei sicuro di voler cancellare il tuo account?')">clicca qui</a>
	    </p>
	    <p>Eliminando il tuo account non potrai usufruire dei servizi The Other Space Cinema.</p>
    </div>
    </div>
    	
    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    
</body>
<footer>
    <div class="footer-content">
        <div class="contact-section">
            <h4>CONTATTI</h4>
            <ul>
                <li>FAQ e Contattaci</li>
            </ul>
        </div>
        <div class="space-cinema-section">
            <h4>THE OTHER SPACE CINEMA</h4>
            <ul>
                <li>Chi siamo</li>
                <li>PNRR</li>
                <li>Lavora con noi</li>
                <li>I nostri cinema</li>
            </ul>
        </div>
        <div class="legal-section">
            <h4>NOTE LEGALI</h4>
            <ul>
                <li>Privacy policy</li>
                <li>Cookie policy</li>
                <li>Condizioni di utilizzo</li>
                <li>Regolamenti</li>
            </ul>
        </div>
        <div class="social-section">
            <h4>SOCIAL</h4>
            <ul>
                <li>Facebook</li>
                <li>Youtube</li>
                <li>Twitter</li>
                <li>Instagram</li>
            </ul>
        </div>
        <div class="app-section">
            <h4>LA NOSTRA APP</h4>
            <p>Scopri di più</p>
        </div>
    </div>
</footer>


</html>
