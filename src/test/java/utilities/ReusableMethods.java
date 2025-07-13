package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReusableMethods {

    public static void bekle(int saniye){

        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }

    public static List<String> getStringList(List<WebElement> kaynakList){

        List<String> stringList = new ArrayList<>();

        for ( WebElement eachElement : kaynakList
        ) {

            stringList.add(eachElement.getText());

        }


        return stringList;
    }

    public static void switchWindowByUrl (WebDriver driver, String hedefUrl){

        Set<String> acikWindowlarinWhdSeti = driver.getWindowHandles();
        for (String eachWhd : acikWindowlarinWhdSeti
        ) {
            // once bizim oglanin getirdigi whd ile bir window'a gecis yapalim
            driver.switchTo().window(eachWhd);
            String gecilenSayfaUrl = driver.getCurrentUrl();

            if (gecilenSayfaUrl.equals(hedefUrl)){
                break;
            }
        }
    }

    public static void switchWindowByTitle(WebDriver driver, String hedefTitle){
        Set<String> acikWindowlarinWhdSeti = driver.getWindowHandles();
        for (String eachWhd : acikWindowlarinWhdSeti
        ) {
            // once bizim oglanin getirdigi whd ile bir window'a gecis yapalim
            driver.switchTo().window(eachWhd);
            String gecilenSayfaTitle = driver.getTitle();

            if (gecilenSayfaTitle.equals(hedefTitle)){
                break;
            }
        }
    }

    public static void getFullScreenshot(WebDriver driver, String screenshotIsmi){
        // 1.adim screenshot objesi olusturmak ve deger olarak driver'imizi atamak
        TakesScreenshot tss = (TakesScreenshot) driver;

        // 2.adim screenshot'i kaydedecegimiz File'i olusturun
        File tumSayfaSS = new File("target/ekranGoruntuleri/"+screenshotIsmi+".png");

        // 3.adim screenshot'i alip gecici bir dosyaya kopyalayalim
        File geciciDosya = tss.getScreenshotAs(OutputType.FILE);

        // 4.adim gecici dosyayi, asil kaydetmek istedigimiz dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya,tumSayfaSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getFullScreenshot(WebDriver driver){
        // dosya isimlerine tarih etiketi ekleyelim
        // ... 240829114023 gibi bir etiket eklemek dosya ismini benzersiz yapar

        LocalDateTime zaman = LocalDateTime.now(); // 2024.08.29T11:42:23:123456
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String tarihEtiketi = zaman.format(dateTimeFormatter); // 240829114023

        // 1.adim screenshot objesi olusturmak ve deger olarak driver'imizi atamak
        TakesScreenshot tss = (TakesScreenshot) driver;

        // 2.adim screenshot'i kaydedecegimiz File'i olusturun
        File tumSayfaSS = new File("target/ekranGoruntuleri/TumSayfaSS_"+tarihEtiketi+".png");

        // 3.adim screenshot'i alip gecici bir dosyaya kopyalayalim
        File geciciDosya = tss.getScreenshotAs(OutputType.FILE);

        // 4.adim gecici dosyayi, asil kaydetmek istedigimiz dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya,tumSayfaSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getWebelementScreenshot(WebElement istenenWebelement){

        // tarih etiketi olusturalim
        LocalDateTime zaman = LocalDateTime.now(); // 2024.08.29T11:42:23:123456
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String tarihEtiketi = zaman.format(dateTimeFormatter); // 240829114023


        // 1.adim kullanacagimiz WebElementi locate edip kaydedin

        // 2.adim kaydedeceginiz dosyayi olusturun
        File webelementSS = new File("target/ekranGoruntuleri/webelementSS_"+tarihEtiketi+".png");

        // 3.adim webelementi kullanarak screenshot'i alip gecici dosyaya kaydedin
        File geciciDosya = istenenWebelement.getScreenshotAs(OutputType.FILE);

        // 4.adim gecici dosyayi asil dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya,webelementSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getWebelementScreenshot(WebElement istenenWebelement,String resimIsmi){

        // tarih etiketi olusturalim
        LocalDateTime zaman = LocalDateTime.now(); // 2024.08.29T11:42:23:123456
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String tarihEtiketi = zaman.format(dateTimeFormatter); // 240829114023


        // 1.adim kullanacagimiz WebElementi locate edip kaydedin

        // 2.adim kaydedeceginiz dosyayi olusturun
        File webelementSS = new File("target/ekranGoruntuleri/"+resimIsmi+"_"+tarihEtiketi+".png");

        // 3.adim webelementi kullanarak screenshot'i alip gecici dosyaya kaydedin
        File geciciDosya = istenenWebelement.getScreenshotAs(OutputType.FILE);

        // 4.adim gecici dosyayi asil dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya,webelementSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
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