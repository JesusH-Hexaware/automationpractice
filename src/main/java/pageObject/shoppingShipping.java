package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shoppingShipping {
    public WebDriver driver;

    public shoppingShipping(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cgv")
    WebElement termsAndConditions;

    public void userAcceptsTermsAndConditions() {
        termsAndConditions.click();
    }

    @FindBy(css = "button[name='processCarrier'] span")
    WebElement shippingCheckout;

    public void userClicksProceedToCheckout() {
        shippingCheckout.click();
    }

}
