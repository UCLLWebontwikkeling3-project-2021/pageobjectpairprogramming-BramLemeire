import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class LastTestResultTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\braml\\OneDrive\\Documenten\\School\\Toegepaste-informatica\\Webontwikkeling 3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Home");
        WebElement userid = driver.findElement(By.id("useridLogin"));
        userid.sendKeys("test");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("test");
        WebElement submit = driver.findElement(By.id("login"));
        submit.click();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_newTestResult_submitted_correctDateIsShown() {
        driver.get(path+"?command=ShowRegisterTestResult");
        String sentenceBeforeSubmit = driver.findElement(By.id("lastTestResult")).getText();
        LocalDate lastTestResultBeforeSubmit = LocalDate.parse(sentenceBeforeSubmit.split(" ")[8]);

        String oneWeekBeforeCurrentTestResult = lastTestResultBeforeSubmit.minus(Period.ofWeeks(1)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        addTestResult(oneWeekBeforeCurrentTestResult);
        driver.get(path+"?command=ShowRegisterTestResult");

        String sentenceAfterSubmit = driver.findElement(By.id("lastTestResult")).getText();
        LocalDate lastTestResultAfterSubmit = LocalDate.parse(sentenceAfterSubmit.split(" ")[8]);
        String lastTestResultAfterSubmitString = lastTestResultAfterSubmit.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        assertEquals(oneWeekBeforeCurrentTestResult, lastTestResultAfterSubmitString);
    }

    private void addTestResult(String date) {
        WebElement field=driver.findElement(By.id("date"));
        field.clear();
        field.sendKeys(date);

        WebElement button=driver.findElement(By.id("registerTestResult"));
        button.click();
    }
}
