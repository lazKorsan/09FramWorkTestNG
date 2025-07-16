package tests.day14_TestNG_Framework;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C07_ConfigurationDosyasiKullanimi {

    @Test
    public void acKapaArtema(){
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        Driver.quitDriver();
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        Driver.quitDriver();
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        Driver.quitDriver();

    }

    @Test
    public void positiveLoginTesti(){



        /*
            Artik test method'larinda DINAMIK OLMAYAN hicbir data istemiyoruz
            Tum locate, url, kullanici adi gibi datalar
            proje ka[psamindaki belirlenmis yerlerde olmali
            test method'unda ihtiyacimiz oldugunda ilgili yerden
            kolayaca ulasabilmeli ve alabilmeliyiz

            pages ==> locate ettigimiz WebElementler
            configuration.properties ==> test datalari
            (url, kullanici adi, sifre, aranacak kelime, kullanilacak browser ...)


         */

        //1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        //2- account linkine basin
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();
        testOtomasyonuPage.accountLinki.click();

        //3- Kullanici email'i olarak gecerli email girin
        testOtomasyonuPage.loginEmailKutusu
                .sendKeys(ConfigReader.getProperty("toGecerliEmail"));
        //4- Kullanici sifresi olarak gecerli password girin
        testOtomasyonuPage.loginPasswordKutusu
                .sendKeys(ConfigReader.getProperty("toGecerliPassword"));
        //5- Login butonuna basarak login olun
        testOtomasyonuPage.loginSignInButonu
                .click();

        //6- Basarili olarak giris yapilabildigini test edin
        Assert.assertTrue(testOtomasyonuPage.logoutButonu.isDisplayed());

        ReusableMethods.bekle(3);
        Driver.quitDriver();
    }
    @Test
    public void test02(){
       Driver.getDriver().get("https://www.testotomasyonu.com/");

        // 1. TestOtomasyonuPage'den bir NESNE (instance) oluşturuyoruz.
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();

        // 2. Oluşturduğumuz NESNE üzerinden arama kutusuna erişiyoruz.
        testOtomasyonuPage.aramaKutusu.sendKeys("phone"+ Keys.ENTER);

        // 3. Yine NESNE üzerinden ürün listesine erişip sayısını alıyoruz.
        int actualAramaSonucu = testOtomasyonuPage.bulunanUrunElementleriList.size();

        // 4. Sonucu ekrana yazdırıyoruz.
        System.out.println("Arama sonucunda " + actualAramaSonucu + " adet ürün bulundu");

        // 5. Sonucun 0'dan büyük olduğunu doğruluyoruz.
        Assert.assertTrue(actualAramaSonucu > 0, "Arama sonucunda ürün bulunamadı!");
    }
}