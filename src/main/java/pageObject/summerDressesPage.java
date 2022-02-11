package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class summerDressesPage {
    public WebDriver driver;

    public summerDressesPage(WebDriver driver){
        this.driver = driver;
    }

    By dresses = By.xpath("//div[@class='product-image-container']");

    public WebElement getDress(){
        List<WebElement> dressesList = driver.findElements(dresses);
        Object dress = dressesList.get(1);
        return (WebElement) dress;
    }

    By moreButton = By.cssSelector("li[class='ajax_block_product col-xs-12 col-sm-6 col-md-4 last-line last-item-of-tablet-line last-mobile-line hovered'] a[title='View'] span");

    public WebElement getMore(){
        return driver.findElement(moreButton);
    }

    By quantity = By.id("quantity_wanted");

    public WebElement setQuantity(){
        return driver.findElement(quantity);
    }

    By size = By.id("group_1");

    public WebElement setSize(){
        return driver.findElement(size);
    }

    By color = By.id("color_8");

    public WebElement setColor(){
        return driver.findElement(color);
    }

    By addCart = By.id("add_to_cart");

    public WebElement addToCart(){
        return driver.findElement(addCart);
    }

    By proceedCheckout = By.xpath("//span[normalize-space()='Proceed to checkout']");

    public WebElement proceedToCheckout(){
        return driver.findElement(proceedCheckout);
    }

    By summaryCheckout = By.cssSelector("a[class='button btn btn-default standard-checkout button-medium'] span");

    public WebElement summaryCheckout(){
        return driver.findElement(summaryCheckout);
    }

    By addressCheckout = By.cssSelector("button[name='processAddress'] span");

    public WebElement addressCheckout(){
        return driver.findElement(addressCheckout);
    }

    By termsAndConditions = By.id("cgv");

    public WebElement acceptTermsAndConditions(){
        return driver.findElement(termsAndConditions);
    }

    By shippingCheckout = By.cssSelector("button[name='processCarrier'] span");

    public WebElement shippingCheckout(){
        return driver.findElement(shippingCheckout);
    }

    By paymentCheckout = By.cssSelector("a[title='Pay by bank wire']");

    public WebElement paymentCheckout(){
        return driver.findElement(paymentCheckout);
    }

    By confirmOrder = By.cssSelector("button[class='button btn btn-default button-medium'] span");

    public WebElement confirmOrder(){
        return driver.findElement(confirmOrder);
    }

    By orderSummary = By.xpath("//div[@class='box']");

    public WebElement orderSummary(){
        return driver.findElement(orderSummary);
    }

    By addWishList = By.id("wishlist_button");

    public WebElement addToWishList(){
        return driver.findElement(addWishList);
    }

    By fancyError = By.xpath("//div[@class='fancybox-inner']");

    public WebElement getError(){
        return driver.findElement(fancyError);
    }

    By addWish = By.xpath("//a[@class='addToWishlist wishlistProd_6']");

    public WebElement addWishList(){
        return driver.findElement(addWish);
    }


}
