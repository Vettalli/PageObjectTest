package browser;

import helpers.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import readJSON.JSONProvider;

public class BrowserUtil {
    private static WebDriver driver;

    public static WebDriver setUpDriver(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (JSONProvider.getProperty(JSONProvider.getJSON("config"), "browserDriver")){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                var chromeOptions = JSONProvider.getProperties(JSONProvider.getJSON("config"), "chromeOptions");

                Logger.info("Properties have been set");

                for (var option : chromeOptions) {
                    options.addArguments(option);
                }

                Logger.info("Arguments have been added");

                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                options.merge(capabilities);

                WebDriverManager.chromedriver().setup();

                Logger.info("Chrome driver has been set");

                driver = new ChromeDriver(options);

                return driver;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                var firefoxOptionsArray = JSONProvider.getProperties(JSONProvider.getJSON("config"), "chromeOptions");

                for (var option : firefoxOptionsArray) {
                    firefoxOptions.addArguments(option);
                }

                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                WebDriverManager.firefoxdriver().setup();

                Logger.info("Firefox driver has been set");

                driver = new FirefoxDriver(firefoxOptions);

                return driver;

            default:
                throw new RuntimeException("Incorrect browser name");
        }
    }

    public static void refreshPage(){
        Logger.info("Refresh page");

        driver.navigate().refresh();
    }

    public static void setMaximumWindowSize(WebDriver driver){
        Logger.info("Setting max window size");

        driver.manage().window().maximize();
    }

    public static String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public static void scrollPage(){
        Logger.info("Scrolling page");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }
}
