import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class productsPractice {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCategoryFilter() {
        driver.get("https://practicesoftwaretesting.com/");

        // Prima categorie
        WebElement firstCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul/li/a[contains(@href,'product-category')]")));
        String categoryName = firstCategory.getText().trim();
        firstCategory.click();

        // Primul filtru din sidebar
        WebElement firstFilter = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//aside//li/a")));
        firstFilter.click();

        // Produsele vizibile
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//ul/li/a[contains(@href,'product')]")));

        int count = 0;
        for (WebElement product : products) {
            if (product.getText().toLowerCase().contains(categoryName.toLowerCase())) {
                count++;
            }
        }

        Assert.assertTrue(count >= 2, "Cel puțin 2 produse trebuie să conțină numele categoriei");
    }

    @Test
    public void testAddToCartSingleProduct() {
        driver.get("https://practicesoftwaretesting.com/");

        // Primul produs
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul/li/a[contains(@href,'product')]")));
        firstProduct.click();

        // Adaugă în coș
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Add to cart')]")));
        addToCart.click();

        // Deschide coșul
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'View cart')]")));
        viewCart.click();

        // Verifică produsul în coș
        WebElement productInCart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(@class,'product-name')]/a")));
        Assert.assertTrue(productInCart.isDisplayed(), "Produsul trebuie să apară în coș");

        // Verifică cantitatea
        WebElement quantity = driver.findElement(By.xpath("//input[@type='number']"));
        Assert.assertEquals(quantity.getAttribute("value"), "1", "Cantitatea trebuie să fie 1");
    }

    @Test
    public void testSearchAndAddHammer() {
        driver.get("https://practicesoftwaretesting.com/");

        // Căutare produs
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='s']")));
        searchInput.sendKeys("Hammer");
        searchInput.submit();

        // Selectăm produsul Hammer
        WebElement hammerProduct = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul/li/a[contains(text(),'Hammer')]")));
        hammerProduct.click();

        // Adaugă în coș
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Add to cart')]")));
        addToCart.click();

        // Deschide coșul
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'View cart')]")));
        viewCart.click();

        // Verifică produsul în coș
        WebElement productInCart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(@class,'product-name')]/a")));
        Assert.assertTrue(productInCart.getText().toLowerCase().contains("hammer"), "Produsul trebuie să fie în coș");

        // Verifică cantitatea
        WebElement quantity = driver.findElement(By.xpath("//input[@type='number']"));
        Assert.assertEquals(quantity.getAttribute("value"), "1", "Cantitatea trebuie să fie 1");

        // Verifică prețul
        WebElement price = driver.findElement(By.xpath("//td[contains(@class,'product-subtotal')]"));
        Assert.assertTrue(price.isDisplayed(), "Prețul trebuie să fie afișat");
    }
}