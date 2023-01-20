package test_flows.computer;

import models.cart.CartItemRowComponent;
import models.cart.TotalComponent;
import models.checkout.*;
import models.components.global.header.HeaderComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckoutOptionsPage;
import models.pages.CheckoutPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethodType;
import test_data.computer.ComputerData;
import test_data.user.User;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;
    private int quantity;

    public User defaultCheckoutUser;
    public PaymentMethodType paymentMethodType;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildCompSpecAndAddToCart() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        computerEssentialComp.unSelectDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        //System.out.println("processor additional price: " + processorAdditionalPrice);

        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        //System.out.println("Ram additional price: " + ramAdditionalPrice);

        String hddFullStr = computerEssentialComp.selectHDDType(computerData.getHdd());
        double hddAdditionalPrice = extractAdditionalPrice(hddFullStr);
        //System.out.println("HDD additional price: " + hddAdditionalPrice);

        double osAdditionalPrice = 0;
        if (computerData.getOs() != null) {
            String fullOSStr = computerEssentialComp.selectOSType(computerData.getOs());
            osAdditionalPrice = extractAdditionalPrice(fullOSStr);
            //System.out.println("OS additional price: " + osAdditionalPrice);
        }

        double allAdditionalPrice = processorAdditionalPrice + ramAdditionalPrice + hddAdditionalPrice + osAdditionalPrice;
        double basePrice = computerEssentialComp.productPrice();
        double totalItemPrice = (basePrice + allAdditionalPrice) * quantity;

        computerEssentialComp.clickOnaddToCartBtn();
        computerEssentialComp.waitItemAddedToCart();

        HeaderComponent headerComponent = computerItemDetailsPage.headerComp();
        headerComponent.navigateToShoppingCartPage();
        try {
            Thread.sleep(3000);
        } catch (Exception ignore) {
        }
    }

    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price;
    }

    public void verifyShoppingCart() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowList = shoppingCartPage.cartItemRowComponents();
        if (cartItemRowList.isEmpty()) {
            Assert.fail("[ERROR] - No item in shopping cart");
        }

        double currentSubtotal = 0;
        double currentTotalUnitPrices = 0;

        for (CartItemRowComponent cartItemRow : cartItemRowList) {
            double unitPrice = cartItemRow.getItemPrice();
            int itemQuantity = cartItemRow.getItemQuantity();
            currentSubtotal += cartItemRow.getItemSubTotal();
            currentTotalUnitPrices += currentTotalUnitPrices + (unitPrice * itemQuantity);
        }
        Assert.assertEquals(currentSubtotal, currentTotalUnitPrices);

        TotalComponent totalComponent = shoppingCartPage.totalComp();
        Map<String, Double> totalCompMap = totalComponent.priceCategories();
        double checkoutSubTotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;
        for (String priceType : totalCompMap.keySet()) {
            double priceValue = totalCompMap.get(priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal += priceValue;
            }
            System.out.println(priceType + ": " + totalCompMap.get(priceType));
        }

        Assert.assertEquals(checkoutSubTotal, currentSubtotal, "[ERROR] ...");
        Assert.assertEquals(checkoutTotal, (checkoutSubTotal + checkoutOtherFeesTotal), "[ERROR] ...");
    }

    public void agreeTOSAndCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComponent = shoppingCartPage.totalComp();
        totalComponent.selectTermOfServiceCheckbox();
        totalComponent.clickOnCheckoutBtn();
        new CheckoutOptionsPage(driver).checkoutAsGuest();
    }

    public void inputBillingAddress() {
        String defaultCheckoutUserJsonLoc = "/src/main/java/test_data/DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJsonLoc, User.class);

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        BillingAddressComponent billingAddressComp = checkoutPage.billingAddressComp();

        billingAddressComp.selectNewAddress();
        billingAddressComp.inputFirstName(defaultCheckoutUser.getFirstName());
        billingAddressComp.inputLastName(defaultCheckoutUser.getLastName());
        billingAddressComp.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComp.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComp.selectState(defaultCheckoutUser.getState());
        billingAddressComp.inputCity(defaultCheckoutUser.getCity());
        billingAddressComp.inputAddress1(defaultCheckoutUser.getAdd1());
        billingAddressComp.inputZipcode(defaultCheckoutUser.getZipCode());
        billingAddressComp.inputPhoneNo(defaultCheckoutUser.getPhoneNum());
        billingAddressComp.clickContinueBtn();
    }

    public void inputShippingAddress() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingAddressComponent shippingAddressComp = checkoutPage.shippingAddressComp();
        shippingAddressComp.clickOnContinueBtn();
    }

    public void selectShippingMethod() {
        List<String> shipppingMethod = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String randomShippingMethod = shipppingMethod.get(new SecureRandom().nextInt(shipppingMethod.size()));

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingMethodComponent shippingMethodComp = checkoutPage.shippingMethodComponent();
        shippingMethodComp.selectShippingMethod(randomShippingMethod);
        shippingMethodComp.clickOnContinueBtn();
    }

   /* public void selectPaymentMethod() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentMethodComponent paymentMethodComp = checkoutPage.paymentMethodComp();
        this.paymentMethodType = PaymentMethodType.COD;
        paymentMethodComp.selectCOD();
    }
*/
    public void selectPaymentMethod() {
        List<PaymentMethodType> paymentMethodTypeList = Arrays.asList(PaymentMethodType.values());
        PaymentMethodType randomPaymentMethodType = paymentMethodTypeList.get(new SecureRandom().nextInt(paymentMethodTypeList.size()));

        if (randomPaymentMethodType == null) {
            throw new IllegalArgumentException("[ERROR] Payment method type can NOT NULL");
        }
        this.paymentMethodType = randomPaymentMethodType;
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentMethodComponent paymentMethodComp = checkoutPage.paymentMethodComp();
        switch (randomPaymentMethodType) {
            case CHECK_MONEY_ORDER:
                paymentMethodComp.selectCheckMoneyOrder();
                break;
            case CREDIT_CARD:
                paymentMethodComp.selectCreditCard();
                break;
            case PURCHASE_ORDER:
                paymentMethodComp.selectPurchaseOrder();
                break;
            default:
                paymentMethodComp.selectCOD();
        }
        paymentMethodComp.clickOnContinueBtn();
    }

    public void inputPaymentInfos() {
        List<CreditCardType> creditCardTypeList = Arrays.asList(CreditCardType.values());
        CreditCardType randomCreditCardType= creditCardTypeList.get(new SecureRandom().nextInt(creditCardTypeList.size()));

        if (paymentMethodType == null) {
            throw new IllegalArgumentException("[ERROR] Payment method type can NOT NULL");
        }
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentInformationComponent paymentInformationComp = checkoutPage.paymentInformationCom();

        switch (paymentMethodType) {
            case COD:
                paymentInformationComp.getPaymentInfos();
                break;
            case CHECK_MONEY_ORDER:
                List<WebElement> actualcheckMoneyOrderParaList = paymentInformationComp.getCheckMoneyOrderParaList();
                List<String> expectedCheckMoneyOrderParaList = new ArrayList<>();
                expectedCheckMoneyOrderParaList.add("Mail Personal or Business Check, Cashier's Check or money order to:");
                expectedCheckMoneyOrderParaList.add("Tricentis GmbH\nLeonard-Bernstein-Straße 10\n1220 Vienna\nAustria");
                expectedCheckMoneyOrderParaList.add("Notice that if you pay by Personal or Business Check, your order may be held for up to 10 days after we receive your check to allow enough time for the check to clear. If you want us to ship faster upon receipt of your payment, then we recommend your send a money order or Cashier's check.");
                expectedCheckMoneyOrderParaList.add("P.S. You can edit this text from admin panel.");
                for (int i = 0; i < actualcheckMoneyOrderParaList.size(); i++) {
                    String actual = actualcheckMoneyOrderParaList.get(i).getText();
                    String expected = expectedCheckMoneyOrderParaList.get(i);
                    Assert.assertEquals(actual, expected, "[ERROR]...");
                }
                break;
            case CREDIT_CARD:
                paymentInformationComp.selectCreditCardType(randomCreditCardType);
                String cardHolderFirstName = defaultCheckoutUser.getFirstName();
                String cardHolderLastName = defaultCheckoutUser.getLastName();
                paymentInformationComp.inputCardHolderName(cardHolderFirstName + " " + cardHolderLastName);
                String cardNumber = getCardNum(randomCreditCardType);
                paymentInformationComp.inputCarNum(cardNumber);
                Calendar calendar = new GregorianCalendar();
                int currentMonth = calendar.get(Calendar.MONTH) + 1;
                int currentYear = calendar.get(Calendar.YEAR) + 1;
                paymentInformationComp.selectExpireMonth(String.format("%02d", currentMonth));
                paymentInformationComp.selectExpireYear(String.valueOf(currentYear));
                paymentInformationComp.inputCardCode("123");
                break;
            case PURCHASE_ORDER:
                paymentInformationComp.inputPurchaseOrderNumber("PO123456789");
                break;
        }
        paymentInformationComp.clickOnContinueBtn();
    }

    private String getCardNum(CreditCardType creditCardType) {
        String cardNum = "";
        switch (creditCardType) {
            case VISA:
                cardNum = "4012888888881881";
                break;
            case MASTER:
                cardNum = "5105105105105100";
                break;
            case AMEX:
                cardNum = "378282246310005";
                break;
            case DISCOVER:
                cardNum = "6011000990139424";
                break;
        }
        return cardNum;
    }

    public void confirmOrder() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ConfirmOrderComponent confirmOrderComp = checkoutPage.confirmOrderComp();
        confirmOrderComp.clickOnContinueBtn();
    }
}
