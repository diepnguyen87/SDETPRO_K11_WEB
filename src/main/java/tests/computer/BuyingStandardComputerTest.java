package tests.computer;

import models.components.order.StandardComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethodType;
import test_data.computer.ComputerData;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "getStandardComputerData")
    public void testStandardComputerBuying(ComputerData computerData) {
        WebDriver driver = getDriver();
        driver.get(demoBaseURL.concat("/build-your-own-computer"));
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData);
        orderComputerFlow.buildCompSpecAndAddToCart();
        orderComputerFlow.verifyShoppingCart();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod();
        orderComputerFlow.inputPaymentInfos();
        orderComputerFlow.confirmOrder();
    }

    @DataProvider()
    private static ComputerData[] getStandardComputerData() {
        String fileLocation = "/src/main/java/test_data/computer/StandardComputerDataList.json";
        ComputerData[] computerData = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerData;
    }
}
