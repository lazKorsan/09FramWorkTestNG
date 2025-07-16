package tests.day16_HTMLReports_DataProvider;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class C01_RaporluAramaTesti extends TestBaseRapor {

    /*
        Bir test method'unda raporlama yapmak icin
        yapilacak ek adimlar testin normal calismasini etkilemez

        ONCE test method'unu verilen gorev cercevesinde hazirliyoruz
     */

    @Test
    public void aramaTesti(){

        extentTest = extentReports.createTest("arama testi",
                "Kullanici belirlenen kelimeyi aratip sonuc bulabilmelidir");

        // Testotomasyonu anasayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Kullanici Testotomasyonu anasayfaya gider");

        // url'in "testotomasyonu" icerdigini test edin
        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));
        extentTest.pass("url'in 'testotomasyonu' icerdigini test eder");

        // belirlenmis arama kelimesi icin arama yapin
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();

        testOtomasyonuPage.aramaKutusu
                .sendKeys(ConfigReader.getProperty("toAranacakKelime") + Keys.ENTER);
        extentTest.info("belirlenmis arama kelimesi icin arama yapar");

        // bulunan urun sayisinin 3'den fazla oldugunu test edin

        int expectedMinUrunSayisi = 3;
        int actualBulunanUrunSayisi = testOtomasyonuPage.bulunanUrunElementleriList
                .size();

        Assert.assertTrue(actualBulunanUrunSayisi > expectedMinUrunSayisi);
        extentTest.pass("bulunan urun sayisinin 3'den fazla oldugunu test eder");

        //ilk urunu tiklayin
        testOtomasyonuPage.bulunanUrunElementleriList
                .get(0)
                .click();
        extentTest.info("ilk urunu tiklar");

        // acilan sayfada urun isminde case sensitive olmadan
        // aranacak kelimenin bulundugunu test edin

        String actualUrunIsmi = testOtomasyonuPage.ilkUrunSayfasiIsimElementi
                .getText()
                .toLowerCase();
        String expectedisimIcerik = ConfigReader.getProperty("toAranacakKelime");

        Assert.assertTrue(actualUrunIsmi.contains(expectedisimIcerik));
        extentTest.pass("acilan sayfada urun isminde case sensitive olmadan\n" +
                "        // aranacak kelimenin bulundugunu test edin");

    }

}