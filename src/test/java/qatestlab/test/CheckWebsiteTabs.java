package qatestlab.test;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpingmethods.test.FrequentOperations.driver;
import static helpingmethods.test.FrequentOperations.loginWebsite;


/**
 * Implementation of task related to the lecture #2
 */
public class CheckWebsiteTabs {

    private static final String SITE_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/index.php?" +
            "controller=AdminLogin&token=d251f363cceb5a849cb7330938c64dea";
    private static final int DELAY_EXECUTION_TIME = 4000;
    public static final String PAGE_TITLE = ".page-title";
    public static final String PAGE_TITLE_OTHER = "h2.title";

    public static void main(String[] args)  {

        //Script A
        driver.manage().window().maximize();
        driver.get(SITE_URL);
        loginWebsite();
        clickOnUserAvatar();
        logout();


        // Script B
        delayExecution();
        loginWebsite();
        delayExecution();
        goToTab("Пульт", "tab-AdminDashboard");
        goToTab("Заказы", "subtab-AdminParentOrders");
        goToTabOther("товары", "subtab-AdminCatalog"); //goToTabOther
        goToTabOtherWay("Управление клиентами", "Клиенты"); //goToTabOtherWay
        goToTab("Customer Service", "subtab-AdminParentCustomerThreads");
        goToTab("Статистика", "subtab-AdminStats");
        goToTabOther("Выбор модуля", "subtab-AdminParentModulesSf"); //goToTabOther
        goToTabOtherWay("Шаблоны > Шаблон", "Design"); //goToTabOtherWay
        goToTab("Перевозчики", "subtab-AdminParentShipping");
        goToTab("Payment Methods", "subtab-AdminParentPayment");
        goToTab( "Локализация", "subtab-AdminInternational");
        goToTab("General", "subtab-ShopParameters");
        goToTab("Information", "subtab-AdminAdvancedParameters");
        driver.quit();
    }

    private static void goToTabOther(String expectedCurrentPageTitle, String tabIDOther)  {
        openSpecificTab(By.id(tabIDOther));
        System.out.print("Current title is " + driver.getTitle());
        driver.navigate().refresh();
        delayExecution();
        System.out.print(getCurrentPageTitle(PAGE_TITLE_OTHER));
        String currentPageTitleOther = getCurrentPageTitle(PAGE_TITLE_OTHER);
        Assert.assertEquals(currentPageTitleOther, expectedCurrentPageTitle);
        delayExecution();
    }

    private static void goToTab(String expectedCurrentPageTitle, String tabID) {
        openSpecificTab(By.id(tabID));
        System.out.print("Current title is " + driver.getTitle());
        driver.navigate().refresh();
        delayExecution();
        System.out.print(getCurrentPageTitle(PAGE_TITLE));
        String currentPageTitle = getCurrentPageTitle(PAGE_TITLE);
        Assert.assertEquals(currentPageTitle, expectedCurrentPageTitle);
    }

    public static void clickOnUserAvatar() {

        delayExecution();
        WebElement userAvatar = driver.findElement(By.id("employee_infos"));
        userAvatar.click();
    }


    private static void logout() {
        WebElement logoutOption = driver.findElement(By.id("header_logout"));
        logoutOption.click();
    }

    private static void delayExecution() {
        try {
            Thread.sleep(DELAY_EXECUTION_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void goToTabOtherWay(String expectedCurrentPageTitle, String linkTabText) {
        openSpecificTabOther(By.linkText(linkTabText));
        System.out.print("Current title is " + driver.getTitle());
        driver.navigate().refresh();
        delayExecution();
        System.out.print(getCurrentPageTitle(PAGE_TITLE));
        String currentPageTitle = getCurrentPageTitle(PAGE_TITLE);
        Assert.assertEquals(currentPageTitle, expectedCurrentPageTitle);
        delayExecution();
    }

    private static String getCurrentPageTitle(String pageTitle) {
        WebElement currentPageLocator = driver.findElement(By.cssSelector(pageTitle));
        return currentPageLocator.getText();
    }

    private static boolean openSpecificTab(By id) {
        try {
            WebElement adminDashboard = driver.findElement(id);
            adminDashboard.click();
            delayExecution();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    private static boolean openSpecificTabOther(By linkText) {
        try{
            WebElement adminDashboard = driver.findElement(linkText);
            adminDashboard.click();
            delayExecution();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
