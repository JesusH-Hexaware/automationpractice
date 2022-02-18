package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class myAccountPage {
    public WebDriver driver;

    public myAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[@class='info-account']")
    WebElement infoAccount;

    @FindBy(xpath = "//a[@class='account']")
    WebElement accountName;

    public boolean userAccount() {
        return infoAccount.isDisplayed();
    }

    public String userAccountName() {
        return accountName.getText();
    }

    @FindBy(xpath = "//i[@class='icon-list-ol']")
    WebElement orderHistory;

    @FindBy(xpath = "//table[@id='order-list']//tr[@class='first_item ']//td[@class='history_link bold footable-first-column']")
    WebElement lastOrder;

    public void userClicksOnTheirAccount() {
        accountName.click();
    }

    public void userClicksOnOrderHistory() {
        orderHistory.click();
    }

    public String userGetsLastOrder() {
        return lastOrder.getText();
    }

}
