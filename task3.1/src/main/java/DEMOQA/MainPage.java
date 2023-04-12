package DEMOQA;

import core.BaseForm;
import elements.Button;
import org.openqa.selenium.By;

public class MainPage extends BaseForm {
    private Button alertsFrameWindowsButton = new Button(By.xpath("//div[contains(@class, 'card')]//*[contains(text(), 'Alerts, Frame & Windows')]"), "Alerts, Frame & Windows button");
    private Button elementsButton = new Button(By.xpath("//div[contains(@class, 'card')]//*[contains(text(), 'Elements')]"), "Elements button");

    public MainPage() {
        super(By.xpath("//img[@class = 'banner-image']"), "Main page");
    }

    public void goToAlertsFrameWindowsPage(){
        alertsFrameWindowsButton.click();
    }

    public void goToElementsPage(){
        elementsButton.click();
    }
}
