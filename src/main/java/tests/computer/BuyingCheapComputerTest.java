package tests.computer;

import driver.DriverFactory;
import models.components.order.CheapComputerComponent;
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

public class BuyingCheapComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "getCheapComputerData")
    public void testCheapComputerBuying(ComputerData computerData) {
        WebDriver driver = getDriver();
        driver.get(demoBaseURL.concat("/build-your-cheap-own-computer"));
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData);
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
    private static ComputerData[] getCheapComputerData() {
        String fileLocation = "/src/main/java/test_data/computer/CheapComputerDataList.json";
        ComputerData[] computerData = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerData;
    }
}
