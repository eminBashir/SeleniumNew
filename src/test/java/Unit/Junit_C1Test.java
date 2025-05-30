package Unit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;
import utilities.Screenshot;

import java.time.Duration;

@ExtendWith(AllureJunit5.class)
public class Junit_C1Test {
    String url = "https://the-internet.herokuapp.com/";
    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description
    @Severity(SeverityLevel.NORMAL)
    @Epic("Heroku app")
    @Feature("Checkbox")
    @Story("The user must be able to click the checkbox")
    public void unitTest() throws InterruptedException {
        driver.get(url);
        WebElement checkBoxLink = driver.findElement(By.linkText("Checkboxes"));
        checkBoxLink.click();

        Screenshot.takeScreenshot(driver,"Checkbox screen");

        Thread.sleep(2000);
        WebElement checkBox1 = driver.findElement(By.xpath("//input[@type='checkbox'][1]"));
        Assert.assertFalse(checkBox1.isSelected());
        checkBox1.click();

        Screenshot.takeScreenshot(driver,"Checkbox2 screen");


        driver.navigate().back();
        String pageSource = driver.getPageSource();
        //System.out.println("pageSource = " + pageSource);
        Thread.sleep(1000);

    }
}
