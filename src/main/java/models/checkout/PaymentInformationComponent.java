package models.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import test_data.CreditCardType;

import java.util.List;

@ComponentCssSelector("#opc-payment_info")
public class PaymentInformationComponent extends Component {

    private final By infoSel = By.cssSelector(".info p");

    private final By creditCardTypeDropdownListSel = By.id("CreditCardType");
    private final By cardHolderNameSel = By.id("CardholderName");
    private final By cardNumSel = By.id("CardNumber");
    private final By expireMonthSel = By.id("ExpireMonth");
    private final By expireYearSel = By.id("ExpireYear");
    private final By cardCodeSel = By.id("CardCode");

    private final By purchaseOrderNumSel = By.id("PurchaseOrderNumber");
    private final By continueBtnSel = By.cssSelector(".button-1.payment-info-next-step-button");

    private final By checkMoneyOrderParaListSel = By.cssSelector(".payment-info .info p");
    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void inputPurchaseOrderNumber(String purchaseOrderNum) {
        findElement(purchaseOrderNumSel).sendKeys(purchaseOrderNum);
    }

    public void clickOnContinueBtn() {
        findElement(continueBtnSel).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueBtnSel));
    }

    public void selectCreditCardType(CreditCardType creditCardType) {
        if (creditCardType == null) {
            throw new IllegalArgumentException("[ERROR] Credit card type can NOT be NULL");
        }
        WebElement creditCardTypeDropdownListElem = findElement(creditCardTypeDropdownListSel);
        Select select = new Select(creditCardTypeDropdownListElem);
        switch (creditCardType) {
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case MASTER:
                select.selectByVisibleText("Master card");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");
                break;
        }
    }

    public void inputCardHolderName(String cardHolderName) {
        findElement(cardHolderNameSel).sendKeys(cardHolderName);
    }

    public void inputCarNum(String cardNum) {
        findElement(cardNumSel).sendKeys(cardNum);
    }

    public void selectExpireMonth(String expireMonth) {
        WebElement expireMonthElem = findElement(expireMonthSel);
        Select select = new Select(expireMonthElem);
        select.selectByVisibleText(expireMonth);
    }

    public void selectExpireYear(String expireYear) {
        WebElement expireYearElem = findElement(expireYearSel);
        Select select = new Select(expireYearElem);
        select.selectByVisibleText(expireYear);
    }

    public void inputCardCode(String cardCode) {
        findElement(cardCodeSel).sendKeys(cardCode);
    }

    public String getPaymentInfos(){
        return findElement(infoSel).getText().trim();
    }

    public List<WebElement> getCheckMoneyOrderParaList(){
        return findElements(checkMoneyOrderParaListSel);
    }
}
