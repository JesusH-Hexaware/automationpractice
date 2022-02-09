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
    public static Logger log = (Logger) LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void setupBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "tc02")
    public void invalidEmail(String baseUrl,
                             String email) throws IOException{
        landingPage landingPage = new landingPage(driver);
        loginPage loginPage = new loginPage(driver);

        driver.get(baseUrl);
        Assert.assertTrue(landingPage.getSingIn().isDisplayed());
        landingPage.getSingIn().click();
        log.info("The user clicks on SignIn");
        Assert.assertTrue(loginPage.getEmailCreate().isDisplayed());
        loginPage.getEmailCreate().sendKeys(email);
        Assert.assertTrue(loginPage.getCreateAccountBtm().isDisplayed());
        loginPage.getCreateAccountBtm().click();
        Assert.assertTrue(loginPage.getCreateAccountError().isDisplayed());
        String expectedText = "Invalid email address.";
        Assert.assertEquals(expectedText, loginPage.getCreateAccountError().getText());

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
