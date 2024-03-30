<%@page import="com.theotherspace.model.User"%>
<%@page import="com.theotherspace.model.Movie"%>
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
    <link rel="stylesheet" href="<%=request.getContextPath() + "/css/MovieSearchStyle.css"%>">
</head>
<body>
    <div id="container" class="container-fluid">
		<%
		List<Movie> moviesFound = (List<Movie>) request.getAttribute("moviesFound");
		if ( moviesFound == null) {
			// La richiesta a TMDB non è andata a buon fine o non è ancora stata effettuata
		%>
        <form action="<%= request.getContextPath() %>/SearchMovieServlet" method="post" id="main-content-container" class="container">
            <h2>Ricerca film per titolo</h2>
            <input type="text" name="movie-search-bar" id="movie-search-bar" autofocus>
            <input type="submit" value="CERCA IN TMDB" id="search-movie-btn">
        </form>
        <%
		} else if (moviesFound.size() == 0) {
			// La richiesta a TMDB è andata a buon fine ma il result set è vuoto
        %>
        <form action="<%= request.getContextPath() %>/AddMovieServlet" method="get" id="main-content-container" class="container">
            <h2>La ricerca non ha dato risultati</h2>
            <input type="submit" value="AGGIUNGI MANUALMENTE" id="search-movie-btn">
        </form>
        <%
		} else {
			// Result set non vuoto
        %>
        <div id="results-container" class="container">
            <h2 id="number-of-results"><%=moviesFound.size() %> risultati</h2>
            
            <%
            for (Movie movie: moviesFound) {
            %>
            <div class="result">
                <img class="result-image" src="<%=movie.getImageUrl() %>">
                <h3 class="result-title"><%=movie.getTitle() %> </h3>
                <a class="add-movie" href="<%= request.getContextPath() %>/AddMovieServlet?movieId=<%=movie.getId()%>">
                    <img src="<%=request.getContextPath() + "/res/plus.png"%>" alt="Add movie">
                </a>
            </div>
            <%
            }
            %>
        </div>        
        <%
		}
        %>

        
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scrips.js"></script>
</body>
</html>