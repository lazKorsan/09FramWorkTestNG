package tests.day14_TestNG_Framework;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.TestOtomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C08_NegatifLoginTest2i2 {
    private TestOtomasyonuPage testOtomasyonuPage;
    private WebDriver driver;

    /**
     * Initializes the WebDriver and page object before each test.
     */
    @BeforeMethod
    public void setup() {
        driver = Driver.getDriver();
        testOtomasyonuPage = new TestOtomasyonuPage();
    }

    /**
     * Closes the WebDriver after each test.
     */
    @AfterMethod
    public void teardown() {
        Driver.quitDriver();
    }

    /**
     * Tests that login fails with a valid email and invalid password.
     */
    @Test(description = "Geçerli email, geçersiz şifre ile giriş yapılamamalı")
    public void gecersizPasswordTesti() {
        // 1. Sayfaya git (Driver sınıfında ısınma turu zaten yapıldı)
        driver.get(ConfigReader.getProperty("toUrl"));

        // 2. Account linkine bas
        testOtomasyonuPage.accountLinki.click();

        // 3. Geçerli email, geçersiz password yaz
        testOtomasyonuPage.loginEmailKutusu
                .sendKeys(ConfigReader.getProperty("toGecerliEmail"));
        testOtomasyonuPage.loginPasswordKutusu
                .sendKeys(ConfigReader.getProperty("toGecersizPassword"));

        // 4. Login butonuna basarak login olmayı dene
        testOtomasyonuPage.loginSignInButonu.click();

        // 5. Başarılı olarak giriş yapılamadığını test et
        Assert.assertTrue(testOtomasyonuPage.loginEmailKutusu.isDisplayed(),
                "Geçersiz şifre ile giriş yapılabilmesi beklenmiyordu, ancak yapıldı!");
    }
}