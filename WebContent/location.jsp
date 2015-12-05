<html class="csstransforms no-csstransforms3d csstransitions">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<jsp:include page="entete.jsp" />
	<link type="text/css" rel="stylesheet" 
		  href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

<div id="wrap">
	
	<jsp:include page="menu.jsp" />
	
	<form method="POST" id="frmLocation" role="form" action="location"> 
  
	  	<div class="row">
		  <div class="form-group col-md-10">
		  	<input type="text" name="titre" class="form-control" placeholder="titre">
		  </div>
		  
  		  <div class="form-group col-md-2">
	  		<button type="submit" class="btn btn-default">Louer</button>
	  	  </div>
	  	</div>
  		
	  	<span>${msgLocation}</span>
  	
	</form>
	
	<c:choose>
	    <c:when test="${empty TblCopies}">
	
	    </c:when>
	    <c:otherwise>
	    
	    	<div class="form-group" id="divListeCopie">
				<h3>Liste des copies </h3> 
				<table class="table table-striped">
				    <tr>
				        <th>No. Série Copie</th>
				        <th>État de la copie</th>
			 		 </tr>
				    <c:forEach var="copie" items="${TblCopies}">
				    <tr>
				        <td>
				            <c:out value="${copie.noseriecopie}" />
				        </td>
				        <td>
				            <c:out value="${copie.etatcopie}" />
				        </td>
				    </tr>
				    </c:forEach>
				</table>
			</div>
		
	    </c:otherwise>
	</c:choose>
	
	</div>    

</body>
</html>