package tests.withai;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

import java.time.Duration;
import java.util.List;


public class MailKontrolTest {

    WebDriver driver;
    String dosyaYolu = System.getProperty("user.home") + "/OneDrive/Desktop/innerData/mailKontrolu.xlsx";

    @FindBy(xpath = "(//*[text()='Account'])[1]")
    WebElement accountButton;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "submitlogin")
    WebElement signInButton;

    @FindBy(xpath = "//div[@class='text-center bg-danger p-2 my-2 round']")
    WebElement errorMessage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.testotomasyonu.com");

        PageFactory.initElements(driver, this);
    }

    @Test
    public void testMailKontrol() {
        // Excel'den verileri oku
        List<String[]> mailList = ReusableMethods.readExcelData(dosyaYolu, "Sayfa1");

        for (int i = 0; i < mailList.size(); i++) {
            String[] credentials = mailList.get(i);
            String mail = credentials[0];
            String password = credentials[1];

            // Her test için tarayıcıyı yeniden başlat
            driver.quit();
            setup();

            try {
                // Giriş işlemleri
                accountButton.click();
                emailInput.sendKeys(mail);
                passwordInput.sendKeys(password);
                signInButton.click();

                // Hata mesajını kontrol et
                if (errorMessage.isDisplayed() && errorMessage.getText().contains("Customer not found!")) {
                    ReusableMethods.writeResultToExcel(dosyaYolu, "Sheet1", i + 1, "Customer not found!");
                }

                Thread.sleep(1000); // Bekleme süresi
            } catch (Exception e) {
                System.out.println("Hata oluştu: " + e.getMessage());
                ReusableMethods.writeResultToExcel(dosyaYolu, "Sheet1", i + 1, "Error: " + e.getMessage());
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}