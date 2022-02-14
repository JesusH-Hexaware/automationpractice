package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.surefire.shared.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.landingPage;
import pageObject.loginPage;
import pageObject.summerDressesPage;
import resources.base;
import resources.xlsxUtil;

import java.io.IOException;
import java.time.Duration;

public class test_08_verifyTotalPrice extends base {

    public WebDriver driver;
    public String sheetName = "TC08";
    public static Logger log = LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "TC08")
    public void verifyTotalPrice(String baseUrl,
                                 String userEmail,
                                 String password,
                                 String size,
                                 String quantity,
                                 String incressQuantity) {

        landingPage landingPage = new landingPage(driver);
        loginPage loginPage = new loginPage(driver);
        summerDressesPage summerDressesPage = new summerDressesPage(driver);

        driver.get(baseUrl);
        log.info("1. Open link" + baseUrl);
        landingPage.getSingIn().click();
        loginPage.getUserEmail().sendKeys(userEmail);
        loginPage.getUserPassword().sendKeys(password);
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

        action.moveToElement(summerDressesPage.getDress()).perform();
        log.info("5. Mouse hover on the second product displayed");

        action.moveToElement(summerDressesPage.getMore()).perform();
        action.click().build().perform();
        log.info("6. 'More' button will be displayed, click on 'More' button");

        summerDressesPage.setQuantity().clear();
        summerDressesPage.setQuantity().sendKeys(quantity);
        log.info("7. Make sure quantity is set to 1.");

        Select drpSize = new Select(summerDressesPage.setSize());
        drpSize.selectByVisibleText(size);
        log.info("8. Select size" + size);

        summerDressesPage.setColor().click();
        log.info("9. Select color of your choice.");

        String unitPrice = (summerDressesPage.price().getText());
        unitPrice = StringUtils.substringAfterLast(unitPrice, "$");
        log.info("Precio unitario = " + unitPrice);

        summerDressesPage.addToCart().click();
        log.info("10. Click 'Add to Cart' button");

        summerDressesPage.proceedToCheckout().click();
        log.info("11. Click 'Proceed to checkout' button");

        summerDressesPage.orderQuantity().clear();
        summerDressesPage.orderQuantity().sendKeys(incressQuantity);
        log.info("12. Change the quantity to 2.");


        int num = Integer.parseInt(incressQuantity);
        log.info("Quantity = " + num);
        float f = Float.parseFloat(unitPrice);
        log.info("Unit Price = " + f);
        float result = num * f;
        log.info("Total de la multiplicacion = " + result);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@id, 'total_product_price')][contains(text()," + result + ")]")));

        String productAmount = (summerDressesPage.productAmountTotal().getText());
        log.info("\n --------- \n" + productAmount);

        productAmount = StringUtils.substringAfterLast(productAmount, "$");


        log.info("Total = " + productAmount);

        float ff = Float.parseFloat(productAmount);
        log.info(ff);
        Assert.assertEquals(ff, result);
        log.info("13. Verify that Total price is changing and reflecting correct price.");


    }

    @DataProvider(name = "TC08")
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
