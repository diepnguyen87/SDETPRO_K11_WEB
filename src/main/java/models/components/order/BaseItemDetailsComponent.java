package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseItemDetailsComponent extends Component {

    public final static By productPriceSel = By.cssSelector(".product-price");
    public final static By addToCartSel = By.cssSelector(".add-to-cart-button");
    public final static By barNotificationSel = By.cssSelector("#bar-notification");

    public BaseItemDetailsComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Double productPrice(){
        String productPriceText = findElement(productPriceSel).getText().trim();
        return Double.parseDouble(productPriceText);
    }

    public void clickOnaddToCartBtn(){
        findElement(addToCartSel).click();
    }

    public void waitItemAddedToCart(){
        String notiContent = "The product has been added to your shopping cart";
        try{
            wait.until(ExpectedConditions.textToBePresentInElementLocated(barNotificationSel, notiContent));
        }catch (TimeoutException t){
            clickOnaddToCartBtn();
        }
    }
}
