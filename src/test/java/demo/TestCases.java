package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Equivalence.Wrapper;

import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    private Wrappers Wrappers;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    @Test
    public void testCase01() {
        try {
            System.out.println("Started TestCase01");
            Wrappers.navigateTo("https://forms.gle/wjPkzeSEk1CM7KgGA");
            Wrappers.waitForPageToLoad();
            Thread.sleep(3000);
            System.out.println("page Loaded!");

            Wrappers.sendKeys(By.xpath("//input[@type='text']"), "Crio Learner");

            long epochTime = System.currentTimeMillis();
            Wrappers.sendKeys(By.xpath("//textarea[@aria-label='Your answer']"), "I want to be the best QA Engineer!" + epochTime);
            System.out.println("current time entered: " + epochTime);

            Wrappers.click(By.xpath("(//div[@id='i13'])[1]"));

            Wrappers.click(By.xpath("(//div[@id='i30'])[1]"));
            Wrappers.click(By.xpath("(//div[@id='i33'])[1]"));
            Wrappers.click(By.xpath("(//div[@id='i39'])[1]"));

            Wrappers.click(By.xpath("(//div[@jsname='wQNmvb'])[1]"));
            Thread.sleep(2000);
            Wrappers.click(By.xpath("(//div[@data-value='Mr'])[2]"));

            Wrappers.sendKeys(By.xpath("//input[@type='date']"), "28");
            Wrappers.sendKeys(By.xpath("//input[@type='date']"), "06");
            Wrappers.sendKeys(By.xpath("//input[@type='date']"), "2024");
            System.out.println("Date Entered!");

            Wrappers.sendKeys(By.xpath("(//input[@type='number'])[1]"), "7");
            Wrappers.sendKeys(By.xpath("(//input[@type='number'])[2]"), "30");
            System.out.println("Time Entered!");

            Wrappers.click(By.xpath("//div[@role='button'][1]"));
            Thread.sleep(3000);

            WebElement confirmMessage = Wrappers.findElement("//div[@class='vHW8K']");
            if(confirmMessage.isDisplayed()){
                System.out.println("Form Submitted successfully!");
                System.out.println("Sucess message: " + confirmMessage.getText());
            }

            System.out.println("End TestCase01");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TestCase01 Failed!");
        }

    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        Wrappers = new Wrappers(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        Wrappers.close();
        Wrappers.quit();

    }
}