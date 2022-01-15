package h04_OneToMany;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H4_Fetch {

	public static void main(String[] args) {
		
		Configuration con = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(H1_Ogrenci.class)
				.addAnnotatedClass(H2_Kitap.class);

		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		//1-) id=111 olan ogrencinin tum kitaplarini listele
		
//		H1_Ogrenci ogrenci=  session.get(H1_Ogrenci.class, 111);
		
//		System.out.println(session.get(H1_Ogrenci.class, 111).getKitapListesi());
		
//		for (H2_Kitap w : ogrenci.getKitapListesi()) {
//			System.out.println(w);
//		}
		
		//2-) id=50 olan kitabin sahibinin bilgilerini listele
			
//		H2_Kitap kitap =  session.get(H2_Kitap.class, 50);
//		System.out.println(kitap.getOgrenci());
		
		//3-) kitaplar ve ogrenciler tablo'larindaki ortak olan ogrenci bilgilerini listeleyiniz(HQL)
		
//		String sorgu = " SELECT o.ogrAd, k.isim FROM H1_Ogrenci o INNER JOIN H2_Kitap k ON o.ogrId = k.ogrenci ";
//		
//		List<Object[]> liste =  session.createQuery(sorgu).getResultList();
//		
//		liste.stream().forEach(t->System.out.println(Arrays.toString(t)));
		
		
		
		// silme islemi, parent'i silince child da silinecek
		
//		session.delete(session.get(H1_Ogrenci.class, 111));
//		System.out.println("silindi");
	
		
		// hql ile tum kitaplari sil
		
		int sayi = session.createQuery("DELETE FROM H2_Kitap").executeUpdate();
		System.out.println(sayi);
		
		
		
		
		
		
		tx.commit();			
		sf.close();
		session.close();
	}

}
