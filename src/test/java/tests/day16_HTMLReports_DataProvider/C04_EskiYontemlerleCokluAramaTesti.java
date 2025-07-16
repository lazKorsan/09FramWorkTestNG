package tests.day16_HTMLReports_DataProvider;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C04_EskiYontemlerleCokluAramaTesti {

    @Test
    public void cokluAramaTesti(){

        // test otomasyonu anasayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // java, phone, apple, samsung, nutella, cokoprens ve dress kelimeleri icin arama yapin

        List<String> aranacakKelimelerListesi =
                new ArrayList<>(Arrays.asList("java", "phone", "apple", "samsung", "nutella", "cokoprens", "dress"));

        List<String> urunBulunanKelimelerListesi = new ArrayList<>();
        List<String> urunBulunamayanKelimelerListesi = new ArrayList<>();

        // arama sonucunda urun bulunabildigini test edin
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < aranacakKelimelerListesi.size() ; i++) {

            testOtomasyonuPage.aramaKutusu.sendKeys( aranacakKelimelerListesi.get(i) + Keys.ENTER);
            ReusableMethods.bekle(1);
            String unExpectedSonucYazisi = "0 Products Found";
            String actualSonucYazisi = testOtomasyonuPage.aramaSonucuElementi.getText();

            if (actualSonucYazisi.equals(unExpectedSonucYazisi)){
                urunBulunamayanKelimelerListesi.add(aranacakKelimelerListesi.get(i));
            }else {
                urunBulunanKelimelerListesi.add(aranacakKelimelerListesi.get(i));
            }

            softAssert.assertNotEquals(actualSonucYazisi,unExpectedSonucYazisi,
                    "Bulunamayan kelime : "+ aranacakKelimelerListesi.get(i));

        }
        System.out.println("Urun bulunan kelimeler : " + urunBulunanKelimelerListesi);
        System.out.println("Urun bulunamayan kelimeler : " + urunBulunamayanKelimelerListesi);
        softAssert.assertAll();
    }
}