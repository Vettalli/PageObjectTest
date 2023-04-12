package waits;

import browser.Browser;
import helpers.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import readJSON.JSONProvider;

import java.time.Duration;

public class ConditionalWait {
    private static WebDriverWait wait;
    private final static Integer timeoutInSeconds = Integer
            .parseInt(JSONProvider.getProperty(JSONProvider.getJSON("config"), "timeoutInSeconds"));

    public static WebDriverWait getWaits(){
        if(wait == null){
            wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(timeoutInSeconds));

            Logger.info("Wait has been set");
        }

        return wait;
    }

    public static WebElement waitForToBeClickable(By locator){
        Logger.info("Waiting until element will be clickable, locator: "+locator);

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementPresented(By locator){
        Logger.info("Waiting until element will be presented, locator: "+locator);

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
