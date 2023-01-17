package models.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#opc-payment_method")
public class PaymentMethodComponent extends Component {

    private final By codSel = By.cssSelector("[value='Payments.CashOnDelivery']");
    private final By checkMoneyOrderSel = By.cssSelector("[value='Payments.CheckMoneyOrder']");
    private final By creditCardSel = By.cssSelector("[value='Payments.Manual']");
    private final By purchaseOrderSel = By.cssSelector("[value='Payments.PurchaseOrder']");

    private final By continueBtnSel = By.cssSelector(".button-1.payment-method-next-step-button");

    public PaymentMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCOD(){
        findElement(codSel).click();
    }

    public void selectCheckMoneyOrder(){
        findElement(checkMoneyOrderSel).click();
    }

    public void selectCreditCard(){
        findElement(creditCardSel).click();
    }

    public void selectPurchaseOrder(){
        findElement(purchaseOrderSel).click();
    }

    public void clickOnContinueBtn(){
        findElement(continueBtnSel).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueBtnSel));
    }
}
