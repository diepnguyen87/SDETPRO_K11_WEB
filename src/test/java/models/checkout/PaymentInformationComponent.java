package models.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector("#opc-payment_info")
public class PaymentInformationComponent extends Component {

    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
