package models.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@ComponentCssSelector("#opc-billing")
public class BillingAddressComponent extends Component {

    private final By addressDropDownSel = By.id("billing-address-select");
    private final By firstNameSel = By.id("BillingNewAddress_FirstName");
    private final By lastNameSel = By.id("BillingNewAddress_LastName");
    private final By emailSel = By.id("BillingNewAddress_Email");
    private final By countrySel = By.id("BillingNewAddress_CountryId");
    private final By stateLoadingProgress = By.id("states-loading-progress");
    private final By stateSel = By.id("BillingNewAddress_StateProvinceId");
    private final By citySel = By.id("BillingNewAddress_City");
    private final By address1Sel = By.id("BillingNewAddress_Address1");
    private final By zipCodeSel = By.id("BillingNewAddress_ZipPostalCode");
    private final By phoneNoSel = By.id("BillingNewAddress_PhoneNumber");
    private final By continueSel = By.cssSelector(".button-1.new-address-next-step-button");

    public BillingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectNewAddress(){
        List<WebElement> addressDropDownElem = findElements(addressDropDownSel);
        if(!addressDropDownElem.isEmpty()){
            Select select = new Select(findElement(addressDropDownSel));
            select.selectByVisibleText("New Address");
        }
    }
    public void inputFirstName(String firstName){
        findElement(firstNameSel).sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        findElement(lastNameSel).sendKeys(lastName);
    }

    public void inputEmail(String email){
        findElement(emailSel).sendKeys(email);
    }

    public void selectCountry(String selectedCountry){
        WebElement countriesListElem = findElement(countrySel);
        Select select = new Select(countriesListElem);
        select.selectByVisibleText(selectedCountry);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(stateLoadingProgress));
    }

    public void selectState(String selectedState){
        WebElement statesListElem = findElement(stateSel);
        Select select = new Select(statesListElem);
        select.selectByVisibleText(selectedState);
    }

    public void inputCity(String city){
        findElement(citySel).sendKeys(city);
    }
    public void inputAddress1(String address1){
        findElement(address1Sel).sendKeys(address1);
    }

    public void inputZipcode(String zipcode){
        findElement(zipCodeSel).sendKeys(zipcode);
    }

    public void inputPhoneNo(String phoneNo){
        findElement(phoneNoSel).sendKeys(phoneNo);
    }

    public void clickContinueBtn(){
        findElement(continueSel).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueSel));
    }
}
