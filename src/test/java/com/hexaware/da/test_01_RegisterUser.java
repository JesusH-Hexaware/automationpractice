package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
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

public class test_01_RegisterUser extends base {
    public WebDriver driver;
    public String sheetName = "TC01";
    public static Logger log = (Logger) LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void setupBrowser() throws IOException{
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "tc01")
    public void landingPage(String baseUrl,
                            String email,
                            String firstname,
                            String lastname,
                            String password,
                            String address,
                            String city,
                            String state,
                            String postalCode,
                            String mobilePhone,
                            String addressAlias
                            ) throws IOException {

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
        Assert.assertTrue(createAccountPage.getFirstName().isDisplayed());
        createAccountPage.getFirstName().sendKeys(firstname);
        Assert.assertTrue(createAccountPage.getlastName().isDisplayed());
        createAccountPage.getlastName().sendKeys(lastname);
        Assert.assertTrue(createAccountPage.getPassword().isDisplayed());
        createAccountPage.getPassword().sendKeys(password);
        Assert.assertTrue(createAccountPage.getAddress().isDisplayed());
        createAccountPage.getAddress().sendKeys(address);
        Assert.assertTrue(createAccountPage.getCity().isDisplayed());
        createAccountPage.getCity().sendKeys(city);
        Assert.assertTrue(createAccountPage.getState().isEnabled());
        Select drpState = new Select(createAccountPage.getState());
        drpState.selectByVisibleText(state);
        Assert.assertTrue(createAccountPage.getPostCode().isDisplayed());
        createAccountPage.getPostCode().sendKeys(postalCode);
        Assert.assertTrue(createAccountPage.getMobilePhone().isDisplayed());
        createAccountPage.getMobilePhone().sendKeys(mobilePhone);
        Assert.assertTrue(createAccountPage.getAddressAlias().isDisplayed());
        createAccountPage.getAddressAlias().clear();
        createAccountPage.getAddressAlias().sendKeys(addressAlias);
        Assert.assertTrue(createAccountPage.getRegisterBtn().isDisplayed());
        createAccountPage.getRegisterBtn().click();

        Assert.assertTrue(myAccountPage.getMyAccount().isDisplayed());
        String expectedText = (firstname + " " + lastname);
        Assert.assertEquals(expectedText, myAccountPage.getUserAccountName().getText());

    }

    @DataProvider(name = "tc01")
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
