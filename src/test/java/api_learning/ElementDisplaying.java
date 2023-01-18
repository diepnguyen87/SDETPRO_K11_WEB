package api_learning;

import driver.DriverFactory;
import org.testng.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tests.BaseTest;

import java.util.List;

public class ElementDisplaying extends BaseTest {

    public static void main(String[] args) {
        try {
            //Get chrome driver
            driver.get("https://the-internet.herokuapp.com/login");

            //Find Element
            List<WebElement> inputList = driver.findElements(By.tagName("input"));

            //Index
            final int USER_NAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;

            if (!inputList.isEmpty()) {
                //Interaction
                inputList.get(USER_NAME_INDEX).sendKeys("tomsmith");
                inputList.get(PASSWORD_INDEX).sendKeys("SuperSecretPassword!");
            } else {
                Assert.fail("[ERROR] CAN NOT FIND ANY ELEMENT INPUT!");
            }

            //DEBUG ONLY
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Quit driver
        driver.quit();
    }
}
