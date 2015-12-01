<header>
<div class="inner relative">
	<a class="logo" href="${pageContext.request.contextPath}/accueil.jsp"><img src="images/logo.jpg" alt="Location de films"></a>
	<a id="menu-toggle" class="button dark" href="#"><i class="icon-reorder"></i></a>
	<nav id="navigation">
		<ul id="main-menu">
			<li><a href="${pageContext.request.contextPath}/accueil">Accueil</a></li>
			<li><a href="${pageContext.request.contextPath}/consultation">Consultation</a></li>
			<li><a href="${pageContext.request.contextPath}/location">Location</a></li>
			<li>
				<div class="dropdown">
					<a class="account" >
					 ${sessionScope.utilisateur}
					</a>
					<div class="submenu" style="display: none;">
					  <ul class="root">
					    <li>
					      <a href="${pageContext.request.contextPath}/">Se déconnecter</a>
					    </li>
					  </ul>
					</div>
				</div>
			</li>
		</ul>
	</nav>
	<div class="clear"></div>
</div>
</header>
<script type="text/javascript" >
	$(document).ready(function()
	{
		$(".account").click(function()
		{
			var X=$(this).attr('id');
		
			if(X==1)
			{
				$(".submenu").hide();
				$(this).attr('id', '0');	
			}
			else
			{
		
				$(".submenu").show();
				$(this).attr('id', '1');
			}
			
		});
		
		//Mouseup textarea false
		$(".submenu").mouseup(function()
		{
			return false
		});
		$(".account").mouseup(function()
		{
			return false
		});
		
		
		//Textarea without editing.
		$(document).mouseup(function()
		{
			$(".submenu").hide();
			$(".account").attr('id', '');
		});
		
	});
	
</script>