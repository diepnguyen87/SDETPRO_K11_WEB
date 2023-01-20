package models.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#opc-shipping_method")
public class ShippingMethodComponent extends Component {

    private final By continueBtnSel = By.cssSelector(".button-1.shipping-method-next-step-button");

    public ShippingMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectShippingMethod(String method) {
        WebElement shippingMethodElem = findElement(By.xpath("//label[contains(text(), '" + method + "')]"));
        shippingMethodElem.click();
    }

    public void clickOnContinueBtn() {
        findElement(continueBtnSel).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueBtnSel));
    }
}
