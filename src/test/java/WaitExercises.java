import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;

public class WaitExercises {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicesoftwaretesting.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // asteapta sa se încarce produsele
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card")));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    // 1 verificare titlu + logo
    @Test
    public void testHomePageTitleAndLogo() {

        Assert.assertTrue(driver.getTitle().contains("Practice Software Testing"));

        WebElement logo = driver.findElement(By.cssSelector(".navbar-brand"));

        Assert.assertTrue(logo.isDisplayed());
    }

    // 2 search product
    @Test
    public void testSearchProduct() {

        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[data-test='search-query']")
                )
        );

        search.sendKeys("hammer");
        search.sendKeys(Keys.ENTER);

        List<WebElement> results = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector(".card"),
                        0
                )
        );

        Assert.assertTrue(results.size() > 0);
    }

    // 3 open product
    @Test
    public void testOpenProduct() {

        List<WebElement> products = driver.findElements(By.cssSelector(".card"));

        products.get(0).click();

        WebElement image = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".container img")
                )
        );

        WebElement addToCart = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='add-to-cart']")
                )
        );

        Assert.assertTrue(image.isDisplayed());
        Assert.assertTrue(addToCart.isDisplayed());
    }

    // 4 filter products
    @Test
    public void testFilterProducts() {

        WebElement filter = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[contains(.,'Hammer')]")
                )
        );

        filter.click();

        List<WebElement> filtered = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector(".card"),
                        0
                )
        );

        Assert.assertTrue(filtered.size() > 0);
    }
}