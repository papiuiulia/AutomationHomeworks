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

public class WaitExercises2 {

    WebDriver driver;
    WebDriverWait wait;

    // locators
    private By products = By.cssSelector(".card");
    private By productLinks = By.cssSelector("[data-test='product-name']");
    private By logo = By.cssSelector(".navbar-brand");
    private By searchInput = By.cssSelector("input[data-test='search-query']");
    private By productImage = By.cssSelector(".container img");
    private By addToCart = By.cssSelector("[data-test='add-to-cart']");
    private By hammerFilter = By.xpath("//label[contains(.,'Hammer')]");

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicesoftwaretesting.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    private WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private List<WebElement> waitForProducts() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(products));
    }

    @Test
    public void testHomePageTitleAndLogo() {

        Assert.assertTrue(driver.getTitle().contains("Practice Software Testing"));
        Assert.assertTrue(waitForVisible(logo).isDisplayed());
    }

    @Test
    public void testSearchProduct() {

        WebElement search = waitForVisible(searchInput);

        search.sendKeys("hammer");
        search.sendKeys(Keys.ENTER);

        List<WebElement> results = waitForProducts();

        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void testOpenProduct() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productLinks));

        List<WebElement> productList = driver.findElements(productLinks);

        productList.get(0).click();

        WebElement image = waitForVisible(productImage);
        WebElement cartButton = waitForVisible(addToCart);

        Assert.assertTrue(image.isDisplayed());
        Assert.assertTrue(cartButton.isDisplayed());
    }

    @Test
    public void testFilterProducts() {

        waitForVisible(hammerFilter).click();

        List<WebElement> filtered = waitForProducts();

        Assert.assertTrue(filtered.size() > 0);
    }
}