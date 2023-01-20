package tests.global.footer;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_flows.global.FooterTestFlow;
import tests.BaseTest;

public class FooterTest extends BaseTest {

    @Test()
    private void testFooterLoginPage() {
        WebDriver driver = getDriver();
        driver.get("https://demowebshop.tricentis.com");
        //Assert.fail("ERROR");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterCompOnProductCat();
    }

}
