<header>
<div class="inner relative">
	<a class="logo" href="${pageContext.request.contextPath}/accueil.jsp"><img src="images/logo.jpg" alt="Location de films"></a>
	<a id="menu-toggle" class="button dark" href="#"><i class="icon-reorder"></i></a>
	<nav id="navigation">
		<ul id="main-menu">
			<li><a href="${pageContext.request.contextPath}/accueil.jsp">Accueil</a></li>
			<li><a href="${pageContext.request.contextPath}/consultation.jsp">Consultation</a></li>
			<li><a href="${pageContext.request.contextPath}/location.jsp">Location</a></li>
			<li class="parent">
				<a href="">Administration</a>
				<ul class="sub-menu">
					<li><a href="${pageContext.request.contextPath}/ajoutfilm.jsp">Ajout d'un film</a></li>
					<li><a href="${pageContext.request.contextPath}/retraitfilm.jsp">Retrait d'un film</a></li>
				</ul>
			</li>
		</ul>
	</nav>
	<div class="clear"></div>
</div>
</header>