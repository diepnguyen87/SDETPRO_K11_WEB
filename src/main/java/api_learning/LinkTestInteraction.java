package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

public class LinkTestInteraction extends BaseTest {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            //Get chrome driver
            driver.get("https://the-internet.herokuapp.com/login");

            //Find Element
            WebElement powerlinkElem = driver.findElement(By.linkText("Elemental Selenium"));

            //Interaction element
            powerlinkElem.click();

            //DEBUG ONLY
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Quit driver
        driver.quit();
    }
}
