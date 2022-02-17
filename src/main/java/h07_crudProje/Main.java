package h07_crudProje;

public class Main {

    public static void main(String[] args) {

        CrudMethodlar method = new CrudMethodlar();

        method.sessionFactoryOlustur();

        // veritabanina personel ekle method'u

//		method.personelEkle("Omer", "Tufan", 7700);		
//		method.personelEkle("Sulayman", "Matkuliyev", 8800);		
//		method.personelEkle("Kursat", "Turgut", 9900);


//		method.idILePersonelSil((long) 6);

        // ID'li kisinin maasini guncelle

//		method.idIleMaasGuncelle((long) 8, 25000);


        // veritabanindan tum kayitlari listele
        method.tumPersoneliListele();

//		method.sil();
//		
//		method.tumPersoneliListele();


    }

}
