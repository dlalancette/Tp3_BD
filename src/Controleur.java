package controleur;
import modele.hibernate.*;

import java.math.BigDecimal;
import java.util.Scanner;

import org.hibernate.*;

public class Controleur 
{
	public static void main(String[] args) 
	{
		System.out.println();
        System.out.println("Une premiere session Hibernate qui insere deux editeurs et deux livres");
        
        Session uneSession = HibernateUtil.getSessionFactory().openSession();
        Transaction uneTransaction = uneSession.beginTransaction();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un mot :");
        while (!sc.hasNextInt()) sc.next();
        BigDecimal filmID = new BigDecimal(sc.nextInt());
        
		Tblfilm film =  (Tblfilm) uneSession.get(Tblfilm.class, filmID);
		System.out.println("Titre du film:");
		System.out.println(film.getTitrefilm());
   
        sc.close();
        uneTransaction.commit();
        uneSession.close();
        HibernateUtil.shutdown();
        
        System.exit(1);
	}

}
