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
import pageObject.searchPage;
import pageObject.tshirtsPage;
import resources.base;
import resources.xlsxUtil;

import java.io.IOException;

public class test_05_searchProduct extends base{
    public WebDriver driver;
    public String sheetName = "TC05";
    public static Logger log = (Logger) LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "TC05")
    public void mouseOver(String baseUrl){

        landingPage landingPage = new landingPage(driver);
        tshirtsPage tshirtsPage = new tshirtsPage(driver);
        searchPage searchPage = new searchPage(driver);


        driver.get(baseUrl);

        Actions action = new Actions(driver);
        WebElement womenSection = landingPage.getWomenSection();
        action.moveToElement(womenSection).perform();
        WebElement womenTshirts = landingPage.getWomenTshirts();
        action.moveToElement(womenTshirts).perform();
        action.click().build().perform();

        String productName = (tshirtsPage.getProduct().getText().toUpperCase());
        String price = tshirtsPage.getPrice().getText();
        tshirtsPage.performSearchProduct().sendKeys(productName);
        tshirtsPage.getSearchButton().click();

        String searchedProduct = (searchPage.getSearchResult().getText());
        String searchedPrice = (searchPage.getPriceResult().getText());

        StringBuilder sb = new StringBuilder("\"");
        productName = (sb + productName + sb);

        Assert.assertEquals(productName, searchedProduct);
        Assert.assertEquals(price, searchedPrice);



    }

    @DataProvider(name = "TC05")
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
