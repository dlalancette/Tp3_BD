<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class="csstransforms no-csstransforms3d csstransitions">
<head>
	<jsp:include page="entete.jsp" />
	<link type="text/css" rel="stylesheet" 
		  href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<div id="wrap">
		<jsp:include page="menu.jsp" />
	
		<form id="frmAjoutFilm" role="form" action="${pageContext.request.contextPath}/AjoutFilm">
		
		  <div class="form-group">
		    <label for="txtTitre">Titre du film:</label>
		    <input type="text" class="form-control" name="txtTitre">
		  </div>
		  
		  <div class="form-group">
		    <label for="txtDateFilm">Année de sortie:</label>
		    <input type="date" class="form-control" name="txtDateFilm">
		  </div>
		  
		  <div class="form-group">
		    <label for="txtLangueFilm">Langue originale:</label>
		    <input type="text" class="form-control" name="txtLangueFilm">
		  </div>
		  
		  <div class="form-group">
		    <label for="lstGenreFilm">Genre du film:</label>
		    <select name="lstGenreFilm" class="form-control">
			    <c:forEach items="${GenreFilm}" var="type">
			        <option value="${type}">${type}</option>
			    </c:forEach>
			</select>
		  </div>
		  
		  <div class="form-group">
		    <label for="lstPaysFilm">Pays de production du film:</label>
		    <select name="lstPaysFilm" class="form-control">
			    <c:forEach items="${PaysFilm}" var="type">
			        <option value="${type}">${type}</option>
			    </c:forEach>
			</select>
		  </div>
		  
		  <div class="form-group">
		    <label for="lstScenaristeFilm">Scénariste du film:</label>
		    <select name="lstScenaristeFilm" class="form-control">
			    <c:forEach items="${ScenaristeFilm}" var="type">
			        <option value="${type}">${type}</option>
			    </c:forEach>
			</select>
		  </div>
		  
		  <div class="form-group">
		    <label for="lstRealFilm">Réalisateur du film:</label>
		    <select name="lstRealFilm" class="form-control">
			    <c:forEach items="${RealFilm}" var="type">
			        <option value="${type}">${type}</option>
			    </c:forEach>
			</select>
		  </div>
		  
		  <div class="form-group">
		    <label for="lstActeurFilm">Acteur du film:</label>
		    <select name="lstActeurFilm" class="form-control">
			    <c:forEach items="${ActeurFilm}" var="type">
			        <option value="${type}">${type}</option>
			    </c:forEach>
			</select>
		  </div>
		  
		  <div class="form-group">
		    <label for="lstPersoFilm">Personnage du film:</label>
		    <select name="lstPersoFilm" class="form-control">
			    <c:forEach items="${PersoFilm}" var="type">
			        <option value="${type}">${type}</option>
			    </c:forEach>
			</select>
		  </div>
		  
		  <button type="submit" class="btn btn-default">Enregistrer</button>
		</form>
		
	</div>    

</body>
</html>