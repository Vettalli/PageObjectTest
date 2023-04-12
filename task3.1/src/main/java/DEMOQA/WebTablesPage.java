package DEMOQA;

import browser.BrowserUtil;
import core.BaseForm;
import elements.Button;
import elements.TextBox;
import helpers.StringUtil;
import helpers.TableUtil;
import models.UserData;
import org.openqa.selenium.By;
import readJSON.JSONProvider;

public class WebTablesPage extends BaseForm {
    private String webTablesXpath = "//span[contains(text(), 'Web Tables')]";
    private String addButtonXpath = "//button[@id = 'addNewRecordButton']";
    private String userDataFromTableXpath = "//*[text() = '%s']";
    private String deleteRecordXpath = "//span[@id = 'delete-record-4']";
    private String rowsXpath = "//div[@class = 'rt-tr-group']";
    private String cellXpath = "(//div[@class = 'rt-td'])[%d]";

    public WebTablesPage() {
        super(By.xpath("//div[@class = 'main-header'  and contains(text(), 'Web Tables')]"), "Elements label");
    }

    public void goToWebTablesTab(){
        var alertsButton = new Button(By.xpath(webTablesXpath), "Alerts button");

        alertsButton.click();
    }

    public void openRegistrationForm(){
        var addButton = new Button(By.xpath(addButtonXpath), "add button");
        addButton.click();
    }

    public boolean isRegistrationFormClosed(){
        var addButton = new Button(By.xpath(addButtonXpath), "add button");
        return addButton.isElementDisplayed();
    }

    public UserData getUserDataFromTable(String id, String firstName, String lastName, String email, String age, String salary, String department){
        var firstNameEl = new TextBox(By.xpath(StringUtil.getXpathWithStringParam(userDataFromTableXpath, firstName)), "first name");
        var firstNameCurrent = firstNameEl.getText();

        var lastNameEl = new TextBox(By.xpath(StringUtil.getXpathWithStringParam(userDataFromTableXpath, lastName)), "last name");
        var lastNameCurrent = lastNameEl.getText();

        var emailEl = new TextBox(By.xpath(StringUtil.getXpathWithStringParam(userDataFromTableXpath, email)), "email");
        var emailCurrent = emailEl.getText();

        var ageEl = new TextBox(By.xpath(StringUtil.getXpathWithStringParam(userDataFromTableXpath, age)), "age");
        var ageCurrent = ageEl.getText();

        var salaryEl = new TextBox(By.xpath(StringUtil.getXpathWithStringParam(userDataFromTableXpath, salary)), "salary");
        var salaryCurrent = salaryEl.getText();

        var departmentEl = new TextBox(By.xpath(StringUtil.getXpathWithStringParam(userDataFromTableXpath, department)), "department");
        var departmentCurrent = departmentEl.getText();

        return new UserData(id, firstNameCurrent, lastNameCurrent, emailCurrent, ageCurrent, salaryCurrent, departmentCurrent);
    }

    public void deleteUser(){
        var button = new Button(By.xpath(deleteRecordXpath), "Delete button");
        button.click();
    }

    public Integer getNumberOfRows(){
        return TableUtil.countNumberOfFilledRows(rowsXpath, cellXpath, Integer.parseInt(JSONProvider.getProperty(JSONProvider.getJSON("data"), "indexDifference")));
    }
}
