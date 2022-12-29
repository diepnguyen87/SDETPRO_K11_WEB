package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectEx;
import url.Urls;

public class Dropdown implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();

        try {
            driver.get(baseUrl.concat(dropdownSlug));
            By dropdownSelect = By.id("dropdown");
            WebElement dropdownElem = driver.findElement(dropdownSelect);
            SelectEx select = new SelectEx(dropdownElem);
            select.selectFirstOption();
            select.selectByIndex(2);
            select.selectByValue("1");

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
