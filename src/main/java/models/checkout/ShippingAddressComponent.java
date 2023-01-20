package models.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#opc-shipping")
public class ShippingAddressComponent extends Component {

    private final By continueBtnSel = By.cssSelector(".button-1.new-address-next-step-button");

    public ShippingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnContinueBtn(){
        findElement(continueBtnSel).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueBtnSel));
    }
}
