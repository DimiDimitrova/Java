import org.example.Generator;
import org.example.accountpage.AccountPage;
import org.example.accountvoucherpage.AccountVoucherPage;
import org.example.cartpage.CartPage;
import org.example.checkoutpage.CheckoutPage;
import org.example.confirmpage.ConfirmPage;
import org.example.enums.*;
import org.example.homepage.HomePage;
import org.example.loginpage.LoginPage;
import org.example.models.Product;
import org.example.productpage.ProductPage;
import org.example.sections.mainheadersection.MainHeaderSection;
import org.example.sections.mainnavigationsection.MainNavigationSections;
import org.example.successpage.SuccessPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class ShoppingCartTests extends BaseTest {
    private HomePage homePage;
    private ProductPage productPage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private AccountVoucherPage accountVoucherPage;
    private SuccessPage successPage;
    private AccountPage accountPage;
    private ConfirmPage confirmPage;
    private Generator generator;
    private MainHeaderSection mainHeaderSection;
    private MainNavigationSections mainNavigationSections;

    public ShoppingCartTests() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage = new LoginPage(driver);
        successPage = new SuccessPage(driver);
        accountPage = new AccountPage(driver);
        accountVoucherPage = new AccountVoucherPage(driver);
        confirmPage = new ConfirmPage(driver);
        generator = new Generator();
        mainHeaderSection = new MainHeaderSection(driver);
        mainNavigationSections = new MainNavigationSections(driver);
    }

    @Test
    public void openShoppingCartSuccessfully() {
        driver.goToUrl(homePage.url);
        mainHeaderSection.elements().mainHeaderNavigation(MainHeader.CART).click();
        driver.waitUntilPageLoadsCompletely();

        cartPage.elements().cartButton().click();

        cartPage.assertions().assertCartPageIsOpen();
    }

    @Test
    public void productInformationPresentInShoppingCart() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().imageItem(Item.PALM_TREO_PRO).click();
        var product = productPage.setProduct();
        var products = new ArrayList<Product>();
        products.add(product);
        productPage.elements().addToCartItemButton().click();

        cartPage.elements().viewCartButton().click();

        cartPage.assertions().assertProductInfo(products);
    }

    @Test
    public void updateProductQuantityInCartSuccessfully() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();

        var product = productPage.addProductToCart(Item.SONY_VAIO);
        cartPage.elements().viewCartButton().click();
        cartPage.updateProductQuantityInCart(3, product.getTitle());

        cartPage.assertions().assertQuantityAndTotalAreCorrect(3, product);
    }

    @Test
    public void removeProductSuccessfully() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();

        var product = productPage.addProductToCart(Item.SONY_VAIO);
        cartPage.elements().viewCartButton().click();
        cartPage.elements().removeButton(product.getTitle()).click();

        cartPage.assertions().assertMessageForEmptyCartIsDisplayed();
    }

    @Test
    public void addItemToShoppingCart() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        var products = new ArrayList<Product>();
        products.add(productPage.addProductToCart(Item.SONY_VAIO));

        cartPage.elements().viewCartButton().click();

        cartPage.assertions().assertProductInfo(products);
    }

    @Test
    public void continueShoppingSuccessfully() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.SONY_VAIO);
        cartPage.elements().viewCartButton().click();

        cartPage.elements().continueShoppingButton().click();

        homePage.assertions().assertHomePageIsLoaded();
    }

    @Test
    public void navigateToCheckoutSuccessfully() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.SONY_VAIO);
        cartPage.elements().viewCartButton().click();

        cartPage.elements().checkoutButton().click();

        checkoutPage.assertions().checkoutPageIsLoaded();
    }

    @ParameterizedTest
    @EnumSource(value = Country.class,names = {"BULGARIA", "UNITED_KINGDOM"})
    public void estimateShippingTaxesSuccessfully(Country country) {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.SONY_VAIO);
        cartPage.elements().viewCartButton().click();

        cartPage.elements().openAccordion(CartAccordion.ESTIMATE_SHIPPING_TAXES).click();
        cartPage.fillEstimateShippingTaxes(country, "1000");
        cartPage.elements().getQuotesButton().click();
        var flatRate = cartPage.elements().flatRateInformation().getText();
        cartPage.elements().flatRate().click();
        cartPage.elements().applyShippingButton().click();

        cartPage.assertions().assertFlatRateIsApplied(flatRate);
    }

    @Test
    public void estimateShippingTaxesFailed_When_NotFilledData() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.addItemToCart(Item.SONY_VAIO);
        cartPage.elements().viewCartButton().click();

        cartPage.elements().openAccordion(CartAccordion.ESTIMATE_SHIPPING_TAXES).click();
        cartPage.elements().getQuotesButton().click();

        cartPage.assertions().assertEstimateShippingTaxesErrorsPresent();
    }

    @ParameterizedTest
    @EnumSource(GiftCertificate.class)
    @ValueSource(doubles = {2, 3, 5})
    public void useGiftCertificateSuccessfullyLikeRegisteredUser(GiftCertificate gift, double amount) {
        var registeredUser = generator.getRegisteredUser();
        loginPage.logIn(registeredUser.getEmail(), registeredUser.getPassword());
        mainNavigationSections.moveToMainMenu(MainMenu.MY_ACCOUNT);
        mainNavigationSections.openMenu(MainMenu.VOUCHER);
        var recipient = generator.getRegisteredRecipients();

        accountVoucherPage.fillPurchaseGiftData(recipient, registeredUser.getLastName(), gift, amount);
        accountVoucherPage.elements().continueButton().click();
        successPage.assertions().assertVoucherIsPurchased();

        mainHeaderSection.elements().mainHeaderNavigation(MainHeader.CART).click();
        cartPage.elements().checkoutButtonInCart().click();
        checkoutPage.elements().termsCheckbox().click();
        checkoutPage.elements().continueButton().click();
        confirmPage.assertions().confirmPageIsLoaded();
        confirmPage.elements().confirmOrderButton().click();

        successPage.assertions().asserThatPurchaseIsMade();

        accountPage.openMenuFromNavbar(Navbar.LOGOUT);

        loginPage.logIn(recipient.getRecipientEmail(), recipient.getRecipientPassword());
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.addItemToCart(Item.SONY_VAIO);
        cartPage.elements().viewCartButton().click();

        cartPage.elements().openAccordion(CartAccordion.USE_GIFT_CERTIFICATE).click();
        cartPage.enterGiftCertificate(gift);
        cartPage.elements().applyGiftCertificate().click();

        cartPage.assertions().assertGiftCertificateIsApplied();
    }

    @Test
    public void UserHaveCorrectInformationForCartEcoTax() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.NIKON_D_300);

        cartPage.elements().viewCartButton().click();

        cartPage.assertions().assertEcoTaxIsCorrect();
    }

    @Test
    public void UserHaveCorrectInformationForCartVAT() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.PALM_TREO_PRO);

        cartPage.elements().viewCartButton().click();

        cartPage.assertions().assertVatIsCorrect();
    }

    @Test
    public void UserHaveCorrectInformationForCartTotalPrice() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.PALM_TREO_PRO);

        cartPage.elements().viewCartButton().click();

        cartPage.assertions().assertTotalPriceIsCorrect();
    }
}