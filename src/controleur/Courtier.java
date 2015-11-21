package controleur;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modele.HibernateUtil;

public class Courtier {

	protected static Session _Session;
	protected static Transaction _Transaction;
	
	public Courtier () {
		_Session = HibernateUtil.getSessionFactory().openSession();
		_Transaction = _Session.beginTransaction();
	}
	
	public void Close() {
        _Transaction.commit();
        _Session.close();
        HibernateUtil.shutdown();
	}
	
}
