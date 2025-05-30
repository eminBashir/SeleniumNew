package utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Screenshot {

    public static void takeScreenshot(WebDriver driver,String screenShotName){
        byte[] screenshotBytes = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(screenShotName,new ByteArrayInputStream(screenshotBytes));
    }
}
