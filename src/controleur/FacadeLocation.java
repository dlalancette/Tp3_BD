package controleur;

import java.util.List;

public class FacadeLocation {
	
	Courtier _courtier;
	CourtierUsager _courtierUsager;
	CourtierConsultation _courtierConsultation;
	
	public FacadeLocation() {
		_courtier = new Courtier();
		_courtierUsager = new CourtierUsager();
		_courtierConsultation = new CourtierConsultation();
	}
	
	public boolean VerifieConnexion(String courrielUsag, String passUsag) {
		return true;
		//return _courtierUsager.ValidateUser(courrielUsag, passUsag);
	}
	
	public List ObtenirListFilm(String titre,String annee,String paysproduction,String genre,String langue,String realisateur,String acteurs)
	{
		return _courtierConsultation.GetFilms(titre,annee,paysproduction,genre,langue,realisateur,acteurs);
	}
	
	public void Close(){
		_courtier.Close();
	}
}
