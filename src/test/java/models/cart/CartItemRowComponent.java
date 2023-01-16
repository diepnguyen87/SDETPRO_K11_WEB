package models.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".cart-item-row")
public class CartItemRowComponent extends Component {

    public final By unitPriceSel = By.cssSelector(".unit-price");
    public final By quantitySel = By.cssSelector(".qty-input");
    public final By subTotalSel = By.cssSelector(".subtotal");

    public CartItemRowComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double getItemPrice(){
        return Double.parseDouble(component.findElement(unitPriceSel).getText().trim());
    }

    public int getItemQuantity(){
        return Integer.parseInt(component.findElement(quantitySel).getAttribute("value").trim());
    }

    public double getItemSubTotal(){
        return Double.parseDouble(component.findElement(subTotalSel).getText().trim());
    }
}
