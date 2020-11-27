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

public class ContactsInTwoWeeksTest {
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
    public void test_Contact_AddedToday_TotalContactsInTwoWeeks_RaisedByOne() {
        driver.get(path+"?command=Contacts");
        String sentenceBeforeSubmit = driver.findElement(By.id("lastContacts")).getText();
        int amountOfContactsBeforeSubmit = Integer.parseInt(sentenceBeforeSubmit.split(" ")[8]);

        String oneWeekAgo = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        addContact("Jan", "Janssens", oneWeekAgo, "15:20", "0412345678", "jan@gmail.com");
        String sentenceAfterSubmit = driver.findElement(By.id("lastContacts")).getText();
        int amountOfContactsAfterSubmit = Integer.parseInt(sentenceAfterSubmit.split(" ")[8]);
        assertEquals(amountOfContactsBeforeSubmit + 1, amountOfContactsAfterSubmit);
    }

    @Test
    public void test_Contact_Added1WeekAgo_TotalContactsInTwoWeeks_RaisedByOne() {
        driver.get(path+"?command=Contacts");
        String sentenceBeforeSubmit = driver.findElement(By.id("lastContacts")).getText();
        int amountOfContactsBeforeSubmit = Integer.parseInt(sentenceBeforeSubmit.split(" ")[8]);

        String oneWeekAgo = LocalDate.now().minus(Period.ofWeeks(1)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        addContact("Jan", "Janssens", oneWeekAgo, "15:20", "0412345678", "jan@gmail.com");
        String sentenceAfterSubmit = driver.findElement(By.id("lastContacts")).getText();
        int amountOfContactsAfterSubmit = Integer.parseInt(sentenceAfterSubmit.split(" ")[8]);
        assertEquals(amountOfContactsBeforeSubmit + 1, amountOfContactsAfterSubmit);
    }

    @Test
    public void test_Contact_Added2WeeksAgo_TotalContactsInTwoWeeks_RaisedByOne() {
        driver.get(path+"?command=Contacts");
        String sentenceBeforeSubmit = driver.findElement(By.id("lastContacts")).getText();
        int amountOfContactsBeforeSubmit = Integer.parseInt(sentenceBeforeSubmit.split(" ")[8]);

        String oneWeekAgo = LocalDate.now().minus(Period.ofWeeks(2)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        addContact("Jan", "Janssens", oneWeekAgo, "15:20", "0412345678", "jan@gmail.com");
        String sentenceAfterSubmit = driver.findElement(By.id("lastContacts")).getText();
        int amountOfContactsAfterSubmit = Integer.parseInt(sentenceAfterSubmit.split(" ")[8]);
        assertEquals(amountOfContactsBeforeSubmit + 1, amountOfContactsAfterSubmit);
    }

    @Test
    public void test_Contact_Added4WeeksAgo_TotalContactsInTwoWeeks_NotRaised() {
        driver.get(path+"?command=Contacts");
        String sentenceBeforeSubmit = driver.findElement(By.id("lastContacts")).getText();
        int amountOfContactsBeforeSubmit = Integer.parseInt(sentenceBeforeSubmit.split(" ")[8]);

        String oneWeekAgo = LocalDate.now().minus(Period.ofWeeks(4)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        addContact("Jan", "Janssens", oneWeekAgo, "15:20", "0412345678", "jan@gmail.com");
        String sentenceAfterSubmit = driver.findElement(By.id("lastContacts")).getText();
        int amountOfContactsAfterSubmit = Integer.parseInt(sentenceAfterSubmit.split(" ")[8]);
        assertEquals(amountOfContactsBeforeSubmit, amountOfContactsAfterSubmit);
    }


    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void addContact(String firstName, String lastName, String date, String hour, String tel, String email) {
        fillOutField("firstName", firstName);
        fillOutField("lastName", lastName);
        fillOutField("date", date);
        fillOutField("hour", hour);
        fillOutField("tel", tel);
        fillOutField("email", email);

        WebElement button=driver.findElement(By.id("addContact"));
        button.click();
    }
}
