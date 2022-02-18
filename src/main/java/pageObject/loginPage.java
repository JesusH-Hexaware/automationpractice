package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

    public WebDriver driver;

    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='SubmitCreate']")
    WebElement CreateAccountBtn;

    @FindBy(xpath = "//input[@id='email_create']")
    WebElement EmailCreate;

    @FindBy(xpath = "//li[normalize-space()='Invalid email address.']")
    WebElement createAccountError;

    @FindBy(id = "email")
    WebElement loginEmail;

    @FindBy(id = "passwd")
    WebElement password;

    @FindBy(id = "SubmitLogin")
    WebElement signInButton;

    public boolean verifyCreateAccountBtn(){
        return CreateAccountBtn.isDisplayed();
    }

    public void userClicksOnCreateAccountBtn(){
        CreateAccountBtn.click();
    }

    public boolean verifyEmailCreateField() {
        return EmailCreate.isDisplayed();
    }

    public void userTypesAnEmail(String typesAnEmail){
        EmailCreate.sendKeys(typesAnEmail);
    }

    public boolean createAccountErrorIsDisplayed() {
        return createAccountError.isDisplayed();
    }

    public String createAccountErrorMsg(){
        return createAccountError.getText();
    }

    public  void userTypesLoginEmail(String typesLoginEmail) {
        loginEmail.sendKeys(typesLoginEmail);
    }

    public void userTypesLoginPassword(String typesLoginPassword){
        password.sendKeys(typesLoginPassword);
    }

    public void userClicksSignInButton(){
        signInButton.click();
    }

}
