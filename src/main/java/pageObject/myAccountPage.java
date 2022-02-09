package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class myAccountPage {
    public WebDriver driver;

    public myAccountPage(WebDriver driver){
        this.driver = driver;
    }

    By myAccount = By.xpath("//p[@class='info-account']");
    public WebElement getMyAccount(){
        return driver.findElement(myAccount);
    }

    By userAccountName = By.xpath("//a[@class='account']");
    public WebElement getUserAccountName(){
        return driver.findElement(userAccountName);
    }

}
