package tests.day13_TestNG_Framework;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage;
import utilities.Driver;

import static utilities.Driver.getDriver;

public class C04_PageClassiKullanma {



    @Test(groups = {"smoke","E2E 1"})
    public void test01(){

        // testotomasyonu anasayfaya gidin
        // Driver.getDriver() ==> driver
        Driver.getDriver().get("https://www.testotomasyonu.com");

        // TestNG POM'de pages class'lari altindaki locate edilmis
        // WEbElement'leri kullanmak icin ilgili class'dan obje olustururuz
        TestOtomasyonuPage testOtomasyonuPage = new TestOtomasyonuPage();

        // phone icin arama yapin
        testOtomasyonuPage.aramaKutusu.sendKeys("phone" + Keys.ENTER);

        // ilk urune tiklayin
        testOtomasyonuPage.bulunanUrunElementleriList
                .get(0)
                .click();

        // acilan sayfadaki urun isminde case sensitive olmadan phone bulundugunu test edin

        String expectedIsimIcerik = "phone";
        String actualIsim = testOtomasyonuPage.ilkUrunSayfasiIsimElementi
                .getText()
                .toLowerCase();

        Assert.assertTrue(actualIsim.contains(expectedIsimIcerik));

    }
}