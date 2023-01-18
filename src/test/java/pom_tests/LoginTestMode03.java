package pom_tests;

import driver.DriverFactory;
import models.components.LoginFormComponent;
import models.pages.LoginPageMode02;
import models.pages.LoginPageMode03;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;
import url.Urls;

public class LoginTestMode03 extends BaseTest implements Urls {

    public static void main(String[] args) {
        try {
            driver.get(baseUrl.concat(loginSlug));
            LoginPageMode03 loginPage = new LoginPageMode03(driver);
            LoginFormComponent loginFormComp = loginPage.loginFormComp();

            loginFormComp.inputUserName("tomsmith");
            loginFormComp.inputPassword("SuperSecretPassword!");
            loginFormComp.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
