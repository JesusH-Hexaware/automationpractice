package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class landingPage {
    public WebDriver driver;

    public landingPage(WebDriver driver){
        this.driver = driver;
    }

    By signIn = By.xpath("//a[@class='login']");
    public WebElement getSingIn(){
        return driver.findElement(signIn);
    }
}
