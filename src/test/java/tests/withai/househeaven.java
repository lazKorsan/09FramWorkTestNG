package tests.withai;

import org.testng.annotations.Test;
import pages.HouseHeavenPage;
import utilities.ConfigReader;
import utilities.Driver;

public class househeaven {

    @Test
    public void test01(){
        Driver.getDriver().get(ConfigReader.getProperty("toHouseHeavenUrl"));
        HouseHeavenPage houseHeavenPage = new HouseHeavenPage() ;

        houseHeavenPage.ilkSayfaSignButonu.click();
        Driver.quitDriver();

    }
}
