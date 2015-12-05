package controleur;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.HibernateUtil;

public class Courtier {

	protected static Session _Session;
	protected static Transaction _Transaction;
	protected static Connection _Connexion;
	
	public Courtier () {
		if(_Session == null)
			_Session = HibernateUtil.getSessionFactory().openSession();
		if(_Transaction == null)
			_Transaction = _Session.beginTransaction();
		if(_Connexion == null)
			_Connexion = _Session.connection(); 
	}
	
	public void Close() {
		if(!_Transaction.wasCommitted())
			_Transaction.commit();
		
		if(_Session != null)
			_Session.close();
		
        HibernateUtil.shutdown();
        _Transaction = null;
        _Session = null;
        _Connexion = null;
	}
	
}
