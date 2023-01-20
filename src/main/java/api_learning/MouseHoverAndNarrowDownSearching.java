package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import tests.BaseTest;
import url.Urls;

import java.sql.Driver;
import java.util.List;

public class MouseHoverAndNarrowDownSearching extends BaseTest implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get(baseUrl.concat(hoverSlug));

            List<WebElement> figtureSel = driver.findElements(By.className("figure"));
            if (figtureSel.isEmpty()) {
                throw new RuntimeException("There is no profile image displayed");
            }
            Actions actions = new Actions(driver);
            for (WebElement figtureElem : figtureSel) {
                WebElement profileNameSel = figtureElem.findElement(By.cssSelector(".figcaption h5"));
                WebElement profileLinkSel = figtureElem.findElement(By.cssSelector(".figcaption a"));

                //BEFORE mouse hover
                System.out.println(profileNameSel.getText() + ": " + profileNameSel.isDisplayed());
                System.out.println(profileLinkSel.getText() + ": " + profileLinkSel.isDisplayed());

                actions.moveToElement(figtureElem).perform();

                //AFTER mouse hover
                System.out.println(profileNameSel.getText() + ": " + profileNameSel.isDisplayed());
                System.out.println(profileLinkSel.getText() + ": " + profileLinkSel.isDisplayed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}