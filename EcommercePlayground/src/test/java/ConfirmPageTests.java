import org.example.Generator;
import org.example.checkoutpage.CheckoutPage;
import org.example.confirmpage.ConfirmPage;
import org.example.enums.Account;
import org.example.enums.Brand;
import org.example.enums.Item;
import org.example.extensions.PriceConvertingExtensions;
import org.example.homepage.HomePage;
import org.example.models.PaymentAddressInfo;
import org.example.models.Product;
import org.example.productpage.ProductPage;
import org.example.successpage.SuccessPage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ConfirmPageTests extends BaseTest {
    private HomePage homePage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private ConfirmPage confirmPage;
    private SuccessPage successPage;
    private Generator generator;

    public ConfirmPageTests() {
        successPage = new SuccessPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        generator = new Generator();
    }

    @Test
    public void ProductInformationIsInConfirmPageCorrectly() {
        var person = generator.getRegisteredUser();
        var paymentAddress = new PaymentAddressInfo();

        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().imageItem(Item.MAC_BOOK_PRO).click();
        var product = productPage.setProduct();
        var products = new ArrayList<Product>();
        products.add(product);
        productPage.elements().buyNowButton().click();

        checkoutPage.fillAccountDetails(person, paymentAddress, Account.LOGIN);
        checkoutPage.continuePurchase();

        confirmPage.assertions().assertProductContentIsCorrect(products);
    }

    @Test
    public void TotalSumIsCorrect() {
        var person = generator.getRegisteredUser();
        var paymentAddress = new PaymentAddressInfo();

        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().imageItem(Item.MAC_BOOK_PRO).click();
        productPage.elements().buyNowButton().click();

        var totalInCheckout = PriceConvertingExtensions.getAmount(checkoutPage.elements().total().getText());
        checkoutPage.fillAccountDetails(person, paymentAddress, Account.LOGIN);
        checkoutPage.assertions().assertTotalPriceIsCorrect();
        checkoutPage.continuePurchase();

        confirmPage.assertions().assertTotalSum(totalInCheckout);
    }

    @Test
    public void PaymentAddressIsCorrect() {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentAddress();

        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().imageItem(Item.MAC_BOOK_PRO).click();
        productPage.elements().buyNowButton().click();

        checkoutPage.fillAccountDetails(person, paymentAddress, Account.GUEST_ACCOUNT);
        checkoutPage.continuePurchase();

        confirmPage.assertions().assertPaymentInfo(paymentAddress);
    }

    @Test
    public void EditOrderSuccessfully() {
        var person = generator.getRegisteredUser();
        var paymentAddress = new PaymentAddressInfo();

        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().imageItem(Item.MAC_BOOK_PRO).click();
        productPage.elements().buyNowButton().click();
        checkoutPage.fillAccountDetails(person, paymentAddress, Account.LOGIN);
        checkoutPage.continuePurchase();

        confirmPage.assertions().confirmPageIsLoaded();
        confirmPage.elements().editButton().click();

        checkoutPage.assertions().checkoutPageIsLoaded();

        checkoutPage.updateProductQuantity(10, Item.MAC_BOOK_PRO);
        checkoutPage.elements().termsCheckbox().click();
        checkoutPage.continuePurchase();

        confirmPage.assertions().assertProductQuantityIsEdited(10, Item.MAC_BOOK_PRO);
    }

    @Test
    public void ConfirmOrderSuccessfully() {
        var person = generator.getRegisteredUser();
        var paymentAddress = new PaymentAddressInfo();

        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().imageItem(Item.MAC_BOOK_PRO).click();
        productPage.elements().buyNowButton().click();

        checkoutPage.fillAccountDetails(person, paymentAddress, Account.LOGIN);
        checkoutPage.continuePurchase();
        confirmPage.elements().confirmOrderButton().click();

        successPage.assertions().asserThatPurchaseIsMade();
    }

    @Test
    public void ContinueSuccessfullyAfterConfirmPurchase() {
        var person = generator.getRegisteredUser();
        var paymentAddress = new PaymentAddressInfo();

        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().imageItem(Item.MAC_BOOK_PRO).click();
        productPage.elements().buyNowButton().click();

        checkoutPage.fillAccountDetails(person, paymentAddress, Account.LOGIN);
        checkoutPage.continuePurchase();
        confirmPage.elements().confirmOrderButton().click();

        successPage.assertions().asserThatPurchaseIsMade();
        successPage.elements().continueButton().click();
        homePage.assertions().assertHomePageIsLoaded();
    }
}