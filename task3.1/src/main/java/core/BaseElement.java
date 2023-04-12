package core;

import browser.Browser;
import helpers.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.ConditionalWait;

import java.util.List;

public abstract class BaseElement {
    private By uniqueLocator;
    private String elementName;
    private WebDriver driver;
    private WebDriverWait wait;

    protected BaseElement(By locator, String name){
        uniqueLocator = locator;
        elementName = name;
        driver = Browser.getDriver();
        wait = ConditionalWait.getWaits();
    }

    public WebElement getElement() {
        Logger.info("Getting element: "+elementName);

        return driver.findElement(uniqueLocator);
    }

    public List<WebElement> getElements() {
        Logger.info("Getting elements: "+elementName);

        return driver.findElements(uniqueLocator);
    }

    public void click(){
        ConditionalWait.waitForToBeClickable(uniqueLocator);
        ConditionalWait.waitForElementPresented(uniqueLocator);

        Logger.info("Clicking on element: "+elementName);

        getElement().click();
    }

    public boolean isElementDisplayed(){
        Logger.info("Checking if element "+elementName+" displayed on that page");

        return getElement().isDisplayed();
    }

    public String getText(){
        Logger.info("Getting "+elementName+"'s text");

        return getElement().getText();
    }
}
