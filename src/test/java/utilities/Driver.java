package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;

/**
 * Singleton Pattern ile WebDriver yönetimini sağlayan utility sınıfı.
 * Çeşitli tarayıcılar için WebDriver oluşturur ve thread-safe bir şekilde yönetir.
 */
public class Driver {

    // Thread-safe WebDriver yönetimi için ThreadLocal kullanımı
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    // Sabitler
    private static final String BROWSER_PROPERTY = "browser";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final long IMPLICIT_WAIT_SECONDS = Long.parseLong(
            ConfigReader.getProperty("implicitWait", "10")
    );

    // Singleton Pattern: Constructor'ı private yaparak obje oluşturulmasını engelle
    private Driver() {
        // Singleton Pattern için
    }

    /**
     * Yapılandırılmış WebDriver örneğini döndürür.
     * Eğer daha önce oluşturulmamışsa, ConfigReader'dan alınan tarayıcı türüne göre WebDriver başlatır.
     *
     * @return WebDriver örneği
     */
    public static WebDriver getDriver() {
        // WebDriver örneği yoksa oluştur
        if (driverPool.get() == null) {
            String browser = ConfigReader.getProperty(BROWSER_PROPERTY, DEFAULT_BROWSER).toLowerCase();

            switch (browser) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driverPool.set(new FirefoxDriver(firefoxOptions));
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driverPool.set(new EdgeDriver(edgeOptions));
                    break;

                case "safari":
                    WebDriverManager.safaridriver().setup();
                    SafariOptions safariOptions = new SafariOptions();
                    driverPool.set(new SafariDriver(safariOptions));
                    break;

                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-search-engine-choice-screen");
                    // Başka Chrome seçenekleri eklenebilir, örneğin:
                    // chromeOptions.addArguments("--headless"); // Headless mod
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
            }

            // Ortak WebDriver ayarları
            WebDriver driver = driverPool.get();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
        }

        return driverPool.get();
    }

    /**
     * Mevcut tarayıcı penceresini kapatır (yalnızca aktif pencereyi).
     */
    public static void closeDriver() {
        WebDriver driver = driverPool.get();
        if (driver != null) {
            driver.close();
            driverPool.remove();
        }
    }

    /**
     * WebDriver'ı tamamen kapatır ve tüm oturumu sonlandırır.
     */
    public static void quitDriver() {
        WebDriver driver = driverPool.get();
        if (driver != null) {
            driver.quit();
            driverPool.remove();
        }
    }
}