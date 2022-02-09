package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class createAccountPage {
    public WebDriver driver;

    public createAccountPage(WebDriver driver){
        this.driver = driver;
    }

    By firstName = By.id("customer_firstname");
    public WebElement getFirstName(){
        return driver.findElement(firstName);
    }

    By lastName = By.id("customer_lastname");
    public WebElement getlastName(){
        return driver.findElement(lastName);
    }

    By password = By.id("passwd");
    public WebElement getPassword(){
        return driver.findElement(password);
    }

    By address = By.id("address1");
    public WebElement getAddress(){
        return driver.findElement(address);
    }

    By city = By.id("city");
    public WebElement getCity(){
        return driver.findElement(city);
    }

    By state = By.id("id_state");
    public WebElement getState(){
        return driver.findElement(state);
    }

    By postcode = By.id("postcode");
    public WebElement getPostCode(){
        return driver.findElement(postcode);
    }

    By mobilePhone = By.id("phone_mobile");
    public WebElement getMobilePhone(){
        return driver.findElement(mobilePhone);
    }

    By addressAlias = By.id("alias");
    public WebElement getAddressAlias(){
        return driver.findElement(addressAlias);
    }

    By registerBtn = By.id("submitAccount");
    public WebElement getRegisterBtn(){
        return driver.findElement(registerBtn);
    }

}
