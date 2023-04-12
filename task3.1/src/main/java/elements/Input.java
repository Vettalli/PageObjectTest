package elements;

import core.BaseElement;
import helpers.Logger;
import org.openqa.selenium.By;

public class Input extends BaseElement {
    public Input(By locator, String name) {
        super(locator, name);
    }

    public void sendKey(String key){
        Logger.info("Sending key: "+key);

        getElement().click();
        getElement().sendKeys(key);
    }
}
