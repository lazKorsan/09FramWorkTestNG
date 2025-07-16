package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HouseHeavenPage {
@FindBy(xpath = "//li[@class='add-listing nav-submenu-open']")
    public  WebElement ilkSayfaSignButonu ;

@FindBy(xpath = "//div[@class=' heading-sm mb-4']")
    public WebElement ilkUrunSayfasiIsimElementi;
}
