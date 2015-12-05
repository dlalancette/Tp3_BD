package controleur;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

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
			lstCopies = otenirCopies(idFilm);
			Tblcopie copie = obtenirCopiesDispo(idFilm);
			
			PreparedStatement st = _Connexion.prepareStatement("{call p_EffectuerLocation(?, ?)}");
            st.setString(1, courrielusag);
            st.setString(2, copie.getNoseriecopie());
            st.execute();
            
			duree = obtenirDureeMax(courrielusag);
			
			message = String.format("Le film a été loué avec succès! \n "
									+ "Veuillez le rapporter d'ici %1s", duree); 
		}
		catch(Exception ex){
			message = ex.getMessage();
			return new Location(message, lstCopies);
		}
		
		
		return new Location(message, lstCopies);
	}
	
	private List otenirCopies(BigDecimal idFilm){
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
	
}
