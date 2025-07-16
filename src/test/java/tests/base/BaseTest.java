package tests.base;

import org.testng.annotations.BeforeSuite;
import utilities.ConfigReader;
import utilities.Driver;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        int maxRetries = 3; // En fazla 3 kere dene
        boolean connected = false;

        System.out.println("--- Test Suite Başlatılıyor: Ağ bağlantısı 'ısınma' işlemi ---");

        for (int i = 0; i < maxRetries; i++) {
            try {
                System.out.println("Siteye bağlanma denemesi " + (i + 1) + "/" + maxRetries + "...");
                Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
                System.out.println("Bağlantı başarılı!");
                connected = true;
                break; // Bağlantı başarılı olursa döngüden çık
            } catch (Exception e) {
                System.err.println("Deneme " + (i + 1) + " başarısız: " + e.getMessage().substring(0, 80) + "...");
                Driver.quitDriver(); // Başarısız driver'ı kapat, sonraki deneme için temiz bir başlangıç yap
            }
        }

        // Döngü bittikten sonra hala bağlanamadıysak, testleri hiç başlatmadan durdur.
        if (!connected) {
            System.err.println(maxRetries + " deneme sonunda siteye bağlanılamadı. Testler durduruluyor.");
            throw new RuntimeException("Test sitesine ulaşılamadığı için testler başlatılamadı.");
        }

        // Isınma için kullanılan tarayıcıyı kapat. Her test kendi temiz tarayıcısını açacak.
        Driver.quitDriver();
        System.out.println("--- 'Isınma' işlemi tamamlandı. Testler başlıyor. ---");
    }
}