package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOptionsPage extends BasePage{

    private final By guestCheckoutSel = By.cssSelector(".button-1.checkout-as-guest-button");
    public CheckoutOptionsPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutAsGuest(){
        component.findElement(guestCheckoutSel).click();
    }
}
