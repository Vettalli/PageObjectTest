package DEMOQA;

import browser.BrowserUtil;
import core.BaseForm;
import elements.Button;
import elements.TextBox;
import helpers.FrameUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class IframePage extends BaseForm {
    private Button alertsButton = new Button(By.xpath("//span[text()[contains(.,'Nested Frames')]]"), "Nested frames button");
    private Button framesButton = new Button(By.xpath("//*[@id='item-2']/span[text()[contains(.,'Frames')]]"), "Frames button");
    private TextBox messageTextBox = new TextBox(By.id("sampleHeading"), "");
    private TextBox parentMessageTextBox = new TextBox(By.id("frame1"), "iframe1");
    private TextBox littleMessageTextBox = new TextBox(By.id("frame2"), "iframe1");
    private TextBox elementInsideTheFrame = new TextBox(By.tagName("body"), "Element inside the first frame");
    private Integer childFrameIndex = 0;

    public IframePage() {
        super(By.xpath("//div[@class = 'main-header' and contains(text(), 'Frames')]"), "Frames");
    }

    public void goToNestedFramesTab(){
        alertsButton.click();
    }

    public void goToFramesTab(){
        framesButton.click();
    }

    public String getMessage(Integer frameIndex) {
        var textBox = new TextBox(By.xpath(""), "");

        if(frameIndex == 1){
            textBox = parentMessageTextBox;
        }
        else if(frameIndex == 2){
            textBox = littleMessageTextBox;
        }

        var element = textBox.getElement();

        FrameUtil.switchToFrame(element);

        var frameHeadings = messageTextBox.getElement();
        var text = frameHeadings.getText();

        return text;
    }

    public String getParentMessage(){
        var parentFrame = parentMessageTextBox.getElement();

        FrameUtil.switchToFrame(parentFrame);

        WebElement parentFrameElement = elementInsideTheFrame.getElement();

        return parentFrameElement.getText();
    }



    public String getChildMessage(){
        FrameUtil.switchToChildElement(childFrameIndex);

        WebElement childrenFrameElement = elementInsideTheFrame.getElement();

        return childrenFrameElement.getText();
    }
}
