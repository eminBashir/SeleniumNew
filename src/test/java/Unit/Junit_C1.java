package Unit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Junit_C1 {
    String url = "https://the-internet.herokuapp.com/";
    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void unitTests() throws InterruptedException {
        driver.get(url);
        WebElement checkBoxLink = driver.findElement(By.linkText("Checkboxes"));
        checkBoxLink.click();
        Thread.sleep(2000);
        WebElement checkBox1 = driver.findElement(By.xpath("//input[@type='checkbox'][1]"));
        Assert.assertFalse(checkBox1.isSelected());
        checkBox1.click();
        driver.navigate().back();
        String pageSource = driver.getPageSource();
        //System.out.println("pageSource = " + pageSource);
        Thread.sleep(1000);
        System.out.println("Hello world");
    }
}
