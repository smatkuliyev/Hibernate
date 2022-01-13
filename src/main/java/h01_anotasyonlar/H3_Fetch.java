package h01_anotasyonlar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H3_Fetch {

	public static void main(String[] args) {
		
		SessionFactory sf =  new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(H1_Sehir.class).buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		// sehir tablosundan istenilen id'li sehiri getir
		System.out.println(session.get(H1_Sehir.class,37)); 		// select * from sehir_tablosu whre sehirPlaka=37
		
//		session.get(H1_Sehir.class,37).setSehirPlaka(34);			// primary key oldugu icin degistiremiyoruz
//		session.get(H1_Sehir.class,37).setSehirAdi("Istanbul");; 	// ama digerleri degistirebiliriz
		
		System.out.println("Sehir nufus: " + session.get(H1_Sehir.class, 35).getSehirNufus());
		// select sehirNufus from sehir_tablosu where sehirPlaka=37
		
		
		tx.commit(); 			// hemen db ye gitmesini saglar
		
		sf.close();
		session.close();
	}

}
