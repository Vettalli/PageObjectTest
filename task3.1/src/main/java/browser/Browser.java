package browser;

import helpers.Logger;
import org.openqa.selenium.WebDriver;

public class Browser {
    private static WebDriver driver;

    public static WebDriver getDriver(){
        if(driver == null){
            driver = BrowserUtil.setUpDriver();
        }

        return driver;
    }

    public static void goToUrl(String url){
        Logger.info("Getting url");

        driver.get(url);
    }

    public static void stopBrowserDriver(){
        Logger.info("Stopping browser driver");

        driver.quit();
        driver = null;
    }

    public static void setMaximumWindowSize(){
        BrowserUtil.setMaximumWindowSize(driver);
    }
}
