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
		_Session = HibernateUtil.getSessionFactory().openSession();
		_Transaction = _Session.beginTransaction();
		_Connexion = _Session.connection(); 
	}
	
	public void Close() {
        _Transaction.commit();
        _Session.close();
        HibernateUtil.shutdown();
	}
	
}
