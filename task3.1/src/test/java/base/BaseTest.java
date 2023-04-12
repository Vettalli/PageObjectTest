package base;

import browser.Browser;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import readJSON.JSONProvider;

public class BaseTest {

    @BeforeTest
    public void setUp() {
        BasicConfigurator.configure();
        Browser.getDriver();
        Browser.setMaximumWindowSize();
        Browser.goToUrl(JSONProvider.getProperty(JSONProvider.getJSON("config"), "url"));
    }

    @AfterTest
    public void tearDown(){
        Browser.stopBrowserDriver();
    }
}