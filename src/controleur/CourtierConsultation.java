package controleur;

import java.math.BigDecimal;
import java.sql.Array;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hsqldb.SessionManager;

import modele.*;

public class CourtierConsultation extends Courtier {
	
	CourtierConsultation() {
		super();
	}
	
	public List GetFilms(String titre,String annee,String paysproduction,String genre,String langue,String realisateur,String acteurs) throws ParseException {   
		
		String[] lstacteur = null;
		
		//Génération de la liste des acteurs pour la requete.
		if(acteurs != null && acteurs.contains(","))
			lstacteur = acteurs.trim().split(",") ;
		else
		{
			lstacteur = new String[1];
			lstacteur[0] = acteurs;
		}

		Criteria criteria = _Session.createCriteria(Tblfilm.class,"Tblfilm");	
		criteria.createAlias("tblpaysproductions", "tblpaysproductions");
		criteria.createAlias("tblgenres", "tblgenres");
		criteria.createAlias("tblrealisateurs", "tblrealisateurs");
		//Les left join ne fonctionne pas ... 
		criteria.createAlias("tblroles", "tblroles",Criteria.LEFT_JOIN);
		criteria.createAlias("tblroles.tblacteur", "tblacteur",Criteria.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("Tblfilm.idfilm"));
		projList.add(Projections.property("Tblfilm.titrefilm"));
		projList.add(Projections.property("Tblfilm.datesortiefilm"));
		projList.add(Projections.property("tblpaysproductions.nompays"));
		projList.add(Projections.property("Tblfilm.langueorigfilm"));
		projList.add(Projections.property("tblgenres.nomgenre"));
		projList.add(Projections.property("tblrealisateurs.prenreal"));
		projList.add(Projections.property("tblrealisateurs.nomreal"));
		//Cette ligne sera modifier pour mettre les acteurs manuellement
		projList.add(Projections.property("tblrealisateurs.idreal"));
		
		criteria.setProjection(projList);
		
		//Ajout des contraintes si elles ont lieu
		if(titre.length() >= 1)
		{
			criteria.add(Restrictions.ilike("Tblfilm.titrefilm", titre,MatchMode.ANYWHERE));
		}
		
		if(annee.length() >= 1)
		{	//Si on a juste l'année on créer deux date soit xxxx-01-01 et xxxx-12-31 pour trouver 
			//tous les films compris entre ces dates.
			if(annee.length() <= 4)
			{
				String debutAnnee,finAnnee;
				debutAnnee = annee + "-01-01";
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date debutDate = formatter.parse(debutAnnee);
				finAnnee = annee + "-12-31";
				Date finDate = formatter.parse(finAnnee);
				criteria.add(Restrictions.between("Tblfilm.datesortiefilm",debutDate ,finDate));
			}
			else {//Si on à une date complete.
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(annee);
			criteria.add(Restrictions.eq("Tblfilm.datesortiefilm", date));
			}
		}
		
		if(paysproduction.length() >= 1)
		{
			criteria.add(Restrictions.ilike("tblpaysproductions.nompays", paysproduction,MatchMode.ANYWHERE));
		}
		
		if(genre.length() >= 1)
		{
			criteria.add(Restrictions.ilike("tblgenres.nomgenre", genre,MatchMode.ANYWHERE));
		}
		
		if(langue.length() >= 1)
		{
			criteria.add(Restrictions.ilike("Tblfilm.langueorigfilm", langue,MatchMode.ANYWHERE));
		}
		
		if(realisateur.length() >= 1)
		{
			Criterion c1 = Restrictions.ilike("tblrealisateurs.nomreal", realisateur,MatchMode.ANYWHERE);
			Criterion c2 = Restrictions.ilike("tblrealisateurs.prenreal", realisateur,MatchMode.ANYWHERE);		
			LogicalExpression orExp = Restrictions.or(c1, c2);
			criteria.add(orExp);
		}
		
		if(acteurs.length() >= 1)
		{
			for(String acteur : lstacteur)
			{
				Criterion c1 = Restrictions.ilike("tblacteur.prenacteur", acteur,MatchMode.ANYWHERE);
				Criterion c2 = Restrictions.ilike("tblacteur.nomacteur", acteur,MatchMode.ANYWHERE);		
				LogicalExpression orExp = Restrictions.or(c1, c2);
				criteria.add(orExp);
			}
		}
		//Ce qui suit permet d'ajouter les acteurs sur seulement une ligne
		//c'est un moyen un peu détourné et pas optimiser afin de les obtenirs
		List doublelistFilms = criteria.list();
		List listFilms = new ArrayList<>();
		//Retire les films en double car il était impossible de faire le left join.
		Object[] arrayFilm =  doublelistFilms.toArray();
		
		for(int i = 0 ; i < arrayFilm.length ; i++)
		{
			if(i+1 == arrayFilm.length )
			{
				listFilms.add(arrayFilm[i]);
			}
			else
			{
				Object[] film1 = (Object[]) arrayFilm[i];
				Object[] film2 = (Object[]) arrayFilm[i+1];
				if(!film1[0].equals(film2[0]))
				{
					listFilms.add(film1);
				}
			}
		}
		
		String listeNomActeurs = "";
		String listePrenomActeurs = "";
		
		listeNomActeurs = "";
		listePrenomActeurs = "";

		Criteria criteria2 = _Session.createCriteria(Tblacteur.class,"Tblacteur");
			
		List<Tblacteur> lstActeurs  = criteria2.list(); 
		//Procédure pour ajouter les acteurs manuellement: 3 boucles imbriqués qui boucle
		//les films, les roles et les acteurs afin de les ajouter.
		for(int i = 0 ; i < listFilms.size() ; i++)
		{	
			Object[] film = (Object[]) listFilms.get(i);
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
			//On modifit l'object du film pour ajouter les acteurs et on le remet dans la liste.
			Object[] objFilm = (Object[]) listFilms.get(i);
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
	
	public static String retireAccent(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
	
}
