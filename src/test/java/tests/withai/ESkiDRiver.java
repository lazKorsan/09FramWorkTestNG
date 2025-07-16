package tests.withai;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ConfigReader;
import utilities.Driver;

// driver en modern HAli ile getirilmiştir.

import java.time.Duration;

public class ESkiDRiver {
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    private static final String BROWSER_TYPE = "browser";
    private static final String DEFAULT_BROWSER_TYPE = "chrome";
    private static final long IMPLICIT_WAIT = Long.parseLong(
            ConfigReader.getProperty("implicitWait", "10")
    );

    private void Driver() {}

    /**
     * Returns a thread-safe WebDriver instance for the specified browser.
     * @return WebDriver instance
     * @throws RuntimeException if driver initialization fails
     */
    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browser = ConfigReader.getProperty(BROWSER_TYPE, DEFAULT_BROWSER_TYPE);
            if (browser == null || browser.trim().isEmpty()) {
                browser = DEFAULT_BROWSER_TYPE;
                logger.warn("Browser type not specified in config, defaulting to {}", browser);
            }
            browser = browser.toLowerCase();
            logger.info("Initializing {} driver...", browser);

            try {
                switch (browser) {
                    case "firefox":
                        driverPool.set(WebDriverManager.firefoxdriver().capabilities(getFirefoxOptions()).create());
                        break;
                    case "edge":
                        driverPool.set(WebDriverManager.edgedriver().capabilities(getEdgeOptions()).create());
                        break;
                    case "chrome":
                    default:
                        driverPool.set(WebDriverManager.chromedriver().capabilities(getChromeOptions()).create());
                }

                WebDriver driver = driverPool.get();
                if (!Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"))) {
                    driver.manage().window().maximize();
                }
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
            } catch (Exception e) {
                logger.error("Driver initialization failed for browser {}: {}", browser, e.getMessage(), e);
                throw new RuntimeException("Driver could not be initialized: " + e.getMessage());
            }
        }
        return driverPool.get();
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-search-engine-choice-screen",
                "--start-maximized",
                "--disable-extensions"
        );
        if (Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"))) {
            options.addArguments("-headless");
        }
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        if (Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-extensions");
        return options;
    }

    /**
     * Closes or quits the WebDriver instance.
     * @param quit if true, quits the driver; if false, closes the current window
     */
    private static void quitOrCloseDriver(boolean quit) {
        WebDriver driver = driverPool.get();
        if (driver != null) {
            try {
                if (quit) {
                    driver.quit();
                    logger.info("Driver quit successfully");
                } else {
                    driver.close();
                    logger.info("Driver closed successfully");
                }
            } catch (Exception e) {
                logger.error("Failed to {} driver: {}", quit ? "quit" : "close", e.getMessage());
            } finally {
                driverPool.remove();
            }
        }
    }

    public static void closeDriver() {
        quitOrCloseDriver(false);
    }

    public static void quitDriver() {
        quitOrCloseDriver(true);
    }
}