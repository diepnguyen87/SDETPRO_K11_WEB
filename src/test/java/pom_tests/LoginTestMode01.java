package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMode01;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestMode01 implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            driver.get(baseUrl.concat(loginSlug));
            LoginPageMode01 loginPage = new LoginPageMode01(driver);

            loginPage.userName().sendKeys("tomsmith");
            loginPage.password().sendKeys("SuperSecretPassword!");
            loginPage.login().click();
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
