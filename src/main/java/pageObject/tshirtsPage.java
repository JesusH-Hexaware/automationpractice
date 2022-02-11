package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class tshirtsPage {
    public WebDriver driver;

    public tshirtsPage(WebDriver driver){
        this.driver = driver;
    }

    By product = By.xpath("//a[normalize-space()='Faded Short Sleeve T-shirts']");
    public WebElement getProduct(){
        return driver.findElement(product);
    }

    By searchProduct = By.id("search_query_top");
    public WebElement performSearchProduct(){
        return driver.findElement(searchProduct);
    }

    By searchButton = By.name("submit_search");
    public WebElement getSearchButton(){
        return driver.findElement(searchButton);
    }

    By price = By.cssSelector("div[class='right-block'] span[class='price product-price']");
    public WebElement getPrice(){
        return driver.findElement(price);
    }

}
