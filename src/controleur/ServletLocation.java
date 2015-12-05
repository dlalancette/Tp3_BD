package controleur;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  

import modele.util.*;

public class ServletLocation extends HttpServlet 
{  
	private static final long serialVersionUID = 1L;
	FacadeLocation _facade; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		 HttpSession session = request.getSession();
		 String action = request.getServletPath();
		 
		 if(session.getAttribute("estAuthentifie") != null &&
			session.getAttribute("estAuthentifie") == "1") {
			 if(action.equals("/")){
				 session.invalidate();
				 request.getRequestDispatcher("/login.jsp").forward(request, response);
			 }
			 else if(action.contains("accueil"))
				 request.getRequestDispatcher("/accueil.jsp").forward(request, response);
			 else if(action.contains("consultation"))
				 request.getRequestDispatcher("/consultation.jsp").forward(request, response);
			 else if(action.contains("location"))
				 request.getRequestDispatcher("/location.jsp").forward(request, response);
			 else
				 response.sendError(HttpServletResponse.SC_NOT_FOUND);
		 }
		 else if(action.equals("/"))
			 request.getRequestDispatcher("/login.jsp").forward(request, response);
		 else {
			 response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		 }
	}
	
	protected void EtablirConnexion(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		String username = request.getParameter("Email");
        String password = request.getParameter("Password");
		HttpSession session = request.getSession();
		
    	if (_facade.VerifieConnexion(username, password)) {
            session.setAttribute("utilisateur", username);
            session.setAttribute("estAuthentifie", "1");
    		request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    	}
    	else {
            session.setAttribute("estAuthentifie", "0");
    		request.setAttribute("error", "Authentification échouée");
        	request.getRequestDispatcher("/login.jsp").forward(request, response);
    	}
	}
	
	protected void LouerFilm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, ParseException {
 		HttpSession session = request.getSession();
		Location location = _facade.EffectuerLocation(request.getParameter("titre").trim(), 
											   (String)session.getAttribute("utilisateur"));
		
		request.setAttribute("msgLocation", location.getMessage());
		request.setAttribute("TblCopies", location.getCopies());
		request.getRequestDispatcher("/location.jsp").forward(request, response);
	}
	
	protected void ObtenirFilm(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException, ParseException {
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
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	{
		//On instancie la facade
		//On ouvre une transaction et session avec la BD
        _facade = new FacadeLocation(); 
	        
		try{
	        if(request.getServletPath().contains("consultation"))
	        	ObtenirFilm(request, response);
	        else if(request.getServletPath().contains("location"))
	        	LouerFilm(request, response);
	        else
	        	EtablirConnexion(request, response);
		}
		catch(Exception ex){
			_facade.Close();
			response.sendError(500, ex.getMessage());
			return;
		}
        
        _facade.Close(); //On ferme la session et la transaction
        //On commit les changements, au besoin
	}
}
