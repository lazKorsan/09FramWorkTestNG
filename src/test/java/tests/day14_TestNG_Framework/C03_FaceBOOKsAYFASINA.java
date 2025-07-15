package tests.day14_TestNG_Framework;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FaceBookPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class C03_FaceBOOKsAYFASINA {


    @Test

    public void FaceBook(){
        Driver.getDriver().get("https://www.facebook.com/");
        FaceBookPage faceBookPages = new FaceBookPage();

        Faker faker = new Faker();
        faceBookPages.emailKutusu.sendKeys(faker.internet().emailAddress());
        faceBookPages.passwordKutusu.sendKeys(faker.internet().password());
        faceBookPages.loginButonu.click();
        ReusableMethods.bekle(3);

        Assert.assertTrue(faceBookPages.emailKutusu.isDisplayed());

    }
}
