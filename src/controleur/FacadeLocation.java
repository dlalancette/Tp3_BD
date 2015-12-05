package controleur;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import modele.util.*;

public class FacadeLocation {
	
	private Courtier _courtier;
	private CourtierUsager _courtierUsager;
	private CourtierConsultation _courtierConsultation;
	private CourtierLocation _courtierLocation;
	
	public FacadeLocation() {
		_courtier = new Courtier();
		_courtierUsager = new CourtierUsager();
		_courtierConsultation = new CourtierConsultation();
		_courtierLocation = new CourtierLocation();
	}
	
	public boolean VerifieConnexion(String courrielUsag, String passUsag) {
		return _courtierUsager.ValidateUser(courrielUsag, passUsag);
	}
	
	public List ObtenirListFilm(String titre,String annee,String paysproduction,String genre,String langue,String realisateur,String acteurs) throws ParseException{
		return _courtierConsultation.GetFilms(titre,annee,paysproduction,genre,langue,realisateur,acteurs);
	}
	
	public Location EffectuerLocation(String titre, String courrielUsag) throws ParseException{

		List lstFilms = _courtierConsultation.GetFilms(titre, "", "", "", "", "", "");
		
		if(!lstFilms.isEmpty())
			return _courtierLocation.effectueLocation((BigDecimal)((Object[])lstFilms.get(0))[0], courrielUsag);
		else 
			return new Location("Film introuvable", new ArrayList());
	}
	
	public void Close(){
		_courtier.Close();
	}
}
