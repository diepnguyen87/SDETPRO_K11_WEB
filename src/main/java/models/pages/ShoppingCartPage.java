package models.pages;

import models.cart.CartItemRowComponent;
import models.cart.TotalComponent;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ShoppingCartPage extends BasePage{

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartItemRowComponent> cartItemRowComponents(){
        return findComponents(CartItemRowComponent.class, driver);
    }

    public TotalComponent totalComp(){
        return findComponent(TotalComponent.class, driver);
    }
}
