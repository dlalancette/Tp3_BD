<!DOCTYPE html>
<html class="csstransforms no-csstransforms3d csstransitions">
<head>
	<jsp:include page="entete.jsp" />
	<link type="text/css" rel="stylesheet" 
		  href="${pageContext.request.contextPath}/css/style.css" />
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-theme" rel="stylesheet">
</head>
<body>
	<div style="padding-left:15%; background:#0099cc; font-size:28px; text-align:left; color:#FFF; font-weight:bold; height:100px; padding-top:50px;">
		Location de films en ligne
	</div>
	<div id="wrap">
		<jsp:include page="menu.jsp" />
	
		<form id="frmAjoutFilm" role="form">
		
		  <div class="form-group">
		    <label for="titre">Titre du film:</label>
		    <input type="text" class="form-control" name="titre">
		  </div>
		  
		  <div class="form-group">
		    <label for="dateFilm">Année de sortie:</label>
		    <input type="date" class="form-control" name="dateFilm">
		  </div>
		  
		  <div class="form-group">
		    <label for="langueFilm">Langue originale:</label>
		    <input type="text" class="form-control" name="langueFilm">
		  </div>
		  
		  <button type="submit" class="btn btn-default">Enregistrer</button>
		</form>
		
	</div>    

</body>
</html>