package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

public abstract class TestBase {

    protected static WebDriver driver;
    protected static Actions action;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @After
    public void tearDown() {
        wait(2);
        //driver.quit();
    }

    public static void wait(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void switchToWindow(int window) {
        List<String> allWindowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(allWindowHandles.get(window));

    }

    public static void scrollToElementWithActions(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).perform();
    }
}
