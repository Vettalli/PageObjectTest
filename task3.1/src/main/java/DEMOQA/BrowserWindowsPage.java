package DEMOQA;

import core.BaseForm;
import elements.Button;
import elements.TextBox;
import org.openqa.selenium.By;

public class BrowserWindowsPage extends BaseForm {
    private Button newTabButton = new Button(By.xpath("//button[@id = 'tabButton']"), "New tab button");
    private TextBox textSampleTextBox = new TextBox(By.xpath("//*[@id = 'sampleHeading']"), "Sample page");
    private Button elementsButton = new Button(By.xpath("(//div[@class = 'header-wrapper'])[1]"), "Elements button");
    private Button linksButton = new Button(By.xpath("//*[@id='item-5']/span[text()[contains(.,'Links')]]"), "Links button");

    public BrowserWindowsPage() {
            super(By.xpath("//div[@class = 'main-header' and contains(text(), 'Browser Windows')]"), "Browser Windows Page");
    }

    public void openNewTab(){
        newTabButton.click();
    }

    public boolean isNewTabOpened(){
        return textSampleTextBox.isElementDisplayed();
    }

    public void openElements(){
        elementsButton.click();
    }

    public void goToLinksTab(){
        linksButton.click();
    }
}
