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
		 
		 //On s'assure via la session courante que l'usager est bien authentifié
		 if(session.getAttribute("estAuthentifie") != null &&
			session.getAttribute("estAuthentifie") == "1") {
			 //On simule un mécanisme de routes et on redirige vers les bon fichiers .jsp
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
		//Si non authentifié et que l'usager tente d'accéder à la page de login
		 else if(action.equals("/")) //Alors on transfert vers cette même page
			 request.getRequestDispatcher("/login.jsp").forward(request, response);
		 //Sinon on affiche un erreur HTTP 401 - accès non autorisé
		 else {
			 response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		 }
	}
	
	protected void EtablirConnexion(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		//On récupère le courriel et le mot de passe
		String username = request.getParameter("Email");
        String password = request.getParameter("Password");
		HttpSession session = request.getSession();
		
		//Si la connexion est valide
    	if (_facade.VerifieConnexion(username, password)) { //Alors on authentifie l'usager
            session.setAttribute("utilisateur", username);
            session.setAttribute("estAuthentifie", "1");
    		request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    	}
    	else { //On n'authentifie pas l'usager et on affiche un msg d'erreur
            session.setAttribute("estAuthentifie", "0");
    		request.setAttribute("error", "Authentification échouée");
        	request.getRequestDispatcher("/login.jsp").forward(request, response);
    	}
	}
	
	//Permet l'affichage des données de location
	protected void LouerFilm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, ParseException {
 		HttpSession session = request.getSession();
 		
 		//On appel les fonctions de location et on capture l'output 
		Location location = _facade.EffectuerLocation(request.getParameter("titre").trim(), 
											   (String)session.getAttribute("utilisateur"));
		
		//Affichage du message d'erreur ou de succès 
		request.setAttribute("msgLocation", location.getMessage());
		//Affichages de l'inventaire des copies pour le film courant
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
	        
        //On redirige vers les bonnes methodes en fonction de l'action définit dans les params HTTP
		try{
	        if(request.getServletPath().contains("consultation"))
	        	ObtenirFilm(request, response);
	        else if(request.getServletPath().contains("location"))
	        	LouerFilm(request, response);
	        else
	        	EtablirConnexion(request, response);
		}
		catch(Exception ex){ //Si erreur on affiche un erreur 500 + exception
			_facade.Close();
			response.sendError(500, ex.getMessage());
			return;
		}
        
        _facade.Close(); //On ferme la session et la transaction
        //On commit les changements, au besoin
	}
}
