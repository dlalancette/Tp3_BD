package controleur;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.*;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import modele.*;

public class CourtierLocation extends Courtier {
	
	CourtierLocation() {
		
	}
	
	public String effectueLocation(BigDecimal idFilm, String courrielusag) {
		String message = "";
		String duree = "";
		
		try{
			Tblcopie copie = obtenirCopiesDispo(idFilm);
			
			CallableStatement cs = _Connexion.prepareCall(String.format(
					"{ call p_EffectuerLocation %1s %2s }", courrielusag, copie.getNoseriecopie()));
	
			cs.execute();
			duree = obtenirDureeMax(courrielusag);
			
			message = String.format("Le film a été loué avec succès! \n "
									+ "Veuillez le rapporter d'ici %1s", duree); 
		}
		catch(Exception ex){
			message = ex.getMessage();
			return message;
		}
		
		
		return message;
	}
	
	private Tblcopie obtenirCopiesDispo(BigDecimal idFilm) {
		Criteria criteria = _Session.createCriteria(Tblcopie.class);
		criteria.add(Restrictions.eq("idfilm", idFilm));
		criteria.add(Restrictions.eq("etatcopie", "D"));
		
		return (Tblcopie) criteria.uniqueResult();
	}
	
	private String obtenirDureeMax(String courrielusag){
		Criteria criteria = _Session.createCriteria(Tblusager.class);
		criteria.createAlias("tblforfait","tblforfait");
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("Tblusager.courrielusag"));
		projList.add(Projections.property("tblforfait.dureeMax"));
		
		criteria.setProjection(projList);
		
		criteria.add(Restrictions.eq("Tblusager.courrielusag", courrielusag));
		
		return (String)((Object[])criteria.uniqueResult())[1];
	}
	
}
