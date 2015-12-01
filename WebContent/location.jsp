<html class="csstransforms no-csstransforms3d csstransitions">
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
	  		<button type="submit" class="btn btn-default">Rechercher</button>
	  	  </div>
	  	</div>
  		
	  	<span>${msgLocation}</span>
  	
	</form>
	
	</div>    

</body>
</html>