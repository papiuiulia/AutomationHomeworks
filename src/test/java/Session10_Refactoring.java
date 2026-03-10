import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Session10_Refactoring {

    WebDriver driver;

    private final String TEXT_BOX_URL = "https://demoqa.com/text-box";
    private final String BUTTONS_URL = "https://demoqa.com/buttons";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Helper method
    private void openPage(String url) {
        driver.get(url);
    }

    private void fillTextBoxForm(String name, String email, String currentAddress, String permanentAddress) {
        driver.findElement(By.id("userName")).sendKeys(name);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("currentAddress")).sendKeys(currentAddress);
        driver.findElement(By.id("permanentAddress")).sendKeys(permanentAddress);
    }

    private void clickSubmit() {
        driver.findElement(By.id("submit")).click();
    }

    // Ex 1
    @Test
    public void fillTextBoxFormTest() {

        openPage(TEXT_BOX_URL);

        fillTextBoxForm(
                "Iulia",
                "iuliatest10@gmail.com",
                "Cluj-Napoca",
                "Cluj"
        );

        clickSubmit();
    }

    // Ex 2
    @Test
    public void verifyOutputContainsNameAndEmail() {

        openPage(TEXT_BOX_URL);

        fillTextBoxForm(
                "Iulia",
                "iulia@gmail.com",
                "",
                ""
        );

        clickSubmit();

        String nameOutput = driver.findElement(By.id("name")).getText();
        String emailOutput = driver.findElement(By.id("email")).getText();

        Assert.assertTrue(nameOutput.contains("Iulia"));
        Assert.assertTrue(emailOutput.contains("iulia@gmail.com"));
    }

    // Ex 3
    @Test
    public void verifySubmitButtonDisplayedAndEnabled() {

        openPage(TEXT_BOX_URL);

        WebElement submitButton = driver.findElement(By.id("submit"));

        Assert.assertTrue(submitButton.isDisplayed());
        Assert.assertTrue(submitButton.isEnabled());
    }

    // Ex 4
    @Test
    public void modifyFullNameField() {

        openPage(TEXT_BOX_URL);

        WebElement nameField = driver.findElement(By.id("userName"));

        nameField.sendKeys("Initial Name");
        driver.findElement(By.id("userEmail")).sendKeys("iulia@gmail.com");

        nameField.clear();
        nameField.sendKeys("Cristina");
    }

    // Buttons Test
    @Test
    public void clickButtonAndVerifyMessage() {

        openPage(BUTTONS_URL);

        driver.findElement(By.xpath("//button[text()='Click Me']")).click();

        String message = driver.findElement(By.id("dynamicClickMessage")).getText();

        Assert.assertEquals(message, "You have done a dynamic click");
    }
}
