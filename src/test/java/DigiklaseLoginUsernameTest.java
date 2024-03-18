import org.example.models.DigiklaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DigiklaseLoginUsernameTest {

    public static WebDriver driver;
    public static String pageUrl = "https://app.digiklase.lt/login-username";
    public static String validUserUsername = "";
    public static String validUserPassword = "";

    @BeforeClass
    public static void beforeClass() {
        driver = new ChromeDriver();
        DigiklaseLogin.driver = driver;
        driver.get(pageUrl);
    }

    @Test
    public void testLoginEmptyUsernameFieldValidation() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "",
                validUserPassword,
                ""
        );

        digiklaseLogin.fillAllFieldsLoginUsername();

        String actualValidationMessage = driver.findElement(By.id("username")).getAttribute("validationMessage");
        String expectedValidationMessage = "Please fill out this field.";

        Assert.assertEquals(actualValidationMessage, expectedValidationMessage);
    }

    @Test
    public void testLoginEmptyPasswordFieldValidation() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "",
                "",
                validUserUsername
        );

        digiklaseLogin.fillAllFieldsLoginUsername();

        String actualValidationMessage = driver.findElement(By.id("password")).getAttribute("validationMessage");
        String expectedValidationMessage = "Please fill out this field.";

        Assert.assertEquals(actualValidationMessage, expectedValidationMessage);
    }

    @Test
    public void testLoginInvalidUsername() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "",
                validUserPassword,
                "user"
        );

        digiklaseLogin.fillAllFieldsLoginUsername();

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/main/div/div/div/div/section/form/div[3]/div[2]"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Neteisingai įvestas prisijungimas arba slaptažodis.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testLoginInvalidPassword() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "",
                "password",
                validUserUsername
        );

        digiklaseLogin.fillAllFieldsLoginUsername();

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/main/div/div/div/div/section/form/div[3]/div[2]"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Neteisingai įvestas prisijungimas arba slaptažodis.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testLoginInvalidUsernameAndPassword() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "",
                "password",
                "user"
        );

        digiklaseLogin.fillAllFieldsLoginUsername();

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/main/div/div/div/div/section/form/div[3]/div[2]"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Neteisingai įvestas prisijungimas arba slaptažodis.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testLoginFacebookButton() {
        WebElement facebookButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/section/form/div[1]/div/a"));
        facebookButton.click();

        Assert.assertTrue(driver.getCurrentUrl().contains("www.facebook.com"));

    }

    @Test
    public void testLoginValidCredentials() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "",
                validUserPassword,
                validUserUsername
        );

        digiklaseLogin.fillAllFieldsLoginUsername();
    }

    @BeforeMethod
    public void getWebsite() {
        driver.get(pageUrl);
    }

    @AfterMethod
    public void wait1S() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}

