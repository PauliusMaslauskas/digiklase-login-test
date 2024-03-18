import org.example.models.DigiklaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DigiklaseLoginEmailTest {

    public static WebDriver driver;
    public static String pageUrl = "https://app.digiklase.lt/login";
    public static String validUserEmail = "";
    public static String validUserPassword = "";

    @BeforeClass
    public static void beforeClass() {
        driver = new ChromeDriver();
        DigiklaseLogin.driver = driver;
        driver.get(pageUrl);
    }

    @Test
    public void testLoginEmptyEmailFieldValidation() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "",
                validUserPassword,
                ""
        );

        digiklaseLogin.fillAllFieldsLoginEmail();

        String actualValidationMessage = driver.findElement(By.id("email")).getAttribute("validationMessage");
        String expectedValidationMessage = "Please fill out this field.";

        Assert.assertEquals(actualValidationMessage, expectedValidationMessage);
    }

    @Test
    public void testLoginEmptyPasswordFieldValidation() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                validUserEmail,
                "",
                ""
        );

        digiklaseLogin.fillAllFieldsLoginEmail();

        String actualValidationMessage = driver.findElement(By.id("password")).getAttribute("validationMessage");
        String expectedValidationMessage = "Please fill out this field.";

        Assert.assertEquals(actualValidationMessage, expectedValidationMessage);
    }

    @Test
    public void testLoginInvalidEmail() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "user@gmail.lt",
                validUserPassword,
                ""
        );

        digiklaseLogin.fillAllFieldsLoginEmail();

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/main/div/div/div/div/section/form/div[3]/div[2]"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Neteisingai įvestas prisijungimas arba slaptažodis.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testLoginInvalidPassword() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                validUserEmail,
                "password",
                ""
        );

        digiklaseLogin.fillAllFieldsLoginEmail();

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/main/div/div/div/div/section/form/div[3]/div[2]"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Neteisingai įvestas prisijungimas arba slaptažodis.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testLoginInvalidEmailAndPassword() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "user@gmail.lt",
                "password",
                ""
        );

        digiklaseLogin.fillAllFieldsLoginEmail();

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/main/div/div/div/div/section/form/div[3]/div[2]"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Neteisingai įvestas prisijungimas arba slaptažodis.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testLoginInvalidEmailFormat() {
        DigiklaseLogin digiklaseLogin = new DigiklaseLogin(
                "user%gmail.com",
                validUserPassword,
                ""
        );

        digiklaseLogin.fillEmail();
        digiklaseLogin.fillPassword();

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/main/div/div/div/div/section/form/div[3]/div"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Neteisingas elektroninio pašto adreso formatas";

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
                validUserEmail,
                validUserPassword,
                ""
        );

        digiklaseLogin.fillAllFieldsLoginEmail();

        WebElement welcomeMessage = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div/div/div/h1"));
        Assert.assertTrue(welcomeMessage.isDisplayed());
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
        driver.quit();
    }
}

