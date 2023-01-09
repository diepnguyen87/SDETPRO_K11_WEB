package test_flows.global;

import models.components.global.TopMenuComponent;

import static models.components.global.TopMenuComponent.MainMenuComponent;
import static models.components.global.TopMenuComponent.SubMenuComponent;

import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent() {
        BasePage basePage = new BasePage(driver);
        FooterComponent footerComponent = basePage.footerComp();

        verifyInformationColumn(footerComponent.informationColumnComp());
        verifyCustomerServiceColumn(footerComponent.customerServiceColumnComp());
        verifyAccountColumn(footerComponent.myAccountColumnComp());
        verifyFollowUsColumn(footerComponent.followUsColumnComp());
    }

    public void verifyFooterCompOnProductCat() {
        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComp = basePage.topMenuComp();
        List<MainMenuComponent> mainMenuComponentList = topMenuComp.getMainMenuComponentList();

        if (mainMenuComponentList.isEmpty()) {
            Assert.fail("[ERROR] - There is no item on main menu");
        }

        MainMenuComponent randomMainMenuComp = mainMenuComponentList.get(new SecureRandom().nextInt(mainMenuComponentList.size()));
        String randomCatHref = randomMainMenuComp.linkElem().getAttribute("href");
        List<SubMenuComponent> subMenuComponentList = randomMainMenuComp.getSubMenuComponentList();

        if (subMenuComponentList.isEmpty()) {
            randomMainMenuComp.linkElem().click();
        } else {
            int subMenuRandom = new SecureRandom().nextInt(subMenuComponentList.size());
            SubMenuComponent randomSubMenuComp = subMenuComponentList.get(subMenuRandom);
            randomCatHref = randomSubMenuComp.getComponent().getAttribute("href");
            randomSubMenuComp.getComponent().click();
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains(randomCatHref));
        } catch (TimeoutException e) {
            Assert.fail("[ERROR] - Timeout to wait " + randomCatHref + " is visible");
        }
        verifyFooterComponent();
    }

    private void verifyInformationColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseURL;
        List<String> expectedlinkText = Arrays.asList(
                "Sitemap", "Shipping & Returns", "Privacy Notice", "Conditions of Use", "About us", "Contact us");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/sitemap",
                baseUrl + "/shipping-returns",
                baseUrl + "/privacy-policy",
                baseUrl + "/conditions-of-use",
                baseUrl + "/about-us",
                baseUrl + "/contactus");
        testFooterColumnComponent(footerColumnComponent, expectedlinkText, expectedHrefs);
    }

    private void verifyCustomerServiceColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseURL;
        List<String> expectedlinkText = Arrays.asList(
                "Search", "News", "Blog", "Recently viewed products", "Compare products list", "New products");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/search",
                baseUrl + "/news",
                baseUrl + "/blog",
                baseUrl + "/recentlyviewedproducts",
                baseUrl + "/compareproducts",
                baseUrl + "/newproducts");
        testFooterColumnComponent(footerColumnComponent, expectedlinkText, expectedHrefs);
    }

    private void verifyAccountColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseURL;
        List<String> expectedlinkText = Arrays.asList(
                "My account", "Orders", "Addresses", "Shopping cart", "Wishlist");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/customer/info",
                baseUrl + "/customer/orders",
                baseUrl + "/customer/addresses",
                baseUrl + "/cart",
                baseUrl + "/wishlist");
        testFooterColumnComponent(footerColumnComponent, expectedlinkText, expectedHrefs);
    }

    private void verifyFollowUsColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseURL;
        List<String> expectedlinkText = Arrays.asList(
                "Facebook", "Twitter", "RSS", "YouTube", "Google+");
        List<String> expectedHrefs = Arrays.asList(
                "http://www.facebook.com/nopCommerce",
                "https://twitter.com/nopCommerce",
                baseUrl + "/news/rss/1",
                "http://www.youtube.com/user/nopCommerce",
                "https://plus.google.com/+nopcommerce");
        testFooterColumnComponent(footerColumnComponent, expectedlinkText, expectedHrefs);
    }

    public void testFooterColumnComponent(FooterColumnComponent footerColumnComponent,
                                          List<String> expectedlinkText, List<String> expectedHrefs) {
        List<String> actualLinkText = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        for (WebElement link : footerColumnComponent.linksElem()) {
            actualLinkText.add(link.getText().trim());
            actualHrefs.add(link.getAttribute("href"));
        }

        if (actualLinkText.isEmpty() || actualHrefs.isEmpty()) {
            Assert.fail("[ERROR] - actual link text and href are empty!");
        }

        Assert.assertEquals(actualLinkText, expectedlinkText, "[ERROR] actual and expected link text are difference");
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERROR] actual and expected href are difference");
    }
}
