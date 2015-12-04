package controleur;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		
		if(acteurs != null && acteurs.contains(","))
			lstacteur = acteurs.split(",") ;
	

		Criteria criteria = _Session.createCriteria(Tblfilm.class,"Tblfilm");	
		criteria.createAlias("tblpaysproductions", "tblpaysproductions");
		criteria.createAlias("tblgenres", "tblgenres");
		criteria.createAlias("tblrealisateurs", "tblrealisateurs");
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("Tblfilm.idfilm"));
		projList.add(Projections.property("Tblfilm.titrefilm"));
		projList.add(Projections.property("Tblfilm.datesortiefilm"));
		projList.add(Projections.property("tblpaysproductions.nompays"));
		projList.add(Projections.property("Tblfilm.langueorigfilm"));
		projList.add(Projections.property("tblgenres.nomgenre"));
		projList.add(Projections.property("tblrealisateurs.prenreal"));
		projList.add(Projections.property("tblrealisateurs.nomreal"));
		//projList.add(Projections.property("tblgenres.idgenre"));
		//projList.add(Projections.property("tblrealisateurs.idreal"));
		
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
			criteria.add(Restrictions.ilike("tblpaysproductions.nompays", paysproduction));
		}
		
		if(genre.length() >= 1)
		{
			criteria.add(Restrictions.ilike("tblgenres.nomgenre", genre));
		}
		
		if(langue.length() >= 1)
		{
			criteria.add(Restrictions.ilike("Tblfilm.langueorigfilm", langue));
		}
		
		if(realisateur.length() >= 1)
		{
			Criterion c1 = Restrictions.ilike("tblrealisateurs.nomreal", realisateur);
			Criterion c2 = Restrictions.ilike("ßßtblrealisateurs.prenreal", realisateur);
			Criterion or = Restrictions.or(c1,c2);			
			criteria.add(or);
		}
		
		//if(acteurs.length() >= 1)
		//{
		//	for(String acteur : lstacteur)
		//		{
		//			Criterion c1 = Restrictions.ilike("tblacteur.prenacteur", acteur);
		//			Criterion c2 = Restrictions.ilike("tblacteur.nomacteur", acteur);
		//			Criterion or = Restrictions.or(c1,c2);			
		//			criteria.add(or);
		//		}
		//}
		
		List listFilms = criteria.list();
		String listeNomActeurs = "";
		String listePrenomActeurs = "";
		
		Object[] arrayFilm =  listFilms.toArray(); 
		

			listeNomActeurs = "";
			listePrenomActeurs = "";

			Criteria criteria2 = _Session.createCriteria(Tblacteur.class,"Tblacteur");
			
			List<Tblacteur> lstActeurs  = criteria2.list(); 
			

			for(int i = 0 ; i < arrayFilm.length ; i++)
			{
				
				Object[] film = (Object[]) arrayFilm[i];
				BigDecimal idFilm =  (BigDecimal)film[0];
			
				listeNomActeurs = "";
				listePrenomActeurs = "";
				for(int nbrActeur=0;nbrActeur < lstActeurs.size();nbrActeur++)
				{
					Set<Tblrole> setTblRoles = lstActeurs.get(nbrActeur).getTblroles();
			
					for (Iterator<Tblrole> it = setTblRoles.iterator(); it.hasNext();)
					{
						Tblrole tblrole = it.next();
						if (tblrole.getTblfilm().getIdfilm().equals(idFilm))
						{
							listeNomActeurs = listeNomActeurs +  lstActeurs.get(nbrActeur).getNomacteur() + ", " + lstActeurs.get(nbrActeur).getPrenacteur() + ";";
						}
					}
				}
				Object[] objFilm = new Object[8];
				objFilm = (Object[]) listFilms.get(i);
				objFilm[8] = listeNomActeurs;
				listFilms.set(i, (Object[])objFilm);
			}
		
		return listFilms;
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
