package qatestlab.test;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * Created by isla on 01.11.2018.
 */
public class CheckWebsiteTabs {


    public static void main(String[] args) throws InterruptedException {

        //Script A
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/index.php?controller=AdminLogin&token=d251f363cceb5a849cb7330938c64dea");
        loginWebsite(driver);
        clickOnUserAvatar(driver);
        logout(driver);


        // Script B
        Thread.sleep(7000);
        loginWebsite(driver);
        Thread.sleep(7000);
        goToTab(driver, "Пульт", "tab-AdminDashboard");
        goToTab(driver, "Заказы", "subtab-AdminParentOrders");
        goToTabOther(driver, "товары", "subtab-AdminCatalog");
        goToTabOtherWay(driver, "Управление клиентами", "Клиенты");
        goToTab(driver, "Customer Service", "subtab-AdminParentCustomerThreads");
        goToTab(driver, "Статистика", "subtab-AdminStats");
        goToTabOther(driver, "Выбор модуля", "subtab-AdminParentModulesSf");
        goToTabOtherWay(driver, "Шаблоны > Шаблон", "Design");
        goToTab(driver, "Перевозчики", "subtab-AdminParentShipping");
        goToTab(driver, "Payment Methods", "subtab-AdminParentPayment");
        goToTab(driver, "Локализация", "subtab-AdminInternational");
        goToTab(driver, "General", "subtab-ShopParameters");
        goToTab(driver, "Information", "subtab-AdminAdvancedParameters");
        driver.quit();
    }

    private static void goToTab(WebDriver driver, String expectedCurrentPageTitle, String tabID) throws InterruptedException {
        openSpecificTab(driver, By.id(tabID));
        System.out.print("Current title is " + driver.getTitle());
        driver.navigate().refresh();
        Thread.sleep(7000);
        System.out.print(getCurrentPageTitle(driver));
        String currentPageTitle = getCurrentPageTitle(driver);
        Assert.assertEquals(currentPageTitle, expectedCurrentPageTitle);
    }

    private static void goToTabOther(WebDriver driver, String expectedCurrentPageTitle, String tabIDOther) throws InterruptedException {

        openSpecificTab(driver, By.id(tabIDOther));
        System.out.print("Current title is " + driver.getTitle());
        driver.navigate().refresh();
        Thread.sleep(7000);
        System.out.print(getCurrentPageTitleOther(driver));
        String currentPageTitleOther = getCurrentPageTitleOther(driver);
        Assert.assertEquals(currentPageTitleOther, expectedCurrentPageTitle);
        Thread.sleep(5000);
    }

    private static void goToTabOtherWay(WebDriver driver, String expectedCurrentPageTitle, String linkTabText) throws InterruptedException {

        openSpecificTabOther(driver, By.linkText(linkTabText));
        System.out.print("Current title is " + driver.getTitle());
        driver.navigate().refresh();
        Thread.sleep(7000);
        System.out.print(getCurrentPageTitle(driver));
        String currentPageTitle = getCurrentPageTitle(driver);
        Assert.assertEquals(currentPageTitle, expectedCurrentPageTitle);
        Thread.sleep(5000);
    }

    private static String getCurrentPageTitle(WebDriver driver) {
        WebElement currentPageLocator = driver.findElement(By.className("page-title"));
        return currentPageLocator.getText();
    }

    private static String getCurrentPageTitleOther(WebDriver driver) {
        WebElement currentPageLocatorOther = driver.findElement(By.cssSelector("h2.title"));
        return currentPageLocatorOther.getText();
    }

    private static void openSpecificTab(WebDriver driver, By id) throws InterruptedException {
        WebElement adminDashboard = driver.findElement(id);
        adminDashboard.click();
        Thread.sleep(7000);
    }


    private static void openSpecificTabOther(WebDriver driver, By linkText) throws InterruptedException {
        WebElement adminDashboard = driver.findElement(linkText);
        adminDashboard.click();
        Thread.sleep(7000);
    }


    public static void loginWebsite(WebDriver driver) throws InterruptedException {
        WebElement loginField = driver.findElement(By.id("email"));
        loginField.sendKeys("webinar.test@gmail.com");

        WebElement passwordField = driver.findElement(By.id("passwd"));
        passwordField.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement loginButton = driver.findElement(By.name("submitLogin"));
        loginButton.click();

        Thread.sleep(15);
    }


    public static void clickOnUserAvatar(WebDriver driver) throws InterruptedException {

        Thread.sleep(10000);
        WebElement userAvatar = driver.findElement(By.id("employee_infos"));
        userAvatar.click();
    }

    private static void logout(WebDriver driver) {
        WebElement logoutOption = driver.findElement(By.id("header_logout"));
        logoutOption.click();
    }

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//resources//chromedriver.exe");
        return new ChromeDriver();

    }

}