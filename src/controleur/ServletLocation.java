package controleur;

import java.io.IOException;  
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  


public class ServletLocation extends HttpServlet 
{  
	FacadeLocation _facade; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 String action = request.getServletPath();
		 
		 if(action == "/AjoutFilm")
			 chargerAjoutFilm(request, response);
		 else
			 request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	protected void chargerAjoutFilm(HttpServletRequest request, HttpServletResponse response){
		
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
