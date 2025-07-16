package tests.withai;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class qrockWEbDRiver {
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    private static final String BROWSER_TYPE = "browser";
    private static final String DEFAULT_BROWSER_TYPE = "chrome";
    private static final long IMPLICIT_WAIT = Long.parseLong(
            ConfigReader.getProperty("implicitWait", "10")
    );
    private static final int WARMUP_ATTEMPTS = Integer.parseInt(
            ConfigReader.getProperty("warmupAttempts", "3")
    );

    private void Driver() {}

    /**
     * Returns a thread-safe WebDriver instance after performing warmup if needed.
     * @return WebDriver instance
     * @throws RuntimeException if driver initialization or warmup fails
     */
    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browser = ConfigReader.getProperty(BROWSER_TYPE, DEFAULT_BROWSER_TYPE).toLowerCase();
            logger.info("Initializing {} driver...", browser);

            try {
                // Initialize WebDriver
                WebDriverManager.chromedriver().setup(); // Örnek olarak sadece Chrome
                ChromeOptions options = new ChromeOptions();
                options.addArguments(
                        "--disable-search-engine-choice-screen",
                        "--start-maximized",
                        "--disable-extensions"
                );
                if (Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"))) {
                    options.addArguments("--headless=new");
                }
                driverPool.set(new ChromeDriver(options));

                WebDriver driver = driverPool.get();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));

                // Perform warmup
                performWarmup(driver);

            } catch (Exception e) {
                logger.error("Driver initialization failed for browser {}: {}", browser, e.getMessage(), e);
                throw new RuntimeException("Driver could not be initialized: " + e.getMessage());
            }
        }
        return driverPool.get();
    }

    /**
     * Performs warmup by loading the page multiple times as specified in config.
     * @param driver WebDriver instance to use for warmup
     */
    private static void performWarmup(WebDriver driver) {
        String url = ConfigReader.getProperty("toUrl");
        if (url == null || url.trim().isEmpty()) {
            logger.error("Target URL is not specified in config");
            throw new RuntimeException("Target URL is not specified in config");
        }

        logger.info("Starting warmup for URL: {}", url);
        for (int i = 1; i <= WARMUP_ATTEMPTS; i++) {
            try {
                driver.get(url);
                logger.info("Warmup attempt {} of {} completed", i, WARMUP_ATTEMPTS);
            } catch (Exception e) {
                logger.warn("Warmup attempt {} of {} failed: {}", i, WARMUP_ATTEMPTS, e.getMessage());
            }
        }
        logger.info("Warmup completed");
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