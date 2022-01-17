package h03_OnetoOne;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H4_Fetch {

	public static void main(String[] args) {
		
		SessionFactory sf =  new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(H1_Kisi.class).addAnnotatedClass(H2_Gunluk.class).buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		
		//1) id=101 olan kisi bilgileri getir
//		System.out.println("1.Soru: " + session.get(H1_Kisi.class, 101));
		
//		System.out.println();
		//2) id=12 olan kisi bilgileri getir
//		System.out.println("2.Soru: " + session.get(H2_Gunluk.class, 12));
		
		//3) gunluk class'indaki butun verileri getir
//		for (int i = 11; i < 15; i++) {
//			System.out.println("Tercih edilmeyen yontem : "+session.get(H2_Gunluk.class, i));
//		}
		
		
		// iki tablo'daki tum bilgileri getir
		
		
//		List<Object[] > liste= session.createQuery("from H1_Kisi k join H2_Gunluk g on  k.kisiId=g.kisi").getResultList();
//
//		liste.forEach((x)-> System.out.println(Arrays.toString(x))); 
		
		
		
		//3) Kisiler ve Gunlukler tablolarindaki ortak olan (one to one ile birebir bağladığımız) kayıtların,
		// Kisi adi, gunluk yazisi(yazilar) ve kisi yası (kisiYas) bilgilerini sorgulayiniz.
		// SQL komutlari
		String sorgu = "select k.kisi_ad, g.owner, k.kisiYas from kisiler k inner join gunlukler g on k.kisi_id = g.baglanti  ";
		
		List<Object[]> sonuc = session.createSQLQuery(sorgu).getResultList();
		for (Object[] w : sonuc) {
			System.out.println(Arrays.toString(w));
		}
		
		
		
		
		
		tx.commit(); 			// hemen db ye gitmesini saglar
		
		sf.close();
		session.close();
		
	}
	
}