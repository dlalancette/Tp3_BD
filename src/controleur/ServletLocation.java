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
		 
		 if(action.equals("/"))
			 request.getRequestDispatcher("/login.jsp").forward(request, response);
		 else if(action.contains("accueil"))
			 request.getRequestDispatcher("/accueil.jsp").forward(request, response);
		 else if(action.contains("consultation"))
			 request.getRequestDispatcher("/consultation.jsp").forward(request, response);
		 else if(action.contains("ajoutfilm"))
			 chargerAjoutFilm(request, response);
		 else
			 response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
	
	protected void chargerAjoutFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/ajoutfilm.jsp").forward(request, response);
	}
	
	protected void ObtenirFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String titre,annee,paysproduction,langue,genre,realisateur,acteurs;
		String[] lstacteur = null;
		
		titre = request.getParameter("titre").trim();
		annee = request.getParameter("annee").trim();
		paysproduction  = request.getParameter("paysproduction").trim();
		genre = request.getParameter("genre").trim();
		langue = request.getParameter("langue").trim();
		realisateur = request.getParameter("realisateur").trim();
		acteurs = request.getParameter("acteur").trim();

		request.setAttribute("TblFilm", _facade.ObtenirListFilm(titre,annee,paysproduction,genre,langue,realisateur,acteurs));
		request.getRequestDispatcher("/consultation.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, 
	     HttpServletResponse response)
	    throws ServletException, IOException
	{
		String username = request.getParameter("Email");
        String password = request.getParameter("Password");

        _facade = new FacadeLocation();
        if(request.getServletPath().contains("consultation"))
        	{
        	ObtenirFilm(request, response);
        	//request.getRequestDispatcher("/consultation.jsp").forward(request, response);
        	}
        else{
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
}
