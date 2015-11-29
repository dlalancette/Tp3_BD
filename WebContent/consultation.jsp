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

	<form method="POST" id="searchForm" role="form" action="consultation"> 
	  
	  <div class="row">
		  <div class="form-group col-md-8">
		  	<input type="text" name="titre" class="form-control" placeholder="titre">
		  </div>
		  
		  <div class="form-group col-md-2">
		  	<input type="text" name="annee" class="form-control" placeholder="annee">
		  </div>
	  </div>
	  
	  <div class="row">
		 
		  <div class="form-group col-md-2">
		  	<input type="text" name="paysproduction" class="form-control" placeholder="pays">
		  </div>
		  
		  <div class="form-group col-md-2">
		  	<input type="text" name="langue" class="form-control" placeholder="langue">
		  </div>
		  
		  <div class="form-group col-md-2">
		  	<input type="text" name="genre" class="form-control" placeholder="genre">
		  </div>
		  
		  <div class="form-group col-md-2">
		  	<input type="text" name="realisateur" class="form-control" placeholder="realisateur">
		  </div>
		  
		  <div class="form-group col-md-2">
		  	<input type="text" name="acteur" class="form-control" placeholder="acteur">
		  </div>
	 	
	 	  <div class="form-group col-md-2">
	  		<button type="submit" class="btn btn-default">Rechercher</button>
	  	  </div>
	  </div>
	  
	</form>
	
	<div class="form-group" id="divListeFilms">
		
		<h3>Liste des films </h3> 
		<table class="table table-striped">
		    <tr>
		        <th>Titre</th>
		        <th>Année</th>
		        <th>Pays</th>
				<th>Langue</th>
		 		<th>Genre</th>
		 		<th>Realisateur</th>
		 		<th>Acteur</th>
	 		 </tr>
		    <c:forEach var="film" items="${TblFilm}">
	    <tr>
	        <td>
	            <c:out value="${film[0]}" />
	        </td>
	        <td>
	            <c:out value="${film[1]}" />
	        </td>
	        <td>
	            <c:out value="${film[2]}" />
	        </td>
	        <td>
	            <c:out value="${film[3]}" />
	        </td>
	        <td>
	            <c:out value="${film[4]}" />
	        </td>
	        <td>
	            <c:out value="${film[5]} ${film[6]} " />
	        </td>
	        <td>
	            <c:out value="${film[7]} ${film[8]}" />
	        </td>
	    </tr>
	    </c:forEach>
		</table>
	</div>

</div>   
</body>
</html>


