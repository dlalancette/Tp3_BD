package controleur;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.HibernateUtil;

public class Courtier {

	protected Session _Session;
	protected Transaction _Transaction;
	protected Connection _Connexion;
	
	public Courtier () {
		_Session = HibernateUtil.getSessionFactory().openSession();
		_Transaction = _Session.beginTransaction();
		_Connexion = _Session.connection(); 
	}
	
	public void Close() {
		if(_Transaction != null)
			_Transaction.commit();
		
		if(_Session != null)
			_Session.close();
		
        HibernateUtil.shutdown();
	}
	
}
