package helpers;

import browser.Browser;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertUtil {
    private static WebDriver driver = Browser.getDriver();

    public static void acceptAlert(){
        driver.switchTo().alert().accept();

        Logger.info("Alert has been accepted");
    }

    public static String getTextAlert(){
        Logger.info("Getting alert's text");

        return driver.switchTo().alert().getText();
    }

    public static boolean isAlertExist(){
        Logger.info("Checking if alert exist");

        try
        {
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException Ex)
        {
            Logger.info("Alert doesn't exist!");

            return false;
        }
    }

    public static String sendRandomTextToAlert(){
        Logger.info("Sending random text to alert");

        var text = StringUtil.generateRandomString();
        driver.switchTo().alert().sendKeys(text);

        return text;
    }
}
