<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
	<title>Location de Films</title>
	<link type="text/css" rel="stylesheet" 
		  href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>

<div class="container">

  <div id="login-form">

    <h3>S'authentifier</h3>

    <fieldset>

      <form method="post">

        <input type="email" required name="Email" value="Email"
			   onBlur="if(this.value=='')this.value='Email'" onFocus="if(this.value=='Email')this.value='' "> 

        <input type="password" required name="Password" value="Password" 
        	   onBlur="if(this.value=='')this.value='Password'" onFocus="if(this.value=='Password')this.value='' ">

        <input type="submit" value="Login">

        <footer class="clearfix">
        	<span class="error">${error}</span>
        </footer>
		
      </form>

    </fieldset>

  </div>

</div>
    
</body>
</html>
