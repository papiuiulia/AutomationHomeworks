package Tema8;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl(String url) {
        System.out.println("Opening URL: " + url);
        driver.get(url); // în real Selenium
    }
}