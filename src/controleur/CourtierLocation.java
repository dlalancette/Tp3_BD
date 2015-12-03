package controleur;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import modele.*;

public class CourtierLocation extends Courtier {
	
	CourtierLocation() {
		super();
	}
	
	public String effectueLocation(BigDecimal idFilm, String courrielusag) {
		String message = "";
		String duree = "";
		try{
			Tblcopie copie = obtenirCopiesDispo(idFilm);
			
			PreparedStatement st = _Connexion.prepareStatement("{call p_EffectuerLocation(?, ?)}");
            st.setString(1, courrielusag);
            st.setString(2, copie.getNoseriecopie());
            st.execute();
			
			/*CallableStatement cs = _Connexion.prepareCall(String.format(
					"{ call p_EffectuerLocation %1s %2s }", courrielusag, copie.getNoseriecopie()));
	
			cs.execute();*/
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
	
	private Tblcopie obtenirCopiesDispo(BigDecimal idFilm) throws Exception {
		Criteria criteria = _Session.createCriteria(Tblcopie.class);
		criteria.add(Restrictions.eq("tblfilm.idfilm", idFilm));
		criteria.add(Restrictions.eq("etatcopie", "D"));
		List lstCopies = criteria.list();
		
		if(!lstCopies.isEmpty())
			return (Tblcopie) lstCopies.get(0);
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
