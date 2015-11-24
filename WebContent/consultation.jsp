<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

<jsp:include page="entete.jsp" />
	<link type="text/css" rel="stylesheet" 
		  href="${pageContext.request.contextPath}/css/style.css" />
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-theme" rel="stylesheet">
	
</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultation</title>
 <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
<div style="padding-left:15%; background:#0099cc; font-size:28px; text-align:left; color:#FFF; font-weight:bold; height:100px; padding-top:50px;">
		Location de films en ligne
	</div>

 <div id="wrap">
		
		<jsp:include page="menu.jsp" />
		
<form action="/" id="searchForm" role="form">
  <input type="text" name="titre" placeholder="titre">
  <input type="text" name="annee" placeholder="annee">
  <input type="text" name="paysproduction" placeholder="pays">
  <input type="text" name="langue" placeholder="langue">
  <input type="text" name="genre" placeholder="genre">
  <input type="text" name="realisateur" placeholder="realisateur">
  <input type="text" name="acteur" placeholder="acteur">
  <input type="submit" value="Rechercher">
</form>
<!-- the result of the search will be rendered inside this div -->
 
<script>
// Attach a submit handler to the form
$( "#searchForm" ).submit(function( event ) {
 
  // Stop form from submitting normally
  event.preventDefault();
 
  // Get some values from elements on the page:
  var $form = $( this ),
  titre = $form.find( "input[name='titre']" ).val(),
  annee = $form.find( "input[name='annee']" ).val(),
  paysproduction = $form.find( "input[name='paysproduction']" ).val(),
  langue = $form.find( "input[name='langue']" ).val(),
  genre = $form.find( "input[name='genre']" ).val(),
  realisateur = $form.find( "input[name='realisateur']" ).val(),
  acteur = $form.find( "input[name='acteur']" ).val(),
    url = $form.attr( "action" );
 
  // Send the data using post
  var posting = $.post( url, { titre: titre, annee: annee, paysproduction: paysproduction, langue: langue, genre: genre, realisateur: realisateur, acteur: acteur } );
 
  // Put the results in a div
  posting.done(function( data ) {
	  var postsjson = $.parseJSON(data);
	  
	  for (var i = 0; i < postsjson.length; i++) {
		  
		  var row = $("<tr />")
		    $("#consultation").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
		    row.append($("<td>" + postsjson[i].RTITREFILM + "</td>"));
		    row.append($("<td>" + postsjson[i].RDATESORTIEFILM + "</td>"));
		    row.append($("<td>" + postsjson[i].RPAYS + "</td>"));
		    row.append($("<td>" + postsjson[i].RLANGUE + "</td>"));
		    row.append($("<td>" + postsjson[i].RGENRE + "</td>"));
		    row.append($("<td>" + postsjson[i].RREALISATEUR + "</td>"));
		    row.append($("<td>" + postsjson[i].RACTEUR + "</td>"));
	    }
  });
});
</script>

<div>
<table id="consultation">
    <tr>
        <th>Titre</th>
        <th>Annee</th>
        <th>Pays produit</th>
        <th>Langue</th>
        <th>Genre</th>
        <th>Realisateur</th>
        <th>Acteur</th>
    </tr>
    
</table>
</div>
</div>
</body>
</html>