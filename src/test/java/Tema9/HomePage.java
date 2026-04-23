package Tema9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private String url = "https://practicesoftwaretesting.com/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(url);
    }

    public void clickOnProduct(String productName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[contains(normalize-space(),'" + productName + "')]")
        ));

        driver.findElement(By.xpath(
                "//h5[contains(normalize-space(),'" + productName + "')]"
        )).click();
    }
}