package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitElementEnabled implements ExpectedCondition<Boolean> {

    By parentSelector;
    By childSelector;

    public WaitElementEnabled(By parentSelector, By childSelector) {
        this.parentSelector = parentSelector;
        this.childSelector = childSelector;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        WebElement parentElem = driver.findElement(parentSelector);
        return parentElem.findElement(childSelector).isEnabled();
    }

    @Override
    public String toString() {
        return "tab numbers to be bigger than 2!";
    }
}
