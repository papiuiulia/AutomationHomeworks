package Tema9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public String getPrice() {
        return driver.findElement(By.cssSelector(".price")).getText();
    }

    public void addToCart() {
        driver.findElement(By.cssSelector("[data-test='add-to-cart']")).click();
    }
}