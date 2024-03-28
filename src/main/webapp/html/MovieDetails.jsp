<%@page import="java.sql.Date"%>
<%@page import="com.theotherspace.model.User"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.theotherspace.model.Review"%>
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
                      	<p><%= ((User)request.getSession().getAttribute("activeUser")).getUsername() %></p>
                   	 <% }else{ %>
                   	 	<p>Login</p>
                     <% } %>
                     
                      
                    </button>
                    <ul class="dropdown-menu">
                    <% if((Boolean)request.getAttribute("isLoggedIn")){ %>
                    <!-- Se l'utente è loggato, mostra solo l'username e l'opzione Logout -->
                      <li><a class="dropdown-item" href="<%= request.getContextPath() %>/AccountInfoServlet">My Account</a></li>
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

                
        <div id="movie-container">
        <%
        String movieTitle = (String) request.getAttribute("movieTitle");
        boolean over18 = (boolean) request.getAttribute("over18");
        String movieDescription = (String) request.getAttribute("movieDescription");
        String movieGenre = (String) request.getAttribute("movieGenre");
        String imgUrl = (String) request.getAttribute("imgUrl");
        List<Screening> screeningsOfMovie = (List<Screening>) request.getAttribute("screeningsOfMovie");
        List<Review> movieReviews = (List<Review>) request.getAttribute("movieReviews");
        %>
        
        <div id="left-movie-container">
        	<img id="movie-image" src="<%=imgUrl %>">
        </div>
        
        <div id="right-movie-container">
        
        	<form action="<%= request.getContextPath() %>/BookingServlet" method="post" id="movie-form">
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
    <div class = "reviews">
    	<%for (Review reviews :  movieReviews) {%>
    	<div class = "container-review">
	    	<div class = "review-user">
	    	    <span class="material-icons">&#xe853;</span>
	    		<p><%=reviews.getUser().getUsername() %> </p>
	    		
	    	</div>
	    	<div class = "review-date">
	    		<p><%=reviews.getDateFormatted() %></p>
	    	</div>
	    	<div class = "review-text">
	    		<p><%=reviews.getText() %></p>
	    	</div>
	    	<div class = "review-rating">
	    		<p>Voto: <%=reviews.getRating() %>/5</p>
	    	</div>
    	</div>
    	<%}%>
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