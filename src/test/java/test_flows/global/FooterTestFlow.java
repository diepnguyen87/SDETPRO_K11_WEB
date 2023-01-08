package test_flows.global;

import models.components.global.footer.FooterColumnComponent;
import org.openqa.selenium.WebDriver;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent() {
        verifyInformationColumn();
        verifyCustomerServiceColumn();
        verifyAccountColumn();
        verifyFollowUsColumn();
    }

    private void verifyFollowUsColumn() {
    }

    private void verifyAccountColumn() {
    }

    private void verifyCustomerServiceColumn() {
    }

    private void verifyInformationColumn() {
    }

    public void testFooterColumnComponent(FooterColumnComponent footerColumnComponent) {
        System.out.println(footerColumnComponent.headerElem().getText());
        footerColumnComponent.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }

}
