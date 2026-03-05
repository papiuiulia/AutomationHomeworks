import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class PracticeFormTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/automation-practice-form");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Completeaza formularul
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("User");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("test@email.com");
        driver.findElement(By.xpath("//label[@for='gender-radio-2']")).click();
        driver.findElement(By.xpath("//input[@id='userNumber']")).sendKeys("0712345678");
        driver.findElement(By.xpath("//input[@id='subjectsInput']")).sendKeys("Math");
        driver.findElement(By.xpath("//textarea[@id='currentAddress']")).sendKeys("Test Address 123");

        // Ascunde banner-ul care blocheaza butonul Submit
        ((JavascriptExecutor) driver).executeScript(
                "var fixedban = document.getElementById('fixedban'); if(fixedban){fixedban.style.display='none';}"
        );

        // Click pe Submit folosind JavaScript
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);

        // Asteapta popup-ul sa fie vizibil
        WebElement popupTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Thanks for submitting the form')]")
                )
        );

        System.out.println("Popup text: " + popupTitle.getText());

        // Inchide popup
        WebElement close = driver.findElement(By.xpath("//button[text()='Close']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", close);

        driver.quit();
    }
}