import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Session10 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Ex 1 - Complete fields + submit
    @Test
    public void fillTextBoxForm() {

        driver.get("https://demoqa.com/text-box");

        driver.findElement(By.id("userName")).sendKeys("Iulia");
        driver.findElement(By.id("userEmail")).sendKeys("iuliatest10@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("Cluj-Napoca");
        driver.findElement(By.id("permanentAddress")).sendKeys("Cluj");

        driver.findElement(By.id("submit")).click();
    }

    // Ex 2 - Verify output
    @Test
    public void verifyOutputContainsNameAndEmail() {

        driver.get("https://demoqa.com/text-box");

        driver.findElement(By.id("userName")).sendKeys("Iulia");
        driver.findElement(By.id("userEmail")).sendKeys("iulia@gmail.com");
        driver.findElement(By.id("submit")).click();

        String nameOutput = driver.findElement(By.id("name")).getText();
        String emailOutput = driver.findElement(By.id("email")).getText();

        Assert.assertTrue(nameOutput.contains("Iulia"));
        Assert.assertTrue(emailOutput.contains("iulia@gmail.com"));
    }

    // Ex 3 - Verify Submit button state
    @Test
    public void verifySubmitButtonDisplayedAndEnabled() {

        driver.get("https://demoqa.com/text-box");

        WebElement submitButton = driver.findElement(By.id("submit"));

        Assert.assertTrue(submitButton.isDisplayed());
        Assert.assertTrue(submitButton.isEnabled());
    }

    // Ex 4 - Modify Full Name field
    @Test
    public void modifyFullNameField() {

        driver.get("https://demoqa.com/text-box");

        WebElement nameField = driver.findElement(By.id("userName"));

        nameField.sendKeys("Initial Name");
        driver.findElement(By.id("userEmail")).sendKeys("iulia@gmail.com");

        nameField.clear();
        nameField.sendKeys("Cristina");
    }

    // Buttons Test
    @Test
    public void clickButtonAndVerifyMessage() {

        driver.get("https://demoqa.com/buttons");

        driver.findElement(By.xpath("//button[text()='Click Me']")).click();

        String message = driver.findElement(By.id("dynamicClickMessage")).getText();

        Assert.assertEquals(message, "You have done a dynamic click");
    }
}