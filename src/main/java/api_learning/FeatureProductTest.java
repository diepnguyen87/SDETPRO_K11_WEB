package api_learning;

import driver.DriverFactory;
import models.components.product.ProductItemComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;

import java.util.List;

public class FeatureProductTest extends BaseTest {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try{
            driver.get("https://demowebshop.tricentis.com");
            testFeatureProductHomePage(driver);

        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }

    private static void testFeatureProductHomePage(WebDriver driver) {
        HomePage homePage = new HomePage(driver);
        List<ProductItemComponent> productItemList = homePage.productGridComp().productItemComp();
        productItemList.forEach(productItem -> {
            System.out.println(productItem.productTitleElem().getText());
        });
    }

}
