import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Tema2 {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    public void acceptCookiesIfPresent() {
        try {
            driver.findElement(By.xpath("//*[contains(text(),'Accept')]")).click();
        } catch (Exception e) {
            System.out.println("Popup-ul nu a aparut.");
        }
    }

    @Test
    public void testExample() {
        driver.get("https://example.com/");
        acceptCookiesIfPresent();

        Assert.assertTrue(driver.getTitle().contains("Example"));
    }

    @Test
    public void testApple() {
        driver.get("https://www.apple.com/");
        acceptCookiesIfPresent();

        Assert.assertNotNull(driver.getTitle());
    }
}