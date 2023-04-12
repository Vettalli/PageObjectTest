package DEMOQA;

import browser.BrowserUtil;
import core.BaseForm;
import elements.Button;
import elements.TextBox;
import org.openqa.selenium.By;

public class AlertsFrameWindowsPage extends BaseForm {
    private Button browserWindowsButton = new Button(By.xpath("//span[contains(text(), 'Browser Windows')]"), "Browser windows button");
    private Button alertsButton = new Button (By.xpath("//span[contains(text(), 'Alerts')]"),"Alerts button");
    private Button alertButton = new Button(By.xpath("//button[@id = 'alertButton']"), "Alert Button");
    private Button confirmButton = new Button(By.xpath("//button[@id = 'confirmButton']"), "Confirm Button");
    private Button promptButton = new Button(By.xpath("//button[@id = 'promtButton']"), "Prompt Button");
    private TextBox promptResultsTextBox = new TextBox(By.xpath("//span[@id = 'promptResult']"), "Confirm result text");
    private TextBox confirmResultTextBox = new TextBox(By.xpath("//span[@id = 'confirmResult']"), "Confirm result text");

    public AlertsFrameWindowsPage() {
        super(By.xpath("//div[@class = 'main-header'  and contains(text(), 'Alerts, Frame & Windows')]"), "Alerts, Frame & Windows Page");
    }

    public void goToBrowserWindowsTab(){
        browserWindowsButton.click();
    }

    public void goToAlertTab(){
        alertsButton.click();
    }

    public void clickToAlertButton(){
        alertButton.click();
    }

    public void clickToConfirmButton(){
        confirmButton.click();
    }

    public void clickToPromptButton(){
        promptButton.click();
    }

    public String getText(){
        return promptResultsTextBox.getText();
    }

    public boolean checkText(){
       return confirmResultTextBox.isElementDisplayed();
    }
}
