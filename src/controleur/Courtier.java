package controleur;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.HibernateUtil;

//Classe parente à tous les Courtiers
//Permet d'initialiser au besoin, une connexion et transaction et de la fermer
public class Courtier {

	//Déclaration de variables statiques à toutes les classes
	protected static Session _Session;
	protected static Transaction _Transaction;
	protected static Connection _Connexion;
	
	public Courtier () {
		//Si aucune session d'instanciée
		if(_Session == null) //Alors on en ouvre une nouvelle
			_Session = HibernateUtil.getSessionFactory().openSession();
		//Si aucune transaction d'instanciée
		if(_Transaction == null)//Alors on débute une nouvelle transaction
			_Transaction = _Session.beginTransaction();
		//On assigne l'objet Connexion
		if(_Connexion == null)
			_Connexion = _Session.connection(); 
	}
	
	//Fermeture des objets Session et Transaction
	public void Close() {
		if(!_Transaction.wasCommitted())
			_Transaction.commit();
		
		if(_Session != null)
			_Session.close();
		
        HibernateUtil.shutdown();
        
        //On réinitialise les variables
        _Transaction = null;
        _Session = null;
        _Connexion = null;
	}
	
}
