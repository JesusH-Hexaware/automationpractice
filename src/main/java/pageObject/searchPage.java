package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class searchPage {
    public WebDriver driver;

    public searchPage(WebDriver driver){
        this.driver = driver;
    }

    By searchResult = By.xpath("//span[@class='lighter']");
    public WebElement getSearchResult(){
        return driver.findElement(searchResult);
    }

    By priceResult = By.cssSelector("div[class='right-block'] span[class='price product-price']");
    public WebElement getPriceResult(){
        return driver.findElement(priceResult);
    }
}
