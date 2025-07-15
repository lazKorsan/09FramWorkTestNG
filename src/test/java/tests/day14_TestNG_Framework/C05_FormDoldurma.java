package tests.day14_TestNG_Framework;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestOtomasyonuFormPage;

import utilities.Driver;
import utilities.ReusableMethods;

public class C05_FormDoldurma {

    @Test (groups = {"smoke","E2E 1"})
    public void test01(){

        //1 - https://testotomasyonu.com/form adresine gidin
        Driver.getDriver().get("https://testotomasyonu.com/form");
        //2- Sirt Agrisi ve Carpinti checkbox’larini secin
        TestOtomasyonuFormPage toFormPage = new TestOtomasyonuFormPage();

        // eger webelementler sayfada gorunmuyorsa once onlara scrool yapalim
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView({block: 'center'});",toFormPage.carpintiCheckboxElementi);
        ReusableMethods.bekle(1);

        toFormPage.sirtAgrisiYaziElementi.click();
        toFormPage.carpintiCheckboxElementi.click();

        //3- Sirt Agrisi ve Carpinti checkbox’larininin seçili olduğunu test edin

        Assert.assertTrue(toFormPage.carpintiCheckboxElementi.isSelected());
        Assert.assertTrue(toFormPage.sirtAgrisiCheckboxElementi.isSelected());

        //4- Seker ve Epilepsi checkbox’larininin seçili olmadigini test edin
        jse.executeScript("arguments[0].scrollIntoView({block: 'center'});",toFormPage.sekerCheckboxElementi);
        ReusableMethods.bekle(1);

        Assert.assertFalse(toFormPage.sekerCheckboxElementi.isSelected());
        Assert.assertFalse(toFormPage.epilepsiCheckboxElementi.isSelected());

        //5- sayfayi kapatin
        ReusableMethods.bekle(3);
        Driver.quitDriver();
    }
}