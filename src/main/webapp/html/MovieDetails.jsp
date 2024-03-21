<%@page import="com.theotherspace.model.Screening"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Other Space</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/MovieDetailsStyle.css"/>
</head>
<body>
    <div id="container" class="container-fluid">

        <header id="header" class="row justify-content-center">
            
            <div class="col-2 d-flex align-items-end justify-content-end">
                <img src="<%=request.getContextPath()%>/res/logo_other_space.png" alt="Logo The Other" id="logo_other_space">
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
                      <li><a class="dropdown-item" href="#"><%= session.getAttribute("loggedInUser") %></a></li>
                      <li><a class="dropdown-item" href="/TheOtherSpace/LogoutServlet">Logout</a></li>
                    <% }else{ %>
                     <!-- Se l'utente non è loggato, mostra le opzioni Signin e Login -->
                      <li><a class="dropdown-item" href="/TheOtherSpace/SignUpServlet">Registrati</a></li>
                      <li><a class="dropdown-item" href="/TheOtherSpace/LogInServlet">Login</a></li>
                     <% } %>
                    </ul>

                </div>
                <span id="search-icon" class="material-icons">&#xe8b6;</span>
            </div>
        </header>

                
        <div id="movie-container">
        <%
        String movieTitle = (String) request.getAttribute("movieTitle");
        boolean over18 = (boolean) request.getAttribute("over18");
        String movieDescription = (String) request.getAttribute("movieDescription");
        String movieGenre = (String) request.getAttribute("movieGenre");
        List<Screening> screeningsOfMovie = (List<Screening>) request.getAttribute("screeningsOfMovie");
        %>
        
        <div id="left-movie-container">
        	<img id="movie-image" src=https://image.tmdb.org/t/p/w600_and_h900_bestv2/zozGfM5kO9qbx1XSvwDwrh6yTca.jpg>
        </div>
        
        <div id="right-movie-container">
        
        	<form action="" method="post" id="movie-form">
        	<h1 id="movie-title"><%=movieTitle.toUpperCase() %></h1>
        	
        	<div id="below-title">
        	<%
        		if(over18) {
        	%>
        		<img id="over-18" src="<%=request.getContextPath()%>/res/number-18.png">
        	<%
        		} else {
        	%>
           		<img id="over-18" src="<%=request.getContextPath()%>/res/eighteen.png">
           	<%
        		}
        	%>
        	<p id="movie-genre"><%=movieGenre %></p>
        	</div>
        	 
        	<p id="movie-description"><%=movieDescription %></p>
        	
        	<select id="screening-select" name="movie-screening" required>
        	<%
        	if (screeningsOfMovie != null && screeningsOfMovie.size() > 0) {
        		for (Screening screening: screeningsOfMovie) {
        			%>
        			<option value="<%=screening.getId() %>"><%=screening.toString() %></option>
        			<%
        		}
        	}
        	%>     	
        	</select>
        	
        	<input type="submit" id="submit-btn" value="SCEGLI IL TUO POSTO">
        	
        	</form>
        	
        </div>
         
        </div>
        
        
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>