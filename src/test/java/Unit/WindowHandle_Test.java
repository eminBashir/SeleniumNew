package Unit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.time.Duration;
import java.util.Set;

public class WindowHandle_Test extends TestBase {

    @Test
    public void windowHandle() {

        //Istifadechi "https://demoqa.com/browser-windows" sehifesine geder.
        driver.get("https://demoqa.com/browser-windows");
        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds(5)));

        String windowHandle1 = driver.getWindowHandle();
        TestBase.wait(2);

        //istifadechi NewTab butonuna basar ve yeni Tab-a kecid eder
        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();

        Set<String> windowhandles = driver.getWindowHandles();

        String windowHandle2 = "";
        for (String each : windowhandles) {
            if (!each.equals(windowHandle1)) {
                driver.switchTo().window(each); //2ci pencereye kecir
                windowHandle2 = driver.getWindowHandle(); //2ci pencereni handle edir
                System.out.println("windowHandle2 = " + windowHandle2);
            }
        }
        TestBase.wait(2);

        //istifadechi "This is a sample page" yazisinin ekranda gorunduyunu tesdiq etmelidi
        String expectedText = "This is a sample page";
        String actualText = driver.findElement(By.id("sampleHeading")).getText();
        assertEquals(expectedText, actualText);

        // istifadechi yeniden birinci pencereye qayidar
        driver.switchTo().window(windowHandle1);

        //istifadechi NewWindow butonuna basar yeni pencereye kecid eder ve windowHandle deyerini yazdirir
        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        newWindowButton.click();

        windowhandles = driver.getWindowHandles();

        String windowHandle3 = "";
        for (String each : windowhandles) {
            if (!each.equals(windowHandle1) || !each.equals(windowHandle2)) {
                driver.switchTo().window(each); //3ci pencereye kecir
                windowHandle3 = driver.getWindowHandle(); //3ci pencereni handle edir
                System.out.println("windowHandle3 =" + windowHandle3);
            }
        }
        wait(2);

        //istifadechi yeniden birinci pencereye qayidar
        driver.switchTo().window(windowHandle1);

        //istifadechi "New window message" buttonuna klikleyer
        WebElement newWindowMessageButton = driver.findElement(By.id("messageWindowButton"));
        newWindowMessageButton.click();

        //istifadechi yeni acilan pencereye kecer ve windowHandle deyerini yazdirir
        windowhandles = driver.getWindowHandles();

        String windowHandle4 = "";
        for (String each : windowhandles) {
            if (!each.equals(windowHandle1) || !each.equals(windowHandle2) || !each.equals(windowHandle3)) {
                driver.switchTo().window(each); //4ci pencereye kecir
                windowHandle4 = driver.getWindowHandle(); //4ci pencereni handle edir
                System.out.println("windowHandle4 =" + windowHandle4);
            }
        }
        wait(2);

        //istifadeci yeniden esas main page geder
        driver.switchTo().window(windowHandle1);

        String expectedUrl = "https://demoqa.com/browser-windows";
        String actualUrl = driver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void example() {
        //Istifadechi "https://demoqa.com/browser-windows" sehifesine geder.
        driver.get("https://demoqa.com/browser-windows");
        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds(5)));

        String windowHandle1 = driver.getWindowHandle();
        TestBase.wait(2);

        //istifadechi NewTab butonuna basar ve yeni Tab-a kecid eder
        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        newWindowButton.click();

        switchToWindow(1);
        TestBase.wait(2);
        String windowHandle2 = driver.getWindowHandle();

        assertFalse(windowHandle1.equals(windowHandle2));
    }
}
