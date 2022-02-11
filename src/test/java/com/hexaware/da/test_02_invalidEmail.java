package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.landingPage;
import pageObject.loginPage;
import resources.base;
import resources.xlsxUtil;

import java.io.IOException;

public class test_02_invalidEmail extends base {
    public WebDriver driver;
    public String sheetName = "TC02";
    public static Logger log = LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void setupBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "tc02")
    public void invalidEmail(String baseUrl,
                             String email) throws IOException {
        landingPage landingPage = new landingPage(driver);
        loginPage loginPage = new loginPage(driver);

        driver.get(baseUrl);
        log.info("1. Open this url  http://automationpractice.com/index.php");
        Assert.assertTrue(landingPage.getSingIn().isDisplayed());
        landingPage.getSingIn().click();
        log.info("2. Click on sign in link.");
        Assert.assertTrue(loginPage.getEmailCreate().isDisplayed());
        loginPage.getEmailCreate().sendKeys(email);
        Assert.assertTrue(loginPage.getCreateAccountBtm().isDisplayed());
        loginPage.getCreateAccountBtm().click();
        log.info("3. Enter invalid email address in the email box and click enter.");
        Assert.assertTrue(loginPage.getCreateAccountError().isDisplayed());
        String expectedText = "Invalid email address.";
        Assert.assertEquals(expectedText, loginPage.getCreateAccountError().getText());
        log.info("4. Validate that an error message is displaying saying \"Invalid email address.\"");

    }

    @DataProvider(name = "tc02")
    public Object[][] getData() throws IOException {
        String path = prop.getProperty("excelPath");
        xlsxUtil xlsx = new xlsxUtil(path);
        int totalRows = xlsx.getRowCount(sheetName);
        int totalColumns = xlsx.getCellCount(sheetName, 1);
        String data[][] = new String[totalRows][totalColumns];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                data[i - 1][j] = xlsx.getCellData(sheetName, i, j);
            }
        }
        return data;
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

}
