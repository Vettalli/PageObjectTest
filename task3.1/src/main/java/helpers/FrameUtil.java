package helpers;

import browser.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameUtil {
    private static WebDriver driver = Browser.getDriver();

    public static void switchToFrame(WebElement element){
        Logger.info("Switching to another frame");

        driver.switchTo().frame(element);
    }

    public static void switchToChildElement(Integer index){
        Logger.info("Switching to the child element");

        driver.switchTo().frame(index);
    }
}
