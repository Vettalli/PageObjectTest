package DEMOQATests;

import DEMOQA.*;
import base.BaseTest;
import browser.BrowserUtil;
import helpers.AlertUtil;
import helpers.Logger;
import helpers.TabUtil;
import models.UserData;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import readJSON.JSONProvider;

import java.util.Iterator;

public class DEMOQATests extends BaseTest {
    @Test
    public void alertsTest(){
        Logger.startTestCase("Alert test");

        MainPage mainPage = new MainPage();
        AlertsFrameWindowsPage alertsPage = new AlertsFrameWindowsPage();

        var isMainPageOpen = mainPage.isFormOpen();

        Assert.assertTrue(isMainPageOpen, "DENOQA Main page is not opened");

        mainPage.goToAlertsFrameWindowsPage();

        Assert.assertTrue(alertsPage.isFormOpen(), "Alerts, Frame & Windows page is not opened");

        BrowserUtil.scrollPage();

        alertsPage.goToAlertTab();
        alertsPage.clickToAlertButton();

        var alertsText = AlertUtil.getTextAlert();

        Assert.assertEquals(alertsText, "You clicked a button");

        AlertUtil.acceptAlert();

        var isAlertActive = AlertUtil.isAlertExist();

        Assert.assertFalse(isAlertActive, "Alert window is opened");

        alertsPage.clickToConfirmButton();

        var alertsConfirmText = AlertUtil.getTextAlert();

        Assert.assertEquals(alertsConfirmText, "Do you confirm action?");

        AlertUtil.acceptAlert();

        Assert.assertFalse(AlertUtil.isAlertExist(), "Alert window is opened");
        Assert.assertTrue(alertsPage.checkText(), "Alert text is missing!");

        alertsPage.clickToPromptButton();

        var alertsPromptText = AlertUtil.getTextAlert();

        Assert.assertEquals(alertsPromptText, "Please enter your name");

        var text = AlertUtil.sendRandomTextToAlert();
        AlertUtil.acceptAlert();

        Assert.assertFalse(AlertUtil.isAlertExist(), "Alert window is opened");
        Assert.assertEquals(alertsPage.getText(), "You entered "+text, "Alert text is missing!");

        Logger.endTestCase("Alert test");
    }

    @Test
    public void iFrameTest(){
        Logger.startTestCase("IFrame test");

        MainPage mainPage = new MainPage();
        IframePage iframePage = new IframePage();

        var isMainPageOpen = mainPage.isFormOpen();

        Assert.assertTrue(isMainPageOpen, "DENOQA Main page is not opened");

        mainPage.goToAlertsFrameWindowsPage();

        BrowserUtil.scrollPage();

        iframePage.goToNestedFramesTab();

        var isNestedFramesPageOpen = iframePage.isFormOpen();

        Assert.assertTrue(isNestedFramesPageOpen, "Nested Frames page is not opened");

        var parentMessage = iframePage.getParentMessage();
        var childMessage = iframePage.getChildMessage();

        Assert.assertNotEquals("", parentMessage, "There are not messages presented on the page");
        Assert.assertNotEquals("", childMessage, "There are not messages presented on the page");

        BrowserUtil.refreshPage();
        BrowserUtil.scrollPage();

        iframePage.goToFramesTab();

        var isFramesPageOpen = iframePage.isFormOpen();

        Assert.assertTrue(isFramesPageOpen, "Frames page is not opened");

        var indexForFrame1 = 1;
        var indexForFrame2 = 2;

        BrowserUtil.refreshPage();

        var message1 = iframePage.getMessage(indexForFrame1);

        BrowserUtil.refreshPage();

        var message2 = iframePage.getMessage(indexForFrame2);

        Assert.assertEquals(message2, message1);

        Logger.endTestCase("IFrame test");
    }

