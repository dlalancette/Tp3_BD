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
  
  <div class="form-group">
  	<input type="text" name="titre" class="form-control" placeholder="titre">
  </div>
  
  <div class="form-group">
  	<input type="text" name="annee" class="form-control" placeholder="annee">
  </div>
 
  <div class="form-group">
  	<input type="text" name="paysproduction" class="form-control" placeholder="pays">
  </div>
  
  <div class="form-group">
  	<input type="text" name="langue" class="form-control" placeholder="langue">
  </div>
  
  <div class="form-group">
  	<input type="text" name="genre" class="form-control" placeholder="genre">
  </div>
  
  <div class="form-group">
  	<input type="text" name="realisateur" class="form-control" placeholder="realisateur">
  </div>
  
  <div class="form-group">
  	<input type="text" name="acteur" class="form-control" placeholder="acteur">
  </div>
 
  <button type="submit" class="btn btn-default">Rechercher</button>
  
</form>
 <div class="form-group">
<table class="table-bordered">
   <h3>Liste des films </h3> 
    <tr>
        <th>Titre</th>
        <th>Année</th>
        <th>Pays</th>
		<th>Langue</th>
 		<th>Genre</th>
 		<th>Realisateur</th>
 		<th></th>
 		<th>Acteur</th>
 		<th></th>
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
            <c:out value="${film[5]}" />
        </td>
        <td>
            <c:out value="${film[6]} ${film[7]}" />
        </td>
         <td>
            <c:out value="${film[8]} ${film[9]}" />
        </td>
        
    </tr>
    </c:forEach>
</table>
</div>

</div>   
</body>
</html>


