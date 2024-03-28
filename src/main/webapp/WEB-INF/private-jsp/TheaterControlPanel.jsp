<%@page import="com.theotherspace.model.Theater"%>
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
		List<Theater> theaters = (List<Theater>) request.getAttribute("theaters");
		Integer theaterRemovedId = null;
		theaterRemovedId = (Integer) request.getAttribute("theaterRemovedId");
		
	%>

    <div id="container" class="container-fluid">

       <div id="left-menu">

        <h3>Pannello di controllo</h3>
        <span id="below-h3"></span>

        <a href="<%= request.getContextPath() %>/UserControlPanelServlet" class="left-menu-element">
            <span class="material-icons">&#xe7ef;</span>
            <h5>Utenti</h5>
        </a>

        <a href="<%= request.getContextPath() %>/MovieControlPanelServlet" class="left-menu-element">
            <span class="material-icons">&#xe02c;</span>
            <h5>Film</h5>
        </a>

        <a href="<%= request.getContextPath() %>/TheaterControlPanelServlet" class="left-menu-element current">
            <span class="material-icons">&#xefed;</span>
            <h5>Sale</h5>
        </a>
        
        <a href="<%= request.getContextPath() %>/ScreeningControlPanelServlet" class="left-menu-element">
            <span class="material-icons">&#xe04b;</span>
            <h5>Proiezioni</h5>
        </a>

        <a id="logout" href="">logout</a>

       </div>

       <div id="main-content">

            <textarea name="users-search-bar" id="users-search-bar" rows="1"></textarea>

            <div id="top-users-container">
              
                    <p class="uh-id">ID </p>
                    <p class="uh-fullname">NUMERO</p>
                    <p class="uh-email">POSTI</p>
                    <p class="uhname"></p>
                    <p class="uh-dob"></p>
                    <div>
                        <a href="<%= request.getContextPath() %>/TheaterControlPanelAddServlet">
                        <span id="add-user" class="material-icons edit">&#xe146;</span>
                         </a>
                    </div>
              
            </div>
            <div id="users-container">
			<%
			for (Theater theater: theaters) {
			%>
                <div class="user">
                    <p class="user-id" name="user-id"><%=theater.getId() %></p>
                    <p class="user-fullname"><%=theater.getNumber() %></p>
                    <p class="user-email"><%=theater.getSeats() %></p>
                    <p class="username"></p>
                    <p class="user-dob"></p>
                    <a href="<%= request.getContextPath() %>/TheaterControlPanelEditServlet?theaterId=<%=theater.getId() %>">
                        <span class="material-icons edit">&#xe3c9;</span>
                    </a>
                    <form action="<%= request.getContextPath() %>/TheaterControlPanelServlet" method="POST">
                    	<input type='text' style="display:none" name="theaterId" value="<%=theater.getId() %>">
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
	let theatersArray = [];
	let theater;
	<%for (int i = 0; i < theaters.size(); i++) { %>
	theater = {id: <%=theaters.get(i).getId()%>,
			number: "<%=theaters.get(i).getNumber() %>",
				seats: "<%=theaters.get(i).getSeats() %>",
				};
		theatersArray[<%=i%>] = theater;
	<%}%>
	console.log(theatersArray);
	
	let searchBar = document.getElementById("users-search-bar");
	let rows = document.querySelectorAll('.user');
	
	searchBar.addEventListener("input", function() {
		let query = searchBar.value.toLowerCase();
		
		for (let j = 0; j < rows.length; j++) {
			rows[j].classList.add('hideRow');
		}
		
		for (let i = 0; i < theatersArray.length; i++) {
			if (theatersArray[i].number.includes(query)) {
				for (let j = 0; j < rows.length; j++) {
					if (rows[j].querySelector('.user-id').innerText == theatersArray[i].id) {
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