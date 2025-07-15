package tests.day14_TestNG_Framework;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import pages.TestOtomasyonuFormPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class C04_DropDawnMenuTesti {

    @Test
    public void test01(){
        Driver.getDriver().get("https://www.testotomasyonu.com/form");
        TestOtomasyonuFormPage testOtomasyonuFormPage = new TestOtomasyonuFormPage();

        Select selectGun = new Select(testOtomasyonuFormPage.gunDropdownElementi);
        selectGun.selectByVisibleText("5");

        Select selectAy = new Select(testOtomasyonuFormPage.ayDropdownElementi);
        selectAy.selectByVisibleText("Mayıs");



        Select selectYil = new Select(testOtomasyonuFormPage.yilDropdownElementi);
        selectYil.selectByVisibleText("1990");

        System.out.println(ReusableMethods.getStringList(selectAy.getOptions()));

        System.out.println("AyDropDown Menusunun Boyutu: "+selectAy.getOptions().size());


        System.out.println("Seçilen Gün: " + selectGun.getFirstSelectedOption().getText());
        System.out.println("Seçilen Ay: " + selectAy.getFirstSelectedOption().getText());
        System.out.println("Seçilen Yıl: " + selectYil.getFirstSelectedOption().getText());




    }
}
