import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LoginTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\braml\\OneDrive\\Documenten\\School\\Toegepaste-informatica\\Webontwikkeling 3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Home");
    }


    @After
    public void clean(){
        driver.quit();
    }

    @Test
    public void test_Login_AllFieldsFilledInCorrectly_UserIsLoggedIn() {
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        String userid = generateRandomUseridInOrderToRunTestMoreThanOnce("test");
        registerPage.addValidPerson(userid,"test", "t", "test@gmail.com", "test");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid(userid);
        homePage.setPassword("test");
        homePage.submitLogin();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasWelcomeMessage("Welcome, test!"));
    }

    @Test
    public void test_Login_UseridNotFilledIn_ErrorMessageGiven(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("");
        homePage.setPassword("test");
        homePage.submitLogin();

        assertEquals("Home",homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No valid userid/password"));
    }

    @Test
    public void test_Login_PasswordNotFilledIn_ErrorMessageGiven(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("test");
        homePage.setPassword("");
        homePage.submitLogin();

        assertEquals("Home",homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No valid userid/password"));
    }

    @Test
    public void test_Login_PasswordNotValid_ErrorMessageGiven(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        String userid = generateRandomUseridInOrderToRunTestMoreThanOnce("test");
        registerPage.addValidPerson(userid,"test", "t", "test@gmail.com", "test");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid(userid);
        homePage.setPassword("wrongPassword");
        homePage.submitLogin();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No valid userid/password"));
    }

    private String generateRandomUseridInOrderToRunTestMoreThanOnce(String component) {
        int random = (int)(Math.random() * 1000 + 1);
        return random+component;
    }


}

