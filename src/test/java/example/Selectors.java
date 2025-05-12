package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selectors {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String originalWindow = driver.getWindowHandle();
        driver.manage().window().maximize();
        driver.get("https://mackolik.com");
        String searchByClass = "reject-all-btn";
        WebElement searchClass = driver.findElement(By.className(searchByClass));
        searchClass.click();
        Thread.sleep(2000);
        String searchBoxXpath = "//input[@placeholder='Arama']";

        WebElement serachBoxElement = driver.findElement(By.xpath(searchBoxXpath));


        serachBoxElement.sendKeys("Galatasaray", Keys.ENTER);
        String searchByCssSelector = "a[href='javascript:popTeam(1)']";

        WebElement searchElement = driver.findElement(By.cssSelector(searchByCssSelector));
        Thread.sleep(2000);
        searchElement.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        //String kadro = "//a[.='Kadro']";

        WebElement kadroElement = driver.findElement(By.xpath("//a[.='Kadro']"));
        kadroElement.click();


    }
}
