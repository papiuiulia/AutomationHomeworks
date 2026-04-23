package Tema9;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShopTests {

    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void addProductToCartTest() {
        homePage.open();

        String productName = "Combination Pliers"; // IMPORTANT: din site real

        homePage.clickOnProduct(productName);

        String name = productPage.getProductName();
        productPage.addToCart();

        Assert.assertTrue(cartPage.isProductInCart(name));

        driver.quit();
    }

    @Test
    public void productDetailsTest() {
        homePage.open();

        homePage.clickOnProduct("Combination Pliers");

        Assert.assertFalse(productPage.getProductName().isEmpty());
        Assert.assertTrue(productPage.getPrice().contains("$"));

        driver.quit();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}