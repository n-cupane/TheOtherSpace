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

                <a href="">PRENOTA</a>

                <a href="">GENERI</a>

                <a href="">FILM</a>

            </nav>

            <div class="col-2 d-flex align-items-center">

                <div class="dropdown">
                    
                    <button id="btn" class="btn btn-secondary dropdown-toggle d-flex justify-content-evenly align-items-center" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                      <span id="person-img" class="material-icons">&#xe7fd;</span>
                      <p>LOG IN</p>
                    </button>
                    <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="#">(Username)</a></li>
                      <li><a class="dropdown-item" href="#">Signin</a></li>
                      <li><a class="dropdown-item" href="#">Login</a></li>
                      <li><a class="dropdown-item" href="#">Logout</a></li>
                    </ul>

                </div>
                <span id="search-icon" class="material-icons">&#xe8b6;</span>
            </div>
        </header>

                
        <div id="movie-container">
        
        <div id="left-movie-container">
        	<img id="movie-image" src=https://image.tmdb.org/t/p/w600_and_h900_bestv2/zozGfM5kO9qbx1XSvwDwrh6yTca.jpg>
        </div>
        
        <div id="right-movie-container">
        
        	<form action="" method="post" id="movie-form">
        	<h1 id="movie-title">DUNE - PARTE DUE</h1>
        	
        	<div id="below-title">
        	<%// SE IL FILM È PER MAGGIORENNI APPARE L'ICONA %>
        	<img id="over-18" src="<%=request.getContextPath()%>/res/number-18.png">
        	<p id="movie-genre">Azione</p>
        	</div>
        	 
        	<p id="movie-description">Il mitico viaggio di Paul Atreides che si unisce a Chani e ai Fremen sul sentiero della vendetta contro i cospiratori che hanno distrutto la sua famiglia. Di fronte alla scelta tra l'amore della sua vita e il destino dell'universo conosciuto, Paul intraprende una missione per impedire un terribile futuro che solo lui è in grado di prevedere.</p>
        	
        	<select id="screening-select" required>
        		<option>Giovedì 20:30</option>
        	</select>
        	
        	<input type="submit" id="submit-btn" value="SCEGLI IL TUO POSTO">
        	
        	</form>
        	
        </div>
         
        </div>
        
        
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>