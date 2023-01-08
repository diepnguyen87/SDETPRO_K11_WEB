package tests.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import support.verification.Verifier;
import test_flows.global.FooterTestFlow;

public class FooterTest {

    @Test(priority = 1, dependsOnMethods = {"testFooterRegisterPage"})
    private void testFooterLoginPage() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demowebshop.tricentis.com");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test(priority = 2)
    private void testFooterRegisterPage() {
        //throw new RuntimeException("Run failed");
        String actualResult = "Ti";
        String expectedResult = "Teo";
        //Verifier.verifyEqual(actualResult, expectedResult);
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertTrue(actualResult.equals(expectedResult), "...");
        Assert.assertFalse(actualResult.equals(expectedResult), "...");
        Assert.fail();
        Assert.fail("...");
    }

    @Test(priority = 3)
    private void testFooterCategoryPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1, 2);
        softAssert.assertEquals(true, false);
        softAssert.assertEquals(2, 3);
        softAssert.assertAll();
        System.out.println("Hello");
    }

    @Test(priority = 4)
    private void testFooterHomePage() {
        WebDriver driver = DriverFactory.getDriver();
        try{
            driver.get("https://demowebshop.tricentis.com");
            HomePage homePage = new HomePage(driver);
            InformationColumnComponent informationColumnComp = homePage.footerComp().informationColumnComp();
            CustomerServiceColumnComponent customerServiceColumnComp = homePage.footerComp().customerServiceColumnComp();

            testFooterColumnComponent(informationColumnComp);
            testFooterColumnComponent(customerServiceColumnComp);
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }

    public void testFooterColumnComponent(FooterColumnComponent footerColumnComponent){
        System.out.println(footerColumnComponent.headerElem().getText());
        footerColumnComponent.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }
}
