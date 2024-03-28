<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Rating Stars</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/ReviewStyle.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <
</head>
<body>

<%  long movieId = (long)request.getAttribute("movieId");
	long userId = (long)request.getAttribute("userId");
%>
<div class="container_total">
  <div class="container_review">
    <div class="second_line">
      <form action="<%= request.getContextPath() %>/ConfirmReviewServlet" method="POST" id="ratingForm">
      		<input type="hidden" name="movieId" value="<%=movieId%>">
      		<input type="hidden" name="userId" value="<%=userId%>">
      		<textarea id="reviewText" name="reviewText" class="area_review" placeholder="La tua recensione" required="required"></textarea>
      		<div class="rating">
	      		<input type="radio" name="rating" value="a" id="rating1" required="required">
	      		<label for="rating1"></label>
	      		<input type="radio" name="rating" value="b" id="rating2">
	      		<label for="rating2"></label>
	      		<input type="radio" name="rating" value="c" id="rating3">
	      		<label for="rating3"></label>
	      		<input type="radio" name="rating" value="d" id="rating4">
	      		<label for="rating4"></label>
	      		<input type="radio" name="rating" value="e" id="rating5">
	      		<label for="rating5"></label>
      		</div>
      		<button type="submit" class="btn_submit">Invia</button> 
      </form>
    </div>
  </div>
</div>


</body>
</html>