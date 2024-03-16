import org.example.models.DigiklaseLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class DigiklaseTest {

    public static WebDriver driver;

    public static String pageUrl = "https://app.digiklase.lt/login";

    @BeforeClass
    public static void beforeClass() {
        driver = new ChromeDriver();
        DigiklaseLogin.driver = driver;
        driver.get(pageUrl);
    }

    @Test


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
     //   driver.quit();
    }
}

