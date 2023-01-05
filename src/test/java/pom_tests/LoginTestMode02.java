package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMode01;
import models.pages.LoginPageMode02;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestMode02 implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            driver.get(baseUrl.concat(loginSlug));
            LoginPageMode02 loginPage = new LoginPageMode02(driver);

            loginPage.inputUserName("tomsmith");
            loginPage.inputPassword("SuperSecretPassword!");
            loginPage.login();
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
