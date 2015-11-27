package controleur;

import java.math.BigDecimal;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import modele.*;

public class CourtierUsager extends Courtier {
	
	CourtierUsager() {
		
	}
	
	public boolean ValidateUser(String courrielUsag, String passUser) {
		Criteria criteria = _Session.createCriteria(Tblusager.class);
		criteria.add(Restrictions.eq("courrielusag", courrielUsag));
		criteria.add(Restrictions.eq("passusag", passUser));
		Tblusager objUsager = (Tblusager)criteria.uniqueResult();                			  
	
		return objUsager == null ? false : true;
	}
}
