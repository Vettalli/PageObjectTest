package DEMOQA;

import core.BaseForm;
import elements.Button;
import elements.Input;
import org.openqa.selenium.By;

public class RegistrationFormPage extends BaseForm {
    private Input firstNameInput = new Input(By.xpath("//input[@id = 'firstName']"), "First name");
    private Input lastNameInput = new Input(By.xpath("//input[@id = 'lastName']"), "Last name");
    private Input emailInput = new Input(By.xpath("//input[@id = 'userEmail']"), "Email");
    private Input ageInput = new Input(By.xpath("//input[@id = 'age']"), "Age");
    private Input salaryInput = new Input(By.xpath("//input[@id = 'salary']"), "Salary");
    private Input departmentInput = new Input(By.xpath("//input[@id = 'department']"), "Department");
    private Button submitButton = new Button(By.xpath("//button[@id = 'submit']"), "Submit");

    public RegistrationFormPage() {
        super(By.xpath("//div[@id = 'registration-form-modal']"), "Registration form page");
    }

    public void fillFirstNameField(String firstName){
        firstNameInput.sendKey(firstName);
    }

    public void fillLastNameField(String lastName){
        lastNameInput.sendKey(lastName);
    }

    public void fillEmailField(String email){
        emailInput.sendKey(email);
    }

    public void fillAgeField(String age){
        ageInput.sendKey(age);
    }

    public void fillSalaryField(String salary){
        salaryInput.sendKey(salary);
    }

    public void fillDepartmentField(String department){
        departmentInput.sendKey(department);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }
}
