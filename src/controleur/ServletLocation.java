package controleur;

import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  


public class ServletLocation extends HttpServlet 
{  
	FacadeLocation _facade; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	   request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, 
	     HttpServletResponse response)
	    throws ServletException, IOException
	{
		String username = request.getParameter("Email");
        String password = request.getParameter("Password");

        _facade = new FacadeLocation();
        
        if (_facade.VerifieConnexion(username, password)) {
            //request.getSession().setAttribute("user", user);
        	request.getRequestDispatcher("/accueil.jsp").forward(request, response);
        }
        else {
            request.setAttribute("error", "Authentification échouée");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
	}
}
