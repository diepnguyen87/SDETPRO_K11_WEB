package tests.computer;

import models.components.order.StandardComputerComponent;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test
    public void testStandardComputerBuying() {
        driver.get(demoBaseURL.concat("/build-your-own-computer"));
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver, StandardComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCart();
    }

}
