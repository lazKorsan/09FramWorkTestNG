package tests.day16_HTMLReports_DataProvider;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class C02_RaporluPozitifLoginTesti extends TestBaseRapor {

    /*
        TestNG'de calisan bir test method'unu raporlu hale getirmek istedigimizde
        1- class'i TestBaseRapor'a extend edin
        2- her method'da extentTest objesine deger atayip
           raporda info cikmasini istedigimiz satirlara extendTest.info()
           assertion olan satirlara ise extendTest.pass()
           method'lari ile aciklama ekleyin
     */
    @Test
    public void positiveLoginTesti(){

        extentTest = extentReports.createTest("Pozitif login testi",
                "Kullanici gecerli bilgilerle sisteme giris yapabilmeli");

        //1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Kullanici https://www.testotomasyonu.com/ anasayfasina gider");

        //2- account linkine basin
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();
        testOtomasyonuPage.accountLinki.click();
        extentTest.info("account linkine basar");

        //3- Kullanici email'i olarak gecerli email girin
        testOtomasyonuPage.loginEmailKutusu
                .sendKeys(ConfigReader.getProperty("toGecerliEmail"));
        extentTest.info("Kullanici email'i olarak gecerli email girer");

        //4- Kullanici sifresi olarak gecerli password girin
        testOtomasyonuPage.loginPasswordKutusu
                .sendKeys(ConfigReader.getProperty("toGecerliPassword"));
        extentTest.info("Kullanici sifresi olarak gecerli password girer");

        //5- Login butonuna basarak login olun
        testOtomasyonuPage.loginSignInButonu
                .click();
        extentTest.info("Login butonuna basarak login olur");

        //6- Basarili olarak giris yapilabildigini test edin
        Assert.assertTrue(testOtomasyonuPage.logoutButonu.isDisplayed());
        extentTest.pass("Basarili olarak giris yapilabildigini test eder");
    }
}