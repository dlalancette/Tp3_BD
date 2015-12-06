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
	
	public Location effectueLocation(BigDecimal idFilm, String courrielusag) {
		String message = "";
		String duree = "";
		List lstCopies = null;
		
		try{
			Tblcopie copie = obtenirCopiesDispo(idFilm);
			
			PreparedStatement st = _Connexion.prepareStatement("{call p_EffectuerLocation(?, ?)}");
            st.setString(1, courrielusag);
            st.setString(2, copie.getNoseriecopie());
            st.execute();
            
			duree = obtenirDureeMax(courrielusag);
			
			message = String.format("Le film a été loué avec succès! \n "
									+ "Veuillez le rapporter d'ici %1s", duree); 

			lstCopies = obtenirCopies(idFilm);
		}
		catch(SQLException sqlE){
			if(sqlE.getErrorCode() == 20004)
				message = "Le nombre de location permise pour votre forfait a été atteint!"
						+ "\nVeuillez rapporter vos films!";
			else 
				message = sqlE.getMessage();
		}
		catch(Exception ex){
			message = ex.getMessage();
			return new Location(message, lstCopies);
		}
		
		return new Location(message, lstCopies);
	}
	
	private List obtenirCopies(BigDecimal idFilm){
		Criteria criteria = _Session.createCriteria(Tblcopie.class);
		criteria.add(Restrictions.eq("tblfilm.idfilm", idFilm));
		return criteria.list();
	}
	
	private Tblcopie obtenirCopiesDispo(BigDecimal idFilm) throws Exception {
		Criteria criteria = _Session.createCriteria(Tblcopie.class);
		criteria.add(Restrictions.eq("tblfilm.idfilm", idFilm));
		criteria.add(Restrictions.eq("etatcopie", "D"));
		List lstCopies = criteria.list();
		
		if(!lstCopies.isEmpty())
			return (Tblcopie)lstCopies.get(0);
		else
			throw new Exception("Aucune copie disponible pour ce film!");
	}
	
	private String obtenirDureeMax(String courrielusag){
		Criteria criteria = _Session.createCriteria(Tblusager.class);
		criteria.add(Restrictions.eq("courrielusag", courrielusag));
		Tblusager usager = (Tblusager)criteria.uniqueResult();
		
		if(usager != null && usager.getTblforfait() != null)
			return usager.getTblforfait().getDureemax();
		else return null;
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
