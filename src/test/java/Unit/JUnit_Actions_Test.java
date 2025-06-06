package Unit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import javax.swing.*;
import java.time.Duration;

import static org.junit.Assert.*;

public class JUnit_Actions_Test extends TestBase {


    @Test
    public void action() {

        // Actions action = new Actions(driver);
        //Istifadechi "https://migros.com.tr/" saytina daxil olmalidir
        driver.get("https://migros.com.tr/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //istifadeci "cookie"-leri qebul etmelidir
        WebElement cookies = driver.findElement(By.id("accept-all"));
        cookies.click();


        //istifadeci ekranda gorsenen bildiris panelin baglamalidir
        WebElement panel = driver.findElement(By.xpath("(//fa-icon[@class='ng-fa-icon'])[1]"));
        panel.click();
        wait(2);

        //istifadeci yeni acilan "Sifarish izlenmesi" penceresini baglamalidi
        WebElement sifarisIzle = driver.findElement(By.cssSelector("fa-icon[class='ng-fa-icon text-color-grey close-button']"));
        if (sifarisIzle.isDisplayed()) {
            sifarisIzle.click();
            wait(2);
        }

        //istifadeci sehifenin en alt hissesinde "Migros" logosunu gormelidir
        WebElement logo = driver.findElement(By.cssSelector("img[alt='migros-logo']"));
        action.scrollToElement(logo).perform();
        assertTrue(logo.isDisplayed());
        wait(2);

        //istifadeci "SearchBox"-u gore bilmelidir
        WebElement searchBox = driver.findElement(By.id("product-search-combobox--trigger"));
        scrollToElementWithActions(searchBox, driver);
        assertTrue(searchBox.isDisplayed());
        wait(2);

        //istifadeci "SerachBox"-a klik etmelidir
        searchBox.click();

        action.keyDown(searchBox, Keys.SHIFT)
                .sendKeys("Dana Sucukk")
                .sendKeys(Keys.BACK_SPACE)
                .keyUp(Keys.SHIFT)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        wait(2);

        //istifadeci axtardigi mehsulun duzgunluyunu dogrulamalidi
        WebElement product = driver.findElement(By.xpath("(//a[@id='product-name'])[1]"));

        String expectedText1 = "Dana";
        String expectedText2 = "Sucuk";
        String actualText = product.getText();

        assertTrue(actualText.contains(expectedText1) && actualText.contains(expectedText2));

    }

    @Test
    public void contextClick() {

        //istifadeci "https://the-internet.herokuapp.com/context_menu" sehifesine daxil olmalidir
        driver.get("https://the-internet.herokuapp.com/context_menu");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        action = new Actions(driver);

        //istifadeci sehifedeki qutuya sag klik etmelidir
        WebElement box = driver.findElement(By.id("hot-spot"));
        action.contextClick(box).perform();
        wait(2);

        //istifadeci acilan alert penceresini baglamalidi
        driver.switchTo().alert().accept();

    }

    @Test
    public void moveToElement() {

        action = new Actions(driver);

        //istifadeci "https://migros.com.tr/" saytina daxil olmalidir.
        driver.get("https://migros.com.tr/");

        //istifadeci saytdaki "Kategoriyalar" bolmesinin ustunde gozlemelidi
        WebElement categories = driver.findElement(By.cssSelector("div[class='categories-icon']"));
        action.moveToElement(categories).perform();
        wait(2);
    }

    @Test
    public void dragDrop() {
        action = new Actions(driver);
        //istifadeci "https://jqueryui.com/droppable/" saytina daxil olmalidir
        driver.get("https://jqueryui.com/droppable/");

        //istifadeci drag-i dropp-un uzerine aparmalidi
        driver.switchTo().frame(0); //frame aliriq

        //frame almagin diger yolu
//        WebElement frame = driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
//        driver.switchTo().frame(frame);

        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));

        action.dragAndDrop(drag, drop).perform();
    }
}
