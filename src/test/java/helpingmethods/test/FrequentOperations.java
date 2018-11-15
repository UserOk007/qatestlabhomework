package helpingmethods.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.EventHandler;

import java.util.concurrent.TimeUnit;


/**
 * Frequent operations - methods that are used in most classes
 */
public class FrequentOperations {

    public static final WebDriver driver = getConfiguredDriver(); //getDriver();
    public static final WebDriverWait wait = new WebDriverWait(driver, 7);

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//resources//chromedriver.exe");
        return new ChromeDriver();
    }

    public static void loginWebsite() {

        waitUntilElementAppears("email");

        WebElement loginField = driver.findElement(By.id("email"));
        loginField.sendKeys("webinar.test@gmail.com");

        WebElement passwordField = driver.findElement(By.id("passwd"));
        passwordField.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement loginButton = driver.findElement(By.name("submitLogin"));
        loginButton.click();

        waitUntilElementAppears("nav-sidebar");
    }

    public static void waitUntilElementAppears(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public static EventFiringWebDriver getConfiguredDriver() {
        EventFiringWebDriver driver = new EventFiringWebDriver(getDriver());
        driver.register(new EventHandler());

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);

        return driver;
    }
}
