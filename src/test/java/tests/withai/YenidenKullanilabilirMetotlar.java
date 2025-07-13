package tests.withai;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class YenidenKullanilabilirMetotlar {

    /**
     * Bu metot, verilen WebDriver'ı kullanarak belirtilen ürünü arar
     * ve arama sonuçlarının geldiğini doğrular.
     * @param driver Aktif WebDriver nesnesi
     * @param aranacakUrun Arama kutusuna yazılacak ürün adı
     */
    public void urunAraVeDogrula(WebDriver driver, String aranacakUrun) {
        // 1. Arama kutusunu bul
        WebElement aramaKutusu = driver.findElement(By.id("global-search"));

        // 2. Arama kutusunu temizle (önceki aramalardan kalma metin olabilir)
        aramaKutusu.clear();

        // 3. Verilen ürünü arama kutusuna gönder ve Enter'a basarak aramayı başlat
        System.out.println(aranacakUrun + " için arama yapılıyor...");
        aramaKutusu.sendKeys(aranacakUrun + Keys.ENTER);

        // 4. Arama sonuçlarının yüklendiğini doğrula
        //    Örnek olarak, sonuç sayısını gösteren elementin varlığını kontrol edelim.
        WebElement sonucYazisiElementi = driver.findElement(By.className("product-count-text"));

        // 5. Sonuç yazısında "bulundu" kelimesinin geçtiğini doğrula (assertion)
        Assert.assertTrue(
                sonucYazisiElementi.isDisplayed(),
                "'" + aranacakUrun + "' için arama sonuçları yüklenemedi."
        );

        System.out.println("'" + aranacakUrun + "' için arama başarıyla doğrulandı.");
    }
    // Bu metot zaten utilities.ReusableMethods dosyanızda mevcut.
    public static List<String> urunAramaSonuclari(WebDriver driver, String... arananKelimeler) {
        List<String> bulunanUrunler = new ArrayList<>();
        for (String urun : arananKelimeler) {
            driver.get("https://www.testotomasyonu.com/");
            WebElement aramaKutusu = driver.findElement(By.id("global-search"));
            aramaKutusu.sendKeys(urun + Keys.ENTER);

            WebElement sonucYazisi = driver.findElement(By.className("product-count-text"));
            String actualSonucYazisi = sonucYazisi.getText();
            String urunSayisiStr = actualSonucYazisi.split(" ")[0].trim();
            try {
                int urunSayisi = Integer.parseInt(urunSayisiStr);
                if (urunSayisi > 0) {
                    bulunanUrunler.add(urun + ": " + urunSayisi + " ürün bulundu");
                } else {
                    bulunanUrunler.add(urun + ": ürün bulunamadı!");
                }
            } catch (NumberFormatException e) {
                bulunanUrunler.add(urun + ": ürün sayısı alınamadı! Gelen metin: " + actualSonucYazisi);
            }
        }
        return bulunanUrunler;
    }
}