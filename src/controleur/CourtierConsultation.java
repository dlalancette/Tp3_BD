package controleur;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hsqldb.SessionManager;

import modele.*;

public class CourtierConsultation extends Courtier {
	
	CourtierConsultation() {
		
	}
	
	public List GetFilms(String titre,String annee,String paysproduction,String genre,String langue,String realisateur,String acteurs) {   
		
		String[] lstacteur = null;
		
		if(acteurs.contains(","))
			lstacteur = acteurs.split(",") ;
	

		Criteria criteria = _Session.createCriteria(Tblfilm.class,"Tblfilm");
		criteria.createAlias("tblroles","tblroles");
		criteria.createAlias("tblpaysproductions", "tblpaysproductions");
		criteria.createAlias("tblgenres", "tblgenres");
		criteria.createAlias("tblrealisateurs", "tblrealisateurs");
		criteria.createAlias("tblroles.tblacteur", "tblacteur");
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("Tblfilm.titrefilm"));
		projList.add(Projections.property("Tblfilm.datesortiefilm"));
		projList.add(Projections.property("tblpaysproductions.nompays"));
		projList.add(Projections.property("Tblfilm.langueorigfilm"));
		projList.add(Projections.property("tblgenres.nomgenre"));
		projList.add(Projections.property("tblrealisateurs.prenreal"));
		projList.add(Projections.property("tblrealisateurs.nomreal"));
		projList.add(Projections.property("tblacteur.prenacteur"));
		projList.add(Projections.property("tblacteur.nomacteur"));
		
		criteria.setProjection(projList);
		
		
		if(titre.length() >= 1)
		{
			criteria.add(Restrictions.ilike("Tblfilm.titrefilm", titre));
		}
		
		if(annee.length() >= 1)
		{		
			criteria.add(Restrictions.le("Tblfilm.datesortiefilm", toEndOfYear(Integer.valueOf(annee))));
			criteria.add(Restrictions.ge("Tblfilm.datesortiefilm", toEndOfYear(Integer.valueOf(annee))));
		}
		
		if(paysproduction.length() >= 1)
		{
			criteria.add(Restrictions.eq("tblpaysproductions.nompays", paysproduction));
		}
		
		if(genre.length() >= 1)
		{
			criteria.add(Restrictions.eq("tblgenres.nomgenre", genre));
		}
		
		if(langue.length() >= 1)
		{
			criteria.add(Restrictions.eq("Tblfilm.langueorigfilm", langue));
		}
		
		if(realisateur.length() >= 1)
		{
			Criterion c1 = Restrictions.ilike("tblrealisateurs.nomreal", realisateur);
			Criterion c2 = Restrictions.ilike("tblrealisateurs.prenreal", realisateur);
			Criterion or = Restrictions.or(c1,c2);			
			criteria.add(or);
		}
		
		if(acteurs.length() >= 1)
		{
			for(String acteur : lstacteur)
				{
					Criterion c1 = Restrictions.ilike("tblacteur.prenacteur", acteur);
					Criterion c2 = Restrictions.ilike("tblacteur.nomacteur", acteur);
					Criterion or = Restrictions.or(c1,c2);			
					criteria.add(or);
				}
		}
		
		return criteria.list();
	}
	
	public Date toStartOfYear(int year) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, year);
	    calendar.set(Calendar.DAY_OF_YEAR, 0);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    return calendar.getTime();
	}

	public Date toEndOfYear(int year) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, year);
	    calendar.set(Calendar.DAY_OF_YEAR, 0);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND,-1);
	    return calendar.getTime();
	}
	
}
