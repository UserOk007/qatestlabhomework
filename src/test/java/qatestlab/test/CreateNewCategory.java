package qatestlab.test;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpingmethods.test.FrequentOperations.driver;
import static helpingmethods.test.FrequentOperations.loginWebsite;


/**
 * Lecture 3 Homework
 */
public class CreateNewCategory {

    private static final WebDriverWait wait = new WebDriverWait(driver, 7);
    private static final String URL_ADMIN = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0";
    private static final String CATALOG_TAB_ID = "subtab-AdminCatalog";
    private static final String CATEGORIES_SUBTAB_LOCATOR = "//*[@id='subtab-AdminCategories']/a";
    private static final String ADD_CATEGORY_BUTTON = "#page-header-desc-category-new_category";
    private static final String CATEGORY_NAME_INPUT = "input[id=name_1]";
    private static final String SAVE_CATEGORY_BUTTON = "button[name='submitAddcategoryAndBackToParent']";
    private static final String RETURN_TO_ALL_CATEGORIES_BUTTON = "#desc-category-back";
    private static final String ALL_CATEGORIES_TABLE_LOCATOR = "#table-category";
    private static final String CATEGORY_NAME_INPUT_FILTRATION = "input[name='categoryFilter_name'";
    private static final String FILTER_CATEGORY_BUTTON = "submitFilterButtoncategory";
    private static final String INPUT_CATEGORY_TEXT = "New test category";
    private static final String FILTERED_CATEGORY_NAME_CELL = "//td[3]";
    private static final String INPUT_CATEGORY_TEXT_FILTRATION = "New test category";
    private static final String EXPECTED_TEXT = "New test category";

    public static void main(String[] args) {


        driver.manage().window().maximize();
        driver.get(URL_ADMIN);
        loginWebsite();
        openCategoriesSubTab();
        addNewCategory();
        returnToAllCategoriesResults();
        filterByCategoryName();
        checkFilteredResult();
        driver.quit();
    }

    private static void checkFilteredResult() {
        WebElement nameFilterResult = driver.findElement(By.xpath(FILTERED_CATEGORY_NAME_CELL));
        String actualFilteredName = nameFilterResult.getText();
        System.out.println(actualFilteredName);
        Assert.assertEquals(actualFilteredName, EXPECTED_TEXT);
    }

    private static void filterByCategoryName() {
        WebElement inputName = driver.findElement(By.cssSelector(CATEGORY_NAME_INPUT_FILTRATION));
        WebElement findButton = driver.findElement(By.id(FILTER_CATEGORY_BUTTON));

        inputName.sendKeys(INPUT_CATEGORY_TEXT_FILTRATION);
        findButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FILTERED_CATEGORY_NAME_CELL)));
    }

    private static void returnToAllCategoriesResults() {
        wait(RETURN_TO_ALL_CATEGORIES_BUTTON);
        WebElement returnToAllCategories = driver.findElement(By.cssSelector(RETURN_TO_ALL_CATEGORIES_BUTTON));
        returnToAllCategories.click();
        wait(ALL_CATEGORIES_TABLE_LOCATOR);
    }

    private static void addNewCategory() {
        WebElement addCategoryButton = driver.findElement(By.cssSelector(ADD_CATEGORY_BUTTON));
        wait(ADD_CATEGORY_BUTTON);
        addCategoryButton.click();
        wait(CATEGORY_NAME_INPUT);
        WebElement inputCategory = driver.findElement(By.cssSelector(CATEGORY_NAME_INPUT));
        WebElement saveCategoryButton = driver.findElement(By.cssSelector(SAVE_CATEGORY_BUTTON));
        inputCategory.sendKeys(INPUT_CATEGORY_TEXT);
        saveCategoryButton.click();
    }

    private static void openCategoriesSubTab() {
        WebElement catalogTab = driver.findElement(By.id(CATALOG_TAB_ID));
        WebElement categories = driver.findElement(By.xpath(CATEGORIES_SUBTAB_LOCATOR));

        Actions actions = new Actions(driver);
        actions.moveToElement(catalogTab).pause(500).moveToElement(categories).click(categories).build().perform();
    }

    private static void wait(String cssSelector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }
}
