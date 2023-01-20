package models.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCssSelector(".cart-footer .totals")
public class TotalComponent extends Component {

    private final By priceTableRowSel = By.tagName("tr");
    private final By priceTypeSel = By.cssSelector(".cart-total-left");
    private final By priceValueSel = By.cssSelector(".cart-total-right");
    private final By tosSel = By.id("termsofservice");
    private final By checkoutSel = By.id("checkout");

    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Map<String, Double> priceCategories(){
        Map<String, Double> priceCategories = new HashMap<>();
        List<WebElement> rowsElem = component.findElements(priceTableRowSel);
        rowsElem.forEach(row -> {
            String type = row.findElement(priceTypeSel).getText().trim().replace(":", "");
            Double value = Double.parseDouble(row.findElement(priceValueSel).getText().trim());
            priceCategories.put(type, value);
        });
        return priceCategories;
    }

    public void selectTermOfServiceCheckbox(){
        component.findElement(tosSel).click();
    }

    public void clickOnCheckoutBtn(){
        component.findElement(checkoutSel).click();
    }
}
