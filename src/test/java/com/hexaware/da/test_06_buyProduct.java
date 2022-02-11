package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.surefire.shared.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.landingPage;
import pageObject.loginPage;
import pageObject.myAccountPage;
import pageObject.summerDressesPage;
import resources.base;
import resources.xlsxUtil;

import java.io.IOException;

public class test_06_buyProduct extends base {
    public WebDriver driver;
    public String sheetName = "TC06";
    public static Logger log = LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");

    }

    @Test(dataProvider = "TC06")
    public void buyProduct(String baseUrl,
                           String userEmail,
                           String userPassword,
                           String quantity,
                           String size) throws IOException {
        landingPage landingPage = new landingPage(driver);
        loginPage loginPage = new loginPage(driver);

        driver.get(baseUrl);
        log.info("1. Open link http://automationpractice.com/index.php");
        landingPage.getSingIn().click();
        loginPage.getUserEmail().sendKeys(userEmail);
        loginPage.getUserPassword().sendKeys(userPassword);
        loginPage.getSignInButton().click();
        log.info("2. Login to the website.");

        Actions action = new Actions(driver);
        WebElement womenSection = landingPage.getWomenSection();
        action.moveToElement(womenSection).perform();
        log.info("3. Move your cursor over Women's link");

        WebElement summerDresses = landingPage.getSummerDresses();
        action.moveToElement(summerDresses).perform();
        action.click().build().perform();
        log.info("4. Click on sub menu 'Summer Dresses'");

        summerDressesPage summerDressesPage = new summerDressesPage(driver);
        action.moveToElement(summerDressesPage.getDress()).perform();
        log.info("5. Mouse hover on the second product displayed");

        action.moveToElement(summerDressesPage.getMore()).perform();
        action.click().build().perform();
        log.info("6. 'More' button will be displayed, click on 'More' button");
        summerDressesPage.setQuantity().clear();
        summerDressesPage.setQuantity().sendKeys(quantity);
        log.info("7. Increase quantity to 2");
        Select drpSize = new Select(summerDressesPage.setSize());
        drpSize.selectByVisibleText(size);
        log.info("8. Select size 'L'");
        summerDressesPage.setColor().click();
        log.info("9. Select color");
        summerDressesPage.addToCart().click();
        log.info("10. Click 'Add to Cart' button");
        summerDressesPage.proceedToCheckout().click();
        log.info("11. Click 'Proceed to checkout' button");
        summerDressesPage.summaryCheckout().click();
        summerDressesPage.addressCheckout().click();
        summerDressesPage.acceptTermsAndConditions().click();
        summerDressesPage.shippingCheckout().click();
        summerDressesPage.paymentCheckout().click();
        summerDressesPage.confirmOrder().click();
        log.info("12. Complete the buy order process till payment");
        String summary = summerDressesPage.orderSummary().getText();

        summary = StringUtils.substringAfterLast(summary, "- Do not forget to insert your order reference ");
        summary = StringUtils.substringBeforeLast(summary, " in the subject of your bank wire.");
        String orderRef = summary;
        log.info("Order Reference in the summary = " + orderRef);

        myAccountPage myAccountPage = new myAccountPage(driver);
        myAccountPage.getUserAccountName().click();
        myAccountPage.getOrderHistory().click();

        String order = myAccountPage.getLastOrder().getText();
        log.info("Order Reference in table = " + order);
        Assert.assertEquals(order, orderRef);
        log.info("13. Make sure that Product is ordered");

    }

    @DataProvider(name = "TC06")
    public Object[][] getData() throws IOException {
        String path = prop.getProperty("excelPath");
        xlsxUtil xlsx = new xlsxUtil(path);
        int totalRows = xlsx.getRowCount(sheetName);
        int totalColumns = xlsx.getCellCount(sheetName, 1);
        String[][] data = new String[totalRows][totalColumns];

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
