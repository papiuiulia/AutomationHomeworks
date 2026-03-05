import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTablesTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/webtables");

        // Click pe Add
        WebElement addButton = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
        addButton.click();

        // Completeaza campurile din popup

        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("User");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("test@email.com");
        driver.findElement(By.xpath("//input[@id='age']")).sendKeys("25");
        driver.findElement(By.xpath("//input[@id='salary']")).sendKeys("5000");
        driver.findElement(By.xpath("//input[@id='department']")).sendKeys("QA");

        // Click Submit
        driver.findElement(By.xpath("//button[@id='submit']")).click();

        driver.quit();
    }
}