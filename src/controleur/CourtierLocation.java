package controleur;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sun.org.apache.bcel.internal.generic.Select;

import modele.*;
import modele.util.*;

public class CourtierLocation extends Courtier {
	
	CourtierLocation() {
		super();
	}
	
	//Permet de procéder à la location d'une copie auprès d'un client
	public Location effectueLocation(BigDecimal idFilm, String courrielusag) {
		String message = "";
		String duree = "";
		List lstCopies = null;
		
		try{
			//On charge la liste de toutes les copies pour le film
			lstCopies = obtenirCopies(idFilm);
			
			//On obtient une copie disponible 
			Tblcopie copie = obtenirCopiesDispo(idFilm);
			
			//On execute la procédure stocké chargée d'effectuer la location
			PreparedStatement st = _Connexion.prepareStatement("{call p_EffectuerLocation(?, ?)}");
            st.setString(1, courrielusag);
            st.setString(2, copie.getNoseriecopie());
            st.execute();
            
            //On obtient la durée de locatio maximum pour le client
			duree = obtenirDureeMax(courrielusag);
			
			message = String.format("Le film a été loué avec succès! \n "
									+ "Veuillez le rapporter d'ici %1s", duree);
		}
		catch(SQLException sqlE){
			//Un déclencheur s'assure de vérifier si le seuil de location dans le forfait est atteint 
			if(sqlE.getErrorCode() == 20004)
				message = "Le nombre de location permise pour votre forfait a été atteint!"
						+ "\nVeuillez rapporter vos films!";
			//Sinon on affiche l'erreur système
			else 
				message = sqlE.getMessage();
		}
		catch(Exception ex){
			message = ex.getMessage();
		}
		
		//Retour des données au sein de l'objet Location
		return new Location(message, lstCopies);
	}
	
	//On retourne une liste de toutes les copies pour un film
	private List obtenirCopies(BigDecimal idFilm){
		Criteria criteria = _Session.createCriteria(Tblcopie.class);
		criteria.add(Restrictions.eq("tblfilm.idfilm", idFilm));
		return criteria.list();
	}
	
	//On obtient une copie parmi celle disponible pour un film
	private Tblcopie obtenirCopiesDispo(BigDecimal idFilm) throws Exception {
		Criteria criteria = _Session.createCriteria(Tblcopie.class);
		criteria.add(Restrictions.eq("tblfilm.idfilm", idFilm));
		criteria.add(Restrictions.eq("etatcopie", "D"));
		List lstCopies = criteria.list();
		
		//Si la requête retourne des résultats
		if(!lstCopies.isEmpty())//Alors, on retourne une copie
			return (Tblcopie)lstCopies.get(0); 
		else //Sinon, on lève un exception
			throw new Exception("Aucune copie disponible pour ce film! Veuillez réessayer plus tard!");
	}
	
	//Obtenir la durée Maximum de la location permis par le forfait du client
	private String obtenirDureeMax(String courrielusag){
		Criteria criteria = _Session.createCriteria(Tblusager.class);
		criteria.add(Restrictions.eq("courrielusag", courrielusag));
		Tblusager usager = (Tblusager)criteria.uniqueResult();
		
		//Si l'usager existe et qu'il est assigné à un forfait
		if(usager != null && usager.getTblforfait() != null) //Alors on obtient la duré max
			return usager.getTblforfait().getDureemax();
		else return null; //Sinon on retourne null
	}
	
	/*private boolean estLocationPermise(String courrielusag){
		int nbCopiesPermises = 0, nbCopiesLouees = 0;
		Criteria criteria = _Session.createCriteria(Tblusager.class,"tblusager");	
		criteria.createAlias("tblforfait", "tblforfait");
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("tblusager.courrielusag"));
		projList.add(Projections.property("tblforfait.nblocmax"));
		
		criteria.setProjection(projList);
		criteria.add(Restrictions.eq("tblusager.courrielusag", courrielusag));
		
		Object[] objUsag = (Object[])criteria.uniqueResult();
		nbCopiesPermises = ((BigDecimal)objUsag[1]).intValueExact();
		nbCopiesLouees = obtenirNbCopiesLouees(courrielusag);
		
		return nbCopiesPermises >= nbCopiesLouees ? true : false;
	}
	
	private int obtenirNbCopiesLouees(String courrielusag){
		Criteria criteria = _Session.createCriteria(Tbllocation.class,"tbllocation");	
		criteria.createAlias("tblcopie", "tblcopie");
		criteria.createAlias("tblusager", "tblusager");
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("tblusager.courrielusag"));
		projList.add(Projections.property("tblcopie.etatcopie"));
		
		criteria.setProjection(projList);
		
		criteria.add(Restrictions.eq("tblusager.courrielusag", courrielusag));
		criteria.add(Restrictions.eq("tblcopie.etatcopie", "L"));
		
		return criteria.list().size();
	}*/
}
