package tests.day13_TestNG_Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C02_Priority {
    public WebDriver driver ;
    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }

    @Test
    public void test01(){
        driver.get("https://www.testotomasyonu.com/");
    }
    @Test
    public void wisequarter(){
        driver.get("https://www.wisequarter.com");
    }
    @Test
    public void youtube(){
        driver.get("https://www.youtube.com/");
        System.out.println(driver.getTitle());
    }
}
