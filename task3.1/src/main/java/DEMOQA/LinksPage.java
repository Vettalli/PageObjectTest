package DEMOQA;

import core.BaseForm;
import elements.Button;
import org.openqa.selenium.By;

public class LinksPage extends BaseForm {
    private Button homeButton = new Button(By.xpath("//a[@id = 'simpleLink']"), "Home button");

    public LinksPage() {
        super(By.xpath("//div[@class = 'main-header'  and contains(text(), 'Links')]"), "Links page");
    }

    public void goToHomePage(){
        homeButton.click();
    }
}
