package com.hexaware.da;


//        1. Open link http://automationpractice.com/index.php
//        2. Move your cursor over Women's link.
//        3. Click on sub menu 'T-shirts'.
//        4. Mouse hover on the second product displayed.
//        5. 'Add to Wishlist' will appear on the bottom of that product, click on it.
//        6. Verify that error message is displayed 'You must be logged in to manage your wish list.'
//        3. Test Case - Verify that Total Price is reflecting correctly if user changes quantity on 'Shopping Cart Summary' Page.
//        Steps to Automate:
//        1. Open link http://automationpractice.com/index.php
//        2. Login to the website.
//        3. Move your cursor over Women's link.
//        4. Click on sub menu 'T-shirts'.
//        5. Mouse hover on the second product displayed.
//        6. 'More' button will be displayed, click on 'More' button.
//        7. Make sure quantity is set to 1.
//        8. Select size 'M'
//        9. Select color of your choice.
//        10. Click 'Add to Cart' button.
//        11. Click 'Proceed to checkout' button.
//        12. Change the quantity to 2.
//        13. Verify that Total price is changing and reflecting correct price.


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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
    public void wishList(String baseUrl) {


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
        //driver.close();
    }



}
