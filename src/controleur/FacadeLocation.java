package controleur;

public class FacadeLocation {
	
	Courtier _courtier;
	CourtierUsager _courtierUsager;
	
	public FacadeLocation() {
		_courtier = new Courtier();
		_courtierUsager = new CourtierUsager();
	}
	
	public boolean VerifieConnexion(String courrielUsag, String passUsag) {
		return _courtierUsager.ValidateUser(courrielUsag, passUsag);
	}
	
	public void Close(){
		_courtier.Close();
	}
}
