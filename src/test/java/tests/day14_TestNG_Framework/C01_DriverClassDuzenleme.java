package tests.day14_TestNG_Framework;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

public class C01_DriverClassDuzenleme {

    @Test(groups = {"regression", "E2E 2"})
    public void test01(){

        /*
            Driver class'inda kucuk bir duzeltme yapmamiz gerekiyor
         */

        // testotomasyonu anasayfaya gidin
        Driver.getDriver().get("https://www.testotomasyonu.com");

        // url'in testotomasyonu icerdigini test edin

        String expectedUrlIcerik = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();
        System.out.println(actualUrl);
        String actualTitle = Driver.getDriver().getTitle();
        System.out.println(actualTitle);
        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));

        // window'u kapatin
        Driver.quitDriver();

    }
}