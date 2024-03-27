<%@page import="com.theotherspace.utilities.BusinessLogic"%>
<%@page import="com.theotherspace.model.Movie"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.theotherspace.model.User"%>
<%@page import="java.util.List"%>
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
    <link rel="stylesheet" href="<%=request.getContextPath() + "/css/ControlPanelStyle.css"%>">
</head>
<body>

	<%
	List<Movie> movies = (List<Movie>) request.getAttribute("movies");
		Integer movieRemovedId = null;
		movieRemovedId = (Integer) request.getAttribute("movieRemovedId");
		
	%>

    <div id="container" class="container-fluid">

       <div id="left-menu">

        <h3>Pannello di controllo</h3>
        <span id="below-h3"></span>

        <a href="http://localhost:8080/TheOtherSpace/UserControlPanelServlet" class="left-menu-element">
            <span class="material-icons">&#xe7ef;</span>
            <h5>Utenti</h5>
        </a>

        <a href="http://localhost:8080/TheOtherSpace/MovieControlPanelServlet" class="left-menu-element current">
            <span class="material-icons">&#xe02c;</span>
            <h5>Film</h5>
        </a>

        <a href="http://localhost:8080/TheOtherSpace/TheaterControlPanelServlet" class="left-menu-element">
            <span class="material-icons">&#xefed;</span>
            <h5>Sale</h5>
        </a>
        
        <a href="http://localhost:8080/TheOtherSpace/ScreeningControlPanelServlet" class="left-menu-element">
            <span class="material-icons">&#xe04b;</span>
            <h5>Proiezioni</h5>
        </a>

        <a id="logout" href="">logout</a>

       </div>

       <div id="main-content">

            <textarea name="movie-search-bar" id="users-search-bar" rows="1"></textarea>

            <div id="top-users-container">
              
                    <p class="uh-id">ID </p>
                    <p class="uh-fullname">TITOLO</p>
                    <p class="uh-email">GENERE</p>
                    <p class="uhname">DURATA</p>
                    <p class="uh-dob">PER ADULTI</p>
                    <div>
                        <a href="http://localhost:8080/TheOtherSpace/SearchMovieServlet">
                        <span id="add-user" class="material-icons edit">&#xe02c;</span>
                         </a>
                    </div>
              
            </div>
            <div id="users-container">
			<%
			for (Movie movie: movies) {
			%>
                <div class="user">
                    <p class="user-id" name="movie-id"><%=movie.getId() %></p>
                    <p class="user-fullname"><%=movie.getTitle() %></p>
                    <p class="user-email"><%=movie.getGenre().getName() %></p>
                    <p class="username"><%=movie.getDuration() %></p>
                    <p class="user-dob"><%=(movie.isOver18()) ? "SÌ" : "NO" %></p>
                    <a href="http://localhost:8080/TheOtherSpace/AddMovieServlet?movieToEditId=<%=movie.getId()%>">
                        <span class="material-icons edit">&#xe3c9;</span>
                    </a>
                    <form action="http://localhost:8080/TheOtherSpace/MovieControlPanelServlet" method="POST">
                    	<input type='text' style="display:none" name="movieId" value="<%=movie.getId() %>">
                        <button type="submit" class="material-icons delete">&#xe872;</button>
                    </form>
                </div>
                
       		<%
			}
       		%>

            </div>
        
       </div>

        
    </div>
    
	<script>
	// Logica per la ricerca in tempo reale
	let moviesArray = [];
	let movie;
	<%for (int i = 0; i < movies.size(); i++) { %>
		movie = {id: <%=movies.get(i).getId()%>,
				genre: "<%=movies.get(i).getGenre().getName().toLowerCase() %>",
				title: "<%=movies.get(i).getTitle().toLowerCase() %>",
				};
		moviesArray[<%=i%>] = movie;
	<%}%>
	console.log(moviesArray);
	
	let searchBar = document.getElementById("users-search-bar");
	let rows = document.querySelectorAll('.user');
	
	searchBar.addEventListener("input", function() {
		let query = searchBar.value.toLowerCase();
		
		for (let j = 0; j < rows.length; j++) {
			rows[j].classList.add('hideRow');
		}
		
		for (let i = 0; i < moviesArray.length; i++) {
			if (moviesArray[i].title.includes(query) || moviesArray[i].genre.includes(query)) {
				for (let j = 0; j < rows.length; j++) {
					if (rows[j].querySelector('.user-id').innerText == moviesArray[i].id) {
						rows[j].classList.remove('hideRow');
					}
				}
			}
		}
		
	});
		
	</script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>