package Tema8;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestRunner {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openUrl("https://example.com/login");
        loginPage.login("admin", "password");

        driver.quit();
    }
}
