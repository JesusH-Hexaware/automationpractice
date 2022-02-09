package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.createAccountPage;
import pageObject.landingPage;
import pageObject.loginPage;
import pageObject.myAccountPage;
import resources.base;
import resources.xlsxUtil;

import java.io.IOException;

public class test_04_errorMessageForIncorrectValues extends base{
    public WebDriver driver;
    public String sheetName = "TC04";
    public static Logger log = (Logger) LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void setupBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "TC04")
    public void errorMsgForIncorrectValues(String baseUrl,
                                           String email,
                                           String firstname,
                                           String lastname,
                                           String password,
                                           String address,
                                           String postalCode,
                                           String mobilePhone,
                                           String invalidEmail) throws IOException {

        landingPage landingPage = new landingPage(driver);
        loginPage loginPage = new loginPage(driver);
        createAccountPage createAccountPage = new createAccountPage(driver);
        myAccountPage myAccountPage = new myAccountPage(driver);

        //driver.get(prop.getProperty("url"));
        driver.get(baseUrl);
        Assert.assertTrue(landingPage.getSingIn().isDisplayed());
        landingPage.getSingIn().click();
        log.info("The user clicks on SignIn");
        Assert.assertTrue(loginPage.getEmailCreate().isDisplayed());
        loginPage.getEmailCreate().sendKeys(email);
        Assert.assertTrue(loginPage.getCreateAccountBtm().isDisplayed());
        loginPage.getCreateAccountBtm().click();

        createAccountPage.getFirstName().sendKeys(firstname);
        createAccountPage.getlastName().sendKeys(lastname);
        createAccountPage.getEmail().clear();
        createAccountPage.getEmail().sendKeys(invalidEmail);
        createAccountPage.getAddress().sendKeys(address);
        createAccountPage.getPostCode().sendKeys(postalCode);
        createAccountPage.getMobilePhone().sendKeys(mobilePhone);

        Assert.assertTrue(createAccountPage.getRegisterBtn().isDisplayed());
        createAccountPage.getRegisterBtn().click();

        Assert.assertTrue(createAccountPage.getErrorAlert().isDisplayed());
        Assert.assertTrue(createAccountPage.getErrorAlertFirstname().isDisplayed());
        Assert.assertTrue(createAccountPage.getErrorAlertLastname().isDisplayed());
        Assert.assertTrue(createAccountPage.getErrorAlertEmail().isDisplayed());
        Assert.assertTrue(createAccountPage.getErrorAlertAddress().isDisplayed());
        Assert.assertTrue(createAccountPage.getErrorAlertPostalcode().isDisplayed());
        Assert.assertTrue(createAccountPage.getErrorAlertMobilePhoneInvalid().isDisplayed());

    }

    @DataProvider(name = "TC04")
    public Object[][] getData() throws IOException{
        String path = prop.getProperty("excelPath");
        xlsxUtil xlsx = new xlsxUtil(path);
        int totalRows = xlsx.getRowCount(sheetName);
        int totalColumns = xlsx.getCellCount(sheetName, 1);
        String data[][] = new String[totalRows][totalColumns];

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
