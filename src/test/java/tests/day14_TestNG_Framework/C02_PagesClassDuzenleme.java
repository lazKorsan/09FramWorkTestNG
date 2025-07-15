package tests.day14_TestNG_Framework;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage;
import utilities.Driver;

public class C02_PagesClassDuzenleme {



    @Test
    public void aramaTesti(){
        // 1. Driver'a static olarak erişip sayfaya gidiyoruz.
        Driver.getDriver().get("https://www.testotomasyonu.com/");

        // 2. TestOtomasyonuPage'den bir NESNE (instance) oluşturuyoruz.
        // Çünkü içindeki WebElement'ler static değil, bu nesneye aittir.
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();

        // 3. Oluşturduğumuz NESNE üzerinden arama kutusuna erişiyoruz.
        testOtomasyonuPage.aramaKutusu.sendKeys("phone"+ Keys.ENTER);

        // 4. Yine NESNE üzerinden ürün listesine erişip sayısını alıyoruz.
        int actualAramaSonucu = testOtomasyonuPage.bulunanUrunElementleriList.size();

        // 5. Sonucu ekrana yazdırıyoruz.
        System.out.println("Arama sonucunda " + actualAramaSonucu + " adet ürün bulundu");

        // 6. Sonucun 0'dan büyük olduğunu doğruluyoruz.
        Assert.assertTrue(actualAramaSonucu > 0,"Arama sonucunda ürün bulunamadı!");
    }

    // İYİLEŞTİRME: Test bittikten sonra tarayıcıyı kapatır.
    @AfterClass
    public void teardown(){
        Driver.quitDriver();
    }
}