package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMode01;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;
import url.Urls;

public class LoginTestMode01 extends BaseTest implements Urls {

    public static void main(String[] args) {
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
