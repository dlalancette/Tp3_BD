package controleur;

import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

@WebServlet(name="hello",urlPatterns={"/hello"})
public class Servlet extends HttpServlet 
{  
	public void doGet (HttpServletRequest requete, HttpServletResponse reponse)  
	    throws ServletException, IOException {  
		try{
	    reponse.setContentType("text/html");
		PrintWriter out = reponse.getWriter();
	
		out.println("<title>Exemple</title>" +
		   "<body bgcolor=FFFFFF>");
	
		out.println("<h2>Hello World!</h2>");
		out.close();  
		}
		catch(Exception e)
		{
			
		}
	}  
	public void doPost(HttpServletRequest request, 
	     HttpServletResponse response)
	    throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		out.println("<title>Exemple</title>" +
		   "<body bgcolor=FFFFFF>");
	
		out.println("<h2>Formulaire recu!</h2>");
	
		String DATA = request.getParameter("DATA");
	
		if(DATA != null){
		  out.println("Le champ contenait la valeur: "+DATA);
		} else {
		  out.println("Aucun texte entré.");
		}
		out.close();
	}
}
