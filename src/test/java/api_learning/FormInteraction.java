package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

public class FormInteraction extends BaseTest {

    public static void main(String[] args) {
        try {
            //Get chrome driver
            driver.get("https://the-internet.herokuapp.com/login");

            //Find Element
            WebElement userNameElem = driver.findElement(By.id("username"));
            WebElement passwordElem = driver.findElement(By.cssSelector("#password"));
            WebElement loginBtnElem = driver.findElement(By.cssSelector("[type='Submit']"));

            //Get attribute
            System.out.println(loginBtnElem.getAttribute("type"));
            System.out.println(loginBtnElem.getCssValue("background-color"));

            //Interaction
            userNameElem.sendKeys("tomsmith");
            passwordElem.sendKeys("SuperSecretPassword!");
            loginBtnElem.click();

            //get page title
            System.out.println(driver.getTitle());

            //get current url
            System.out.println(driver.getCurrentUrl());

            //Back to previous page and refresh
            driver.navigate().back();
            driver.navigate().refresh();

            //DEBUG ONLY
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Quit driver
        driver.quit();
    }
}
