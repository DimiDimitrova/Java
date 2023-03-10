import org.example.Generator;
import org.example.PurchaseFacade;
import org.example.cartpage.CartPage;
import org.example.checkoutpage.CheckoutPage;
import org.example.confirmpage.ConfirmPage;
import org.example.enums.Account;
import org.example.enums.CategoryInSearchBox;
import org.example.enums.Item;
import org.example.homepage.HomePage;
import org.example.models.Product;
import org.example.productpage.ProductPage;
import org.example.successpage.SuccessPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class CheckoutTests extends BaseTest {
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private ConfirmPage confirmPage;
    private SuccessPage successPage;
    private HomePage homePage;
    private CartPage cartPage;
    private Generator generator;
    private PurchaseFacade purchaseFacade;

    public CheckoutTests() {
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        successPage = new SuccessPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        generator = new Generator();
        purchaseFacade = new PurchaseFacade(homePage, cartPage, productPage, checkoutPage, confirmPage, successPage);
    }

    @Test
    public void MadePurchaseSuccessfullyWithLoginOption() {
        var person = generator.getRegisteredUser();
        var paymentAddress = generator.createPaymentAddress();
        purchaseFacade.purchaseItem(Item.PALM_TREO_PRO, person, paymentAddress, Account.LOGIN);
    }

    @Test
    public void MakePurchaseSuccessfullyLikeGuest() {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentAddress();
        purchaseFacade.purchaseItem(Item.PALM_TREO_PRO, person, paymentAddress, Account.GUEST_ACCOUNT);
    }

    @Test
    public void MakePurchaseSuccessfullyLikeNewUser() {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseItem(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    public void MadePurchaseWithRegisterAccountFailed_When_FirstNameHasLessSymbols_Than_MinSize(int firstNameSize) {
        var person = generator.createPersonWithSpecificFirstName(firstNameSize);
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectFirstNameValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {33, 34, 45})
    public void MadePurchaseWithRegisterAccountFailed_When_FirstNameHasMoreSymbols_Than_MaxSize(int sizeFirstName) {
        var person = generator.createPersonWithSpecificFirstName(sizeFirstName);
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectFirstNameValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    public void MakePurchaseWithRegisterAccountFailed_When_LastNameHasLessSymbols_Than_MinSize(int lastNameSize) {
        var person = generator.createPersonWithSpecificLastName(lastNameSize);
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectLastNameValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {34, 33})
    public void MakePurchaseWithRegisterAccountFailed_When_LastNameHasMoreSymbols_Than_MaxSize(int sizeLastName) {
        var person = generator.createPersonWithSpecificLastName(sizeLastName);
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.NIKON_D_300, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectLastNameValidation();
    }

    @Test
    public void MakePurchaseWithRegisterAccountFailed_When_UseAlreadyRegisteredEmail() {
        var person = generator.createPersonInfo();
        var registeredUser = generator.getRegisteredUser();
        person.setEmail(registeredUser.getEmail());
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertOrderFailedWithExistEmail();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void MakePurchaseWithRegisterAccountFailed_When_TelephoneSymbolsAreLess_Than_MinSize(int telephoneSize) {
        var person = generator.createPersonWithSpecificTelephone(telephoneSize);
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.IPOD_NANO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectPhoneValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {33, 34, 35})
    public void MakePurchaseWithRegisterAccountFailed_When_TelephoneSymbolsAreMore_Than_MaxSize(int telephoneSize) {
        var person = generator.createPersonWithSpecificTelephone(telephoneSize);
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.IPOD_NANO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectPhoneValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    public void MakePurchaseWithRegisterAccountFailed_When_PasswordSymbolsAreLess_Than_MinSize(int password) {
        var person = generator.createPersonWithSpecificPassword(password);
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectPasswordValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {23, 22})
    public void MakePurchaseWithRegisterAccountFailed_When_PasswordSymbolsAreMore_Than_MaxSize(int password) {
        var person = generator.createPersonWithSpecificPassword(password);
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectPasswordValidation();
    }

    @Test
    public void MakePurchaseFailedWithRegisterAccount_When_ConfirmPasswordIsIncorrect() {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentAddress();

        // purchaseFacade.PurchaseValidation(Item.IPOD_NANO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertPasswordsMismatchValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void MakePurchaseFailedWithRegisterAccount_When_AddressHasSymbolsLess_Than_MinSize(int addressSize) {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentWithSpecificAddress(addressSize);

        purchaseFacade.purchaseValidation(Item.IPOD_SHUFFLE, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectAddressValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {129, 130, 200})
    public void MakePurchaseFailedWithRegisterAccount_When_AddressHasSymbolsMore_Than_MaxSize(int addressSize) {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentWithSpecificAddress(addressSize);

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectAddressValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void MakePurchaseFailedWithRegisterAccount_When_CityHasSymbolsLess_Than_MinSize(int citySize) {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentWithSpecificCity(citySize);

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectCityValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {129, 130})
    public void MakePurchaseFailedWithRegisterAccount_When_CityHasSymbolsMore_Than_MaxSize(int citySize) {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentWithSpecificCity(citySize);

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectCityValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void MakePurchaseFailedWithRegisterAccount_When_PostCodeHasSymbolsLess_Than_MinSize(int postCodeSize) {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentWithSpecificPostCode(postCodeSize);

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectPostCodeValidation();
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 12})
    public void MakePurchaseFailedWithRegisterAccount_When_PostCodeHasSymbolsMore_Than_MaxSize(int postCodeSize) {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentWithSpecificPostCode(postCodeSize);

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectPostCodeValidation();
    }

    @Test
    public void MakePurchaseFailedWithRegisterAccount_When_Country_Is_PleaseSelect() {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentWithSelectCountryOption();

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertIncorrectCountryValidation();
    }

    @Test
    public void MakePurchaseFailedWhenConditionsAreNotCheck() {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentAddress();

        purchaseFacade.purchaseValidation(Item.PALM_TREO_PRO, person, paymentAddress, Account.REGISTER_ACCOUNT);

        checkoutPage.assertions().assertTermConditionsRequiredAgreementValidation();
    }

    @Test
    public void MakePurchaseFailedWithRegisterAccount_When_ConfirmPasswordIsEmpty() {
        var person = generator.createPersonInfo();
        var paymentAddress = generator.createPaymentAddress();

        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.IPOD_NANO);
        cartPage.elements().checkoutButton().click();

        checkoutPage.fillAccountDetails(person, paymentAddress, Account.REGISTER_ACCOUNT);
        checkoutPage.fillPasswordFields(person.getPassword(), "");
        checkoutPage.continuePurchase();

        checkoutPage.assertions().assertEmptyConfirmPasswordValidation();
    }

    @Test
    public void UpdateProductQuantityInCheckoutSuccessfully() {
        var product = Item.IPOD_NANO;
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(product);
        cartPage.elements().checkoutButton().click();

        checkoutPage.updateProductQuantity(3, product);
        checkoutPage.elements().updateButton(product).click();

        checkoutPage.assertions().assertQuantityAndTotalAreCorrect(3, product);
    }

    @Test
    public void RemoveProductSuccessfully() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.PALM_TREO_PRO);

        cartPage.elements().checkoutButton().click();
        driver.waitForAjax();
        checkoutPage.elements().removeButton(Item.PALM_TREO_PRO).click();

        cartPage.assertions().assertShoppingCartIsEmpty();
    }

    @Test
    public void UserHaveCorrectInformationForCheckoutEcoTax() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.HTC_TOUCH_HD);

        cartPage.elements().checkoutButton().click();

        checkoutPage.assertions().assertEcoTaxIsCorrect();
    }

    @Test
    public void UserHaveCorrectInformationForCheckoutVAT() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.IPOD_NANO);

        cartPage.elements().checkoutButton().click();

        checkoutPage.assertions().assertVatIsCorrect();
    }

    @Test
    public void UserHaveCorrectInformationForCheckoutTotalPrice() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.elements().listViewButton().click();
        productPage.addItemToCart(Item.IPOD_NANO);

        cartPage.elements().checkoutButton().click();

        checkoutPage.assertions().assertTotalPriceIsCorrect();
    }

    @Test
    public void AddedProductPresentOnThePage() {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        var product = productPage.addProductToCart(Item.PALM_TREO_PRO);
        var products = new ArrayList<Product>();
        products.add(product);

        cartPage.elements().checkoutButton().click();

        checkoutPage.assertions().assertProductsInfo(products);
    }
}