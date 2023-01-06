package tests.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.components.product.ProductGridComponent;
import models.components.product.ProductItemComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class FeatureProductTest {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
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
