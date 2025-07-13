import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

import java.util.List;


@Test
public void testUrunAramaSonuclariKullanimi(WebDriver driver) {
    List<String> arananKelimeler = List.of("phone", "tablet", "laptop", "camera");

    List<String> sonucListesi = ReusableMethods.urunAramaSonuclari(
            driver, arananKelimeler.toArray(new String[0])
    );
    for (String bilgi : sonucListesi) {
        System.out.println(bilgi);
    }
}

void main() {
}

