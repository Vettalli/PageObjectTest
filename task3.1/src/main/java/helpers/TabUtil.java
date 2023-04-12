package helpers;

import browser.Browser;
import org.openqa.selenium.WebDriver;

public class TabUtil {
    private static WebDriver driver = Browser.getDriver();

    public static void switchToChildTab(){
        String mainWindow = driver.getWindowHandle();
        String childWindow = "";

        var windows = driver.getWindowHandles();

        for (var window : windows) {
            if(window != mainWindow){
                childWindow = window;
            }
        }

        Logger.info("Switching to child window");

        driver.switchTo().window(childWindow);
    }

    public static void switchToParentTab(){
        String mainWindow = driver.getWindowHandle();

        var windows = driver.getWindowHandles();

        for (var window : windows) {
            if(window != mainWindow){
                mainWindow = window;
                driver.close();

                break;
            }
        }

        Logger.info("Switching to main window");

        driver.switchTo().window(mainWindow);
    }
}
