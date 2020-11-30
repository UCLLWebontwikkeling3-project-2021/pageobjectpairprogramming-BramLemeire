import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page{
    @FindBy(id = "useridLogin")
    private WebElement useridField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "logout")
    private WebElement logoutButton;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Home");
    }

    public void setUserid(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitLogin() {
        loginButton.click();
    }

    public HomePage submitLogout() {
        logoutButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasWelcomeMessage(String message) {
        //return driver.findElement(By.id("welcome")).getText().equals(message);
        WebElement welcomeMsg = driver.findElement(By.cssSelector("div#container main p"));
        return (message.equals(welcomeMsg.getText()));
    }

}

