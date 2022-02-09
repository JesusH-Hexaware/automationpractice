package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPage {

    public WebDriver driver;

    public loginPage(WebDriver driver){
        this.driver = driver;

    }

    By createAccountBtn = By.xpath("//button[@id='SubmitCreate']");
    public WebElement getCreateAccountBtm(){
        return driver.findElement(createAccountBtn);
    }

    By emailCreate = By.xpath("//input[@id='email_create']");
    public WebElement getEmailCreate(){
        return driver.findElement(emailCreate);
    }

}
