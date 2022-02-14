package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.landingPage;
import pageObject.summerDressesPage;
import resources.base;
import resources.xlsxUtil;

import java.io.IOException;

public class test_07_wishList extends base {

    public WebDriver driver;
    public String sheetName = "TC07";
    public static Logger log = LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "TC07")
    public void wishList(String baseUrl,
                         String errorMsgTxt) {

        landingPage landingPage = new landingPage(driver);

        driver.get(baseUrl);
        log.info("1. Open link http://automationpractice.com/index.php");

        Actions action = new Actions(driver);
        WebElement womenSection = landingPage.getWomenSection();
        action.moveToElement(womenSection).perform();
        log.info("2. Move your cursor over Women's link");


        WebElement summerDresses = landingPage.getSummerDresses();
        action.moveToElement(summerDresses).perform();
        action.click().build().perform();
        log.info("3. Click on sub menu 'Summer Dresses'");

        summerDressesPage summerDressesPage = new summerDressesPage(driver);
        action.moveToElement(summerDressesPage.getDress()).perform();
        log.info("4. Mouse hover on the second product displayed");

        action.moveToElement(summerDressesPage.addWishList()).perform();
        action.click().build().perform();
        log.info("5. 'Add to Wishlist' will appear on the bottom of that product, click on it.");

        String errorMsg = summerDressesPage.getError().getText();
        log.info("Error Message: " + errorMsg);
        Assert.assertEquals(errorMsg, errorMsgTxt);
        log.info("6. Verify that error message is displayed 'You must be logged in to manage your wish list.'");

    }

    @DataProvider(name = "TC07")
    public Object[][] getData() throws IOException{
        String path = prop.getProperty("excelPath");
        xlsxUtil xlsx = new xlsxUtil(path);
        int totalRows = xlsx.getRowCount(sheetName);
        int totalColumns = xlsx.getCellCount(sheetName, 1);
        String[][] data = new String[totalRows][totalColumns];

        for (int i = 1; i <= totalRows; i++){
            for (int j = 0; j < totalColumns; j++){
                data[i-1][j] = xlsx.getCellData(sheetName, i , j);
            }
        }
        return data;
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }

}
