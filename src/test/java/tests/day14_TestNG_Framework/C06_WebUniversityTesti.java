package tests.day14_TestNG_Framework;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WebUniversityPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class C06_WebUniversityTesti {

    @Test
    public void test01(){

        //1."http://webdriveruniversity.com/" adresine gidin
        Driver.getDriver().get("http://webdriveruniversity.com/");
        //2."Login Portal" a  kadar asagi inin
        WebUniversityPage webUniversityPage = new WebUniversityPage();

        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView({block: 'center'});",webUniversityPage.loginPortalButonu);
        ReusableMethods.bekle(1);

        //3."Login Portal" a tiklayin
        webUniversityPage.loginPortalButonu.click();

        //4.Diger window'a gecin
        ReusableMethods.switchWindowByTitle(Driver.getDriver() , "WebDriver | Login Portal");

        //5."username" ve  "password" kutularina fake deger yazdirin
        Faker faker = new Faker();

        webUniversityPage.usernameKutusu
                .sendKeys(faker.name().username());

        webUniversityPage.passwordKutusu
                .sendKeys(faker.internet().password());


        //6."login" butonuna basin

        webUniversityPage.loginButonu
                .click();



        //7.Popup'ta cikan yazinin "validation failed" oldugunu test edin

        String expectedAlertYazisi = "validation failed";
        String actualAlertYazisi = Driver.getDriver()
                .switchTo()
                .alert()
                .getText();

        Assert.assertEquals(  actualAlertYazisi,expectedAlertYazisi);

        //8.Ok diyerek Popup'i kapatin
        Driver.getDriver()
                .switchTo().alert()
                .accept();

        //9.Ilk windowa geri donun
        ReusableMethods.switchWindowByUrl(Driver.getDriver() , "https://webdriveruniversity.com/");

        //10.Ilk windowa donuldugunu test edin
        String expectedTitle = "WebDriverUniversity.com";
        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertEquals(actualTitle,expectedTitle);

        ReusableMethods.bekle(3);
        Driver.quitDriver();
    }
}