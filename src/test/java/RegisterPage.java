/*
Bram Lemeire
Mathieu Pechovitch
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends Page {
    @FindBy(id = "userid")
    private WebElement useridField;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "signUp")
    private WebElement signUpButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Register");
    }

    public void setUserid(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public HomePage submitValid() {
        signUpButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void submitInvalid() {
        signUpButton.click();
    }

    public void addValidPerson(String userid, String firstName, String lastName, String email, String password) {
        setUserid(userid);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        submitValid();
    }

    public boolean hasErrorMessage(String message) {
        for (WebElement errorMessage : driver.findElements(By.cssSelector("div.alert-danger ul li"))) {
            if (message.equals(errorMessage.getText())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasStickyUserid(String userid) {
        return userid.equals(useridField.getAttribute("value"));
    }

    public boolean hasStickyFirstName(String firstName) {
        return firstName.equals(firstNameField.getAttribute("value"));
    }

    public boolean hasStickyLastName(String lastName) {
        return lastName.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyEmail(String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasStickyPassword(String password) {
        return password.equals(passwordField.getAttribute("value"));
    }

    public boolean hasEmptyUserid() {
        return useridField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyFirstName() {
        return firstNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyLastName() {
        return lastNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEmail() {
        return emailField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyPassword() {
        return passwordField.getAttribute("value").isEmpty();
    }

}

