package h07_crudProje;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CrudMethodlar {

    private static SessionFactory sf;

    public void sessionFactoryOlustur() {

        try {

            Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Personel.class);
            sf = con.buildSessionFactory();

            //	} catch (Throwable e) {	  // oturum acmak veya yanlis gidebilecek her seyi kesinlikle ele almak istediginiz bir is parcacigini en yüksek "tumunu yakala" duzeyi
        } catch (Exception e) {
            e.printStackTrace();
            //		System.out.println("while creating Session factory error occured... "+e);

            //		throw new ExceptionInInitializerError(e); // 27 veya 29 birini secebilirsin
        }

    }

    // veritabanina bir personel ekleyen method yapalim
    public void personelEkle(String ad, String soyad, int maas) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Personel personel = new Personel(ad, soyad, maas);
            session.save(personel);

            tx.commit();
            System.out.println("Done!");
        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        } finally {
            session.close();
        }

    }

    public void idILePersonelSil(Long personelId) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {

            Personel personel = session.get(Personel.class, personelId);

            if (personel == null) {
                System.out.println(personelId + " ID'li kisinin kaydi bulunmadi");
            } else {
                session.delete(personel);
                tx.commit();
                System.out.println(personelId + " ID'li kisinin kaydi silindi");
                System.out.println("silinen : " + personel);
            }

        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        } finally {
            session.close();
        }

    }

    public void idIleMaasGuncelle(Long personelID, int maas) {

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {

            Personel personel = session.get(Personel.class, personelID);
            if (personel == null) {
                System.out.println(personelID + " ID'li kisinin kaydi bulunmadi");
            } else {
                personel.setMaas(maas);
                tx.commit();
                System.out.println(personelID + " ID'li kisinin yeni maasi " + maas);
            }

        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        } finally {
            session.close();
        }

    }

    public void tumPersoneliListele() {

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {

            List<Personel> liste = session.createQuery("FROM Personel").getResultList();

            liste.stream().forEach((t) -> System.out.println(t));
            tx.commit();

        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        } finally {
            session.close();
        }

    }


    public void sil() {


        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        Scanner scan = new Scanner(System.in);
        System.out.print("lütfen silmek istediğiniz kişinin id sini giriniz:");
        long no = scan.nextLong();


        Personel personel = session.get(Personel.class, no);

        if (personel == null) {
            System.out.println(no + " nolu kisinin kaydi bulunamamistir.");
        } else {
            session.delete(personel);
            tx.commit(); //önce silinmeyi kaydet sonra syso ile göster
            System.out.println(no + " nolu kisinin kaydi silinmistir.");
        }
        scan.close();
    }


}
