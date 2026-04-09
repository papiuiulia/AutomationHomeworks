package Tema8;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ex8 {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.openUrl("https://example.com");
        loginPage.login("admin", "1234");

        driver.quit();
    }
}
