package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".header")
public class HeaderComponent extends Component {

    private By shoppingCardLink = By.cssSelector(".cart-label");
    public HeaderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void navigateToShoppingCartPage(){
        WebElement shoppingCardLinkElem = findElement(shoppingCardLink);
        scrollUpToElement(shoppingCardLinkElem);
        shoppingCardLinkElem.click();
    }
}
