import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;


public class GroupsExercises {

    WebDriver driver;
    WebDriverWait wait;
    PracticeSitePage practicePage; // POM

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        practicePage = new PracticeSitePage(driver, wait);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


    @Test
    public void testProductTab() {
        practicePage.openMainPage();
        String mainTab = practicePage.getCurrentTab();

        practicePage.goToHandToolsCategory();
        String productName = practicePage.getFirstProductName();
        String productUrl = practicePage.getFirstProductUrl();

        practicePage.openProductInNewTab(productUrl, productName);
        practicePage.switchToTab(mainTab);
        practicePage.refreshMainPage();

        System.out.println("Test Open Product in New Tab trecut cu succes!");
    }


    @Test
    public void testTaburiTitluri() {
        driver.get("https://www.google.com");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.wikipedia.org");

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println("Tab 2: " + driver.getTitle());
        driver.switchTo().window(tabs.get(0));
        System.out.println("Tab 1: " + driver.getTitle());
    }


    @Test(priority = -1)
    public void testPriority1() { Assert.assertTrue(true); }

    @Test(priority = 0)
    public void testPriority2() { Assert.assertEquals(2, 2); }

    @Test(priority = 1)
    public void testPriority3() { Assert.assertFalse(false); }

    @Test(groups = {"smoke"})
    public void loginTest() { Assert.assertTrue(true); }

    @Test(groups = {"regression"})
    public void searchTest() { Assert.assertTrue(true); }

    @Test(groups = {"smoke", "regression"})
    public void checkoutTest() { Assert.assertTrue(true); }


    static class PracticeSitePage {
        WebDriver driver;
        WebDriverWait wait;

        public PracticeSitePage(WebDriver driver, WebDriverWait wait) {
            this.driver = driver;
            this.wait = wait;
        }

        public void openMainPage() {
            driver.get("https://practicesoftwaretesting.com/");
            wait.until(ExpectedConditions.titleContains("Practice Software Testing"));
            Assert.assertTrue(driver.getTitle().contains("Practice Software Testing"),
                    "Nu ești pe pagina corectă!");
        }

        public void goToHandToolsCategory() {
            driver.get("https://practicesoftwaretesting.com/category/hand-tools");
        }

        public String getCurrentTab() {
            return driver.getWindowHandle();
        }

        public String getFirstProductName() {
            WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='product-name']")));
            return firstProduct.getText();
        }

        public String getFirstProductUrl() {
            WebElement productLink = driver.findElement(By.cssSelector("[data-test^='product-']"));
            return productLink.getAttribute("href");
        }

        public void openProductInNewTab(String url, String productName) {
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(url);
            wait.until(ExpectedConditions.titleContains(productName));
            Assert.assertTrue(driver.getTitle().contains(productName),
                    "Produsul din tab nou nu corespunde!");
        }

        public void switchToTab(String tabHandle) {
            driver.switchTo().window(tabHandle);
        }

        public void refreshMainPage() {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.titleContains("Practice Software Testing"));
            Assert.assertTrue(driver.getTitle().contains("Practice Software Testing"),
                    "Pagina nu s-a încărcat corect după refresh!");
        }
    }
}