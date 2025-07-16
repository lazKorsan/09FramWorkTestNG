package tests.day16_HTMLReports_DataProvider;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C05_DataProvider {

    @DataProvider
    public static Object[][] kelimeleriGonderenDataProvider() {

        String[][] aranacakKelimelerArrayi = {{"java"}, {"phone"}, {"apple"}, {"samsung"}, {"nutella"}, {"cokoprens"}, {"dress"}};


        return aranacakKelimelerArrayi;
    }

    /*
        TestNG'nin bize bir alternatif olarak sundugu DataProvider
        test edecegimiz kelimeleri disardan teker teker bize yollar

        java, phone, apple, samsung, nutella, cokoprens ve dress kelimeleri icin arama yapin

           1- DataProvider'in gonderdigi kelimeleri
              test method'unda kullanmak icin bir parametre olusturalim
              parametre ile gelen TEK BIR kelimeyi aratacak sekilde
              testimizi yazalim

           2- @Test notasyonun oldugu satira
              bize aranacak kelimeleri yollaycak bir data provider ismi yaziyoruz
              (dataProvider = "kelimeleriGonderenDataProvider")

           3- bu data provider henuz var olmadigindan
              ustune yaklasip, IntelliJ'ye olusturtuyoruz

           4- Data provider'in bize gonderecegi datalari
              iki katli bir array olarak hazirliyoruz


     */


    @Test(dataProvider = "kelimeleriGonderenDataProvider")
    public void dataProviderIleCokluAramaTesti( String aranacakKelime ){

        // test otomasyonu anasayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // arama kutusuna parametre olarak gelen aranacakKelime'yi yazip aratin
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();

        testOtomasyonuPage.aramaKutusu.sendKeys(aranacakKelime + Keys.ENTER);

        String unExpectedSonucYazisi = "0 Products Found";
        String actualSonucYazisi = testOtomasyonuPage.aramaSonucuElementi.getText();

        Assert.assertNotEquals(actualSonucYazisi,unExpectedSonucYazisi);

        Driver.quitDriver();

    }

}