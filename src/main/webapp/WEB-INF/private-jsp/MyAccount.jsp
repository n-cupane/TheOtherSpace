<%@page import="com.theotherspace.model.User"%>
<%@ page import="java.util.List" %>
<%@ page import="com.theotherspace.model.Movie" %>
<%@ page import="com.theotherspace.utilities.BusinessLogic" %>
<%@ page import="java.lang.Boolean" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/MyAccount.css"/>
</head>
<body>
    <div id="container" class="container-fluid">

        <header id="header" class="row justify-content-center">
            
            <div class="col-2 d-flex align-items-center justify-content-center">
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
                     <% }  %>
                     
                      
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
                
            </div>
        </header>

        
        
        <div class="container_movies" id="container_movies">
        		<a href="<%= request.getContextPath() %>/MyAccountServlet">La mia Card</a>
	            <a href="<%= request.getContextPath() %>/AccountInfoServlet">Le mie info</a>
				<a href="<%= request.getContextPath() %>/MyNextTicketServlet">Le mie proiezioni</a>
    	</div>
    </div>
    <% boolean haveCard = (boolean)request.getAttribute("haveCard"); 
       User activeUser = (User)request.getAttribute("activeUser");
    %>
    
    <%if(!haveCard){ %>
    <div class="conditions-form">
		  <form action="<%= request.getContextPath() %>/AcceptCardServlet" method="post">
			    <div>
			      <input type="checkbox" id="condition1" name="conditions" value="condition1" required="required">
			      <label for="condition1">Utilizzo Responsabile della Carta TheOtherSpace:</label>
			    </div>
				    <p>
				      L'utente accetta di utilizzare la propria carta TheOtherSpace in modo responsabile e rispettoso delle norme dell'organizzazione.
				      Si impegna a fare un uso appropriato dei vantaggi offerti dalla carta TheOtherSpace e a non recare disturbo agli altri utenti durante l'accesso ai servizi.
				      Qualsiasi utilizzo improprio potrebbe comportare la revoca dei privilegi associati alla carta TheOtherSpace dell'utente.
				    </p>
			    <div>
			      <input type="checkbox" id="condition2" name="conditions" value="condition2" required="required">
			      <label for="condition2">Condivisione di Informazioni Personali:</label>
			    </div>
				    <p>
				      L'utente comprende che, per registrare e utilizzare la carta TheOtherSpace, potrebbe essere necessario fornire alcune informazioni personali.
				      Si impegna a fornire solo informazioni accurate e veritiere durante il processo di registrazione e a rispettare la privacy dei propri dati.
				      Le informazioni fornite verranno trattate nel rispetto delle leggi sulla privacy e non saranno divulgate a terzi senza il consenso dell'utente.
				    </p>
			    <div>
			      <input type="checkbox" id="condition3" name="conditions" value="condition3" required="required">
			      <label for="condition3">Sicurezza della Carta TheOtherSpace:</label>
			    </div>
				    <p>
				      L'utente riconosce che la sua carta TheOtherSpace è di sua responsabilità e si impegna a mantenerla al sicuro da smarrimenti o utilizzi non autorizzati.
				      In caso di smarrimento o furto della carta TheOtherSpace, l'utente è tenuto a segnalarlo tempestivamente all'assistenza clienti per evitare un uso non autorizzato.
				      TheOtherSpace non sarà responsabile per eventuali utilizzi non autorizzati della carta da parte di terzi.
				    </p>
		    	<input type="submit" value="Accetta Condizioni">
		  </form>
	</div>
	<%} else { %>
			<div class="container">
		    <div class="left-half">
		        <img src="https://i0.wp.com/www.tariffando.it/wp-content/uploads/2021/05/Immagine.png?fit=794%2C410&ssl=1" alt="Image" class="image">
		        <div class="my-card">
		    	<p>Username: <%=activeUser.getUsername() %></p>
		    	<p>Codice carta: <%=activeUser.getCardCode()%></p>
		    	<%if(activeUser.getCardPoints()!=null){ %>
		    	<p>Saldo punti: <%=activeUser.getCardPoints()%></p>
		    	<%} else { %>
		    	<p>Saldo punti: 0</p>
		    	<%} %>
		    </div>
		    </div>
		    
		    <div class="right-half">
		        <div class="inner-div">
		            <p>Risparmio sugli acquisti: Con la carta TheOtherSpace, hai accesso a sconti esclusivi presso una vasta gamma di partner convenzionati. Che tu stia acquistando cibo, vestiti o intrattenimento, la tua carta ti garantisce prezzi ridotti e offerte speciali.</p>
		        </div>
		        <div class="inner-div">
		            <p>Accumulo punti per premi speciali: Ogni volta che utilizzi la tua carta TheOtherSpace, accumuli punti che possono essere convertiti in fantastici premi. Ad esempio, ogni 1000 punti guadagnati ti danno diritto a un biglietto gratuito per il tuo prossimo acquisto.</p>
		        </div>
		        <div class="inner-div">
		            <p>Offerte esclusive per i membri: Essere titolare della carta TheOtherSpace ti dà accesso a promozioni e offerte speciali riservate esclusivamente ai membri. Da sconti a sorprese inaspettate, ci sono sempre vantaggi da scoprire utilizzando la tua carta TheOtherSpace.</p>
		        </div>
		    </div>
		</div>
		<%} %>




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
            <h4>THE SPACE CINEMA</h4>
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
    <div class="footer-content">
    	<div style="display: flex">
	    	<p>This website uses TMDB and the TMDB APIs but is not endorsed, certified, or otherwise approved by TMDB.</p>
	    	<img style="height: 1.5vw; margin-left: 19.5px; margin-top: 20px" src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_square_1-5bdc75aaebeb75dc7ae79426ddd9be3b2be1e342510f8202baf6bffa71d7f5c4.svg">
    	</div>
    </div>
</footer>


</html>