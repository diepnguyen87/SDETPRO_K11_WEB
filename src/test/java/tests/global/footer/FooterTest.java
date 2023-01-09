package tests.global.footer;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.global.FooterTestFlow;

public class FooterTest {

    @Test()
    private void testFooterLoginPage() {
        WebDriver driver = DriverFactory.getDriver();

        try{
            driver.get("https://demowebshop.tricentis.com");
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyFooterCompOnProductCat();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

    @Test
    private void testFooterRegisterPage() {

    }

    @Test()
    private void testFooterCategoryPage() {

    }

    @Test()
    private void testFooterHomePage() {

    }

}
