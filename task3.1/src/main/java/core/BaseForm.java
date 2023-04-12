package core;

import helpers.Logger;
import org.openqa.selenium.By;
import waits.ConditionalWait;

public abstract class BaseForm {
    private By uniqueLocator;
    private String formName;

    public BaseForm(By uniqueLocator, String formName) {
        this.uniqueLocator = uniqueLocator;
        this.formName = formName;
        ConditionalWait.getWaits();
    }

    public boolean isFormOpen(){
        Logger.info("Checking if the form is open");

        return ConditionalWait.waitForElementPresented(uniqueLocator).isDisplayed();
    }
}
