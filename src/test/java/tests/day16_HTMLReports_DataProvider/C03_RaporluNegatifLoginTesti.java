package tests.day16_HTMLReports_DataProvider;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class C03_RaporluNegatifLoginTesti extends TestBaseRapor {

    @Test
    public void gecersizPasswordTesti(){

        extentTest = extentReports.createTest("Gecersiz Password Testi",
                "Kullanici gecersiz password ile sisteme giriz yapamaz");
        // 1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Kullanici https://www.testotomasyonu.com/ anasayfasina gider");

        // 2- account linkine basin
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();
        testOtomasyonuPage.accountLinki.click();
        extentTest.info("account linkine basar");

        //	- gecerli email, gecersiz password girin
        testOtomasyonuPage.loginEmailKutusu
                .sendKeys(ConfigReader.getProperty("toGecerliEmail"));
        extentTest.info("Email olarak gecerli email girer");
        testOtomasyonuPage.loginPasswordKutusu
                .sendKeys(ConfigReader.getProperty("toGecersizPassword"));
        extentTest.info("Password olarak gecersiz password girer");
        //4- Login butonuna basarak login olmayi deneyin
        testOtomasyonuPage.loginSignInButonu
                .click();
        extentTest.info("Login butonuna basarak login olmayi dener");

        //5- Basarili olarak giris yapilamadigini test edin
        Assert.assertTrue(testOtomasyonuPage.loginEmailKutusu.isDisplayed());
        extentTest.pass("Basarili olarak giris yapilamadigini test eder");
    }

    @Test
    public void gecersizEmailTesti(){

        extentTest = extentReports.createTest("Gecersiz Email Testi",
                "Kullanici gecersiz email ile sisteme giriz yapamaz");
        // 1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Kullanici https://www.testotomasyonu.com/ anasayfasina gider");

        // 2- account linkine basin
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();
        testOtomasyonuPage.accountLinki.click();
        extentTest.info("account linkine basar");

        //	- gecersiz email, gecerli password girin
        testOtomasyonuPage.loginEmailKutusu
                .sendKeys(ConfigReader.getProperty("toGecersizEmail"));
        extentTest.info("Email olarak gecersiz email girer");
        testOtomasyonuPage.loginPasswordKutusu
                .sendKeys(ConfigReader.getProperty("toGecerliPassword"));
        extentTest.info("Password olarak gecerli password girer");
        //4- Login butonuna basarak login olmayi deneyin
        testOtomasyonuPage.loginSignInButonu
                .click();
        extentTest.info("Login butonuna basarak login olmayi dener");

        //5- Basarili olarak giris yapilamadigini test edin
        Assert.assertTrue(testOtomasyonuPage.loginEmailKutusu.isDisplayed());
        extentTest.pass("Basarili olarak giris yapilamadigini test eder");
    }

    @Test
    public void gecersizEmailGecersizPasswordTesti(){
        extentTest = extentReports.createTest("Gecersiz Email ve Gecersiz Password Testi",
                "Kullanici gecersiz email ve password ile sisteme giriz yapamaz");
        // 1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Kullanici https://www.testotomasyonu.com/ anasayfasina gider");

        // 2- account linkine basin
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();
        testOtomasyonuPage.accountLinki.click();
        extentTest.info("account linkine basar");

        //	- gecersiz email, gecersiz password girin
        testOtomasyonuPage.loginEmailKutusu
                .sendKeys(ConfigReader.getProperty("toGecersizEmail"));
        extentTest.info("Email olarak gecersiz email girer");
        testOtomasyonuPage.loginPasswordKutusu
                .sendKeys(ConfigReader.getProperty("toGecersizPassword"));
        extentTest.info("Password olarak gecersiz password girer");
        //4- Login butonuna basarak login olmayi deneyin
        testOtomasyonuPage.loginSignInButonu
                .click();
        extentTest.info("Login butonuna basarak login olmayi dener");

        //5- Basarili olarak giris yapilamadigini test edin
        Assert.assertTrue(testOtomasyonuPage.loginEmailKutusu.isDisplayed());
        extentTest.pass("Basarili olarak giris yapilamadigini test eder");
    }
}