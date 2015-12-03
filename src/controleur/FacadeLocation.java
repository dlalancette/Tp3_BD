package controleur;

import java.math.BigDecimal;
import java.util.List;

public class FacadeLocation {
	
	private Courtier _courtier;
	private CourtierUsager _courtierUsager;
	private CourtierConsultation _courtierConsultation;
	private CourtierLocation _courtierLocation;
	
	public FacadeLocation() {
		
	}
	
	public boolean VerifieConnexion(String courrielUsag, String passUsag) {
		_courtierUsager = new CourtierUsager();
		return _courtierUsager.ValidateUser(courrielUsag, passUsag);
	}
	
	public List ObtenirListFilm(String titre,String annee,String paysproduction,String genre,String langue,String realisateur,String acteurs){
		_courtierConsultation = new CourtierConsultation();
		return _courtierConsultation.GetFilms(titre,annee,paysproduction,genre,langue,realisateur,acteurs);
	}
	
	public String EffectuerLocation(BigDecimal idFilm, String courrielUsag){
		_courtierLocation = new CourtierLocation();
		return _courtierLocation.effectueLocation(idFilm, courrielUsag);
	}
	
	public void Close(){
		if(_courtierUsager != null)
			_courtierUsager.Close();
		if(_courtierConsultation != null)
			_courtierConsultation.Close();
		if(_courtierLocation != null)
			_courtierLocation.Close();
	}
}
