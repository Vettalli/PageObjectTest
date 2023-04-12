package elements;

import core.BaseElement;
import org.openqa.selenium.By;

public class Button extends BaseElement {
    public Button (By locator, String name){
        super(locator, name);
    }
}
