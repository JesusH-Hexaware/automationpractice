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

    By email = By.id("email");
    public WebElement getEmail(){
        return driver.findElement(email);
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

    By errorAlert = By.xpath("//div[@class='alert alert-danger']");
    public WebElement getErrorAlert(){
        return driver.findElement(errorAlert);
    }

    By errorAlertPhone = By.xpath("//li[normalize-space()='You must register at least one phone number.']");
    public WebElement getErrorAlertPhone(){
        return driver.findElement(errorAlertPhone);
    }

    By errorAlertLastname = By.xpath("//b[normalize-space()='lastname']");
    public WebElement getErrorAlertLastname(){
        return driver.findElement(errorAlertLastname);
    }

    By errorAlertFirstname = By.xpath("//b[normalize-space()='firstname']");
    public WebElement getErrorAlertFirstname(){
        return driver.findElement(errorAlertFirstname);
    }

    By errorAlertPassword = By.xpath("//b[normalize-space()='passwd']");
    public WebElement getErrorAlertPassword(){
        return driver.findElement(errorAlertPassword);
    }

    By errorAlertAddress = By.xpath("//b[normalize-space()='address1']");
    public WebElement getErrorAlertAddress(){
        return driver.findElement(errorAlertAddress);
    }

    By errorAlertCity = By.xpath("//b[normalize-space()='city']");
    public WebElement getErrorAlertCity(){
        return driver.findElement(errorAlertCity);
    }

    By errorAlertPostalcode = By.xpath("//li[contains(text(),'Zip/Postal')]");
    public WebElement getErrorAlertPostalcode(){
        return driver.findElement(errorAlertPostalcode);
    }

    By errorAlertState = By.xpath("//li[contains(text(),'country requires')]");
    public WebElement getErrorAlertState(){
        return driver.findElement(errorAlertState);
    }

    By errorAlertEmail = By.xpath("//b[normalize-space()='email']");
    public WebElement getErrorAlertEmail(){
        return driver.findElement(errorAlertEmail);
    }

    By errorAlertMobilePhoneInvalid = By.xpath("//b[normalize-space()='phone_mobile']");
    public WebElement getErrorAlertMobilePhoneInvalid(){
        return driver.findElement(errorAlertMobilePhoneInvalid);
    }

}