    @Test(dataProvider = "userData")
    public void tablesTest(UserData userData){
        Logger.startTestCase("Tables test");

        UserData user = new UserData(userData.getId(), userData.getFirstName(), userData.getLastName()
                , userData.getEmail(), userData.getAge(), userData.getSalary(), userData.getDepartment());
        MainPage mainPage = new MainPage();
        WebTablesPage elementsPage = new WebTablesPage();
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        var currentUrl = BrowserUtil.getCurrentUrl();
        var baseUrl = JSONProvider.getProperty(JSONProvider.getJSON("config"), "url");

        if(currentUrl.equals(baseUrl)){
            var isMainPageOpen = mainPage.isFormOpen();

            Assert.assertTrue(isMainPageOpen, "DENOQA Main page is not opened");

            mainPage.goToElementsPage();

            BrowserUtil.scrollPage();

            elementsPage.goToWebTablesTab();
        }

        var isWebTablesPageOpen = elementsPage.isFormOpen();

        Assert.assertTrue(isWebTablesPageOpen, "Web tables page is not opened");

        BrowserUtil.scrollPage();

        elementsPage.goToWebTablesTab();
        elementsPage.openRegistrationForm();

        var isRegistrationFormOpen = registrationFormPage.isFormOpen();

        Assert.assertTrue(isRegistrationFormOpen, "Registration page is not opened");

        registrationFormPage.fillFirstNameField(user.getFirstName());
        registrationFormPage.fillLastNameField(user.getLastName());
        registrationFormPage.fillEmailField(user.getEmail());
        registrationFormPage.fillAgeField(user.getAge());
        registrationFormPage.fillSalaryField(user.getSalary());
        registrationFormPage.fillDepartmentField(user.getDepartment());
        registrationFormPage.clickSubmitButton();


        var isRegistrationFormClosed = elementsPage.isRegistrationFormClosed();

        Assert.assertTrue(isRegistrationFormClosed, "Registration page is still opened");

        var userCurrent = elementsPage.getUserDataFromTable(userData.getId(), userData.getFirstName(), userData.getLastName()
                , userData.getEmail(), userData.getAge(), userData.getSalary(), userData.getDepartment());
        user = new UserData(userData.getId(), userData.getFirstName(), userData.getLastName()
                , userData.getEmail(), userData.getAge(), userData.getSalary(), userData.getDepartment());

        Assert.assertTrue(EqualsBuilder.reflectionEquals(user,userCurrent), "Wrong user's data!");

        var amountOfUsersBeforeDelete = elementsPage.getNumberOfRows();

        elementsPage.deleteUser();

        var amountOfUsersAfterDelete = elementsPage.getNumberOfRows();

        Assert.assertNotEquals(amountOfUsersAfterDelete, amountOfUsersBeforeDelete, "User is still in the list!");

        boolean isUser = true;

        try {
            elementsPage.getUserDataFromTable(userData.getId(), userData.getFirstName(), userData.getLastName()
                    , userData.getEmail(), userData.getAge(), userData.getSalary(), userData.getDepartment());
        }
        catch (NoSuchElementException ex){
            Logger.info("There is no such a user in the table!");
            isUser = false;
        }

        Assert.assertFalse(isUser, "User has not been deleted!");

        Logger.endTestCase("Tables test");
    }

    @DataProvider(name = "userData")
    public Iterator<Object[]> testData(){
        return JSONProvider.getUserData();
    }

    @Test
    public void handlesTest(){
        Logger.startTestCase("Handles test");

        MainPage mainPage = new MainPage();
        AlertsFrameWindowsPage alertsFrameWindowsPage = new AlertsFrameWindowsPage();
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage();
        LinksPage linksPage = new LinksPage();

        var isMainPageOpen = mainPage.isFormOpen();

        Assert.assertTrue(isMainPageOpen, "DENOQA Main page is not opened");

        mainPage.goToAlertsFrameWindowsPage();

        BrowserUtil.scrollPage();

        alertsFrameWindowsPage.goToBrowserWindowsTab();

        var isBrowserWindowsPage = browserWindowsPage.isFormOpen();

        Assert.assertTrue(isBrowserWindowsPage, "Browser Windows page is not opened");

        browserWindowsPage.openNewTab();

        TabUtil.switchToChildTab();

        var isNewTab = browserWindowsPage.isNewTabOpened();

        Assert.assertTrue(isNewTab, "New tab page is not opened");

        TabUtil.switchToParentTab();

        isBrowserWindowsPage = browserWindowsPage.isFormOpen();

        Assert.assertTrue(isBrowserWindowsPage, "Browser Windows page is not opened");

        browserWindowsPage.openElements();
        browserWindowsPage.goToLinksTab();

        var isLinksPage = linksPage.isFormOpen();

        Assert.assertTrue(isLinksPage, "Links page is not opened");

        linksPage.goToHomePage();

        TabUtil.switchToChildTab();

        isMainPageOpen = mainPage.isFormOpen();

        Assert.assertTrue(isMainPageOpen, "DENOQA Main page is not opened");

        TabUtil.switchToParentTab();

        isLinksPage = linksPage.isFormOpen();

        Assert.assertTrue(isLinksPage, "Links page is not opened");

        Logger.endTestCase("Handles test");
    }
}
