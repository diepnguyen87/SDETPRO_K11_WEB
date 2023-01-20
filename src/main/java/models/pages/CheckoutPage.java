package models.pages;

import models.checkout.*;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public BillingAddressComponent billingAddressComp(){
        return findComponent(BillingAddressComponent.class, driver);
    }

    public ConfirmOrderComponent confirmOrderComp(){
        return findComponent(ConfirmOrderComponent.class, driver);
    }

    public PaymentInformationComponent paymentInformationCom(){
        return findComponent(PaymentInformationComponent.class, driver);
    }

    public PaymentMethodComponent paymentMethodComp(){
        return findComponent(PaymentMethodComponent.class, driver);
    }

    public ShippingAddressComponent shippingAddressComp(){
        return findComponent(ShippingAddressComponent.class, driver);
    }

    public ShippingMethodComponent shippingMethodComponent(){
        return findComponent(ShippingMethodComponent.class, driver);
    }
}
