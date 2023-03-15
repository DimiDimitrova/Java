import jdk.jfr.Description;
import org.example.Generator;
import org.example.PurchaseVoucherFacade;
import org.example.accountorderpage.AccountOrderPage;
import org.example.accountpage.AccountPage;
import org.example.accountvoucherpage.AccountVoucherPage;
import org.example.changepasswordpage.ChangePasswordPage;
import org.example.checkoutpage.CheckoutPage;
import org.example.confirmpage.ConfirmPage;
import org.example.editaccountpage.EditAccountPage;
import org.example.enums.Account;
import org.example.enums.GiftCertificate;
import org.example.enums.MainMenu;
import org.example.enums.Navbar;
import org.example.homepage.HomePage;
import org.example.loginpage.LoginPage;
import org.example.registerpage.RegisterPage;
import org.example.sections.mainnavigationsection.MainNavigationSections;
import org.example.sections.myaccountdropdownsections.MyAccountDropDownSections;
import org.example.successpage.SuccessPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

public class AccountTests extends BaseTest {
    private LoginPage loginPage;
    private AccountPage accountPage;
    private HomePage homePage;
    private MainNavigationSections mainNavigationSections;
    private AccountVoucherPage accountVoucherPage;
    private AccountOrderPage accountOrderPage;
    private EditAccountPage editAccountPage;
    private SuccessPage successPage;
    private Generator generator;
    private RegisterPage registerPage;
    private ChangePasswordPage changePasswordPage;
    private MyAccountDropDownSections myAccountDropDownSection;
    private PurchaseVoucherFacade purchaseVoucherFacade;
    private CheckoutPage checkoutPage;
    private ConfirmPage confirmPage;

    public AccountTests() {
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        homePage = new HomePage(driver);
        mainNavigationSections = new MainNavigationSections(driver);
        editAccountPage = new EditAccountPage(driver);
        accountOrderPage = new AccountOrderPage(driver);
        successPage = new SuccessPage(driver);
        accountVoucherPage = new AccountVoucherPage(driver);
        registerPage = new RegisterPage(driver);
        changePasswordPage = new ChangePasswordPage(driver);
        generator = new Generator();
        myAccountDropDownSection = new MyAccountDropDownSections(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        purchaseVoucherFacade = new PurchaseVoucherFacade(mainNavigationSections, myAccountDropDownSection,
            accountVoucherPage, successPage, checkoutPage, confirmPage);
    }

    @Test
    @Description("As a registered user, I should be able to view orders history")
    public void viewOrderHistorySuccessfully() {
        var registeredUser = generator.getRegisteredUser();
        loginPage.logIn(registeredUser.getEmail(), registeredUser.getPassword());

        accountPage.openMenuFromNavbar(Navbar.ORDER_HISTORY);

        accountOrderPage.assertions().accountOrderHistoryIsDisplayed();
    }

    @Test
    @Description("Users should be able to logout successfully")
    public void logoutSuccessfully() {
        var registeredUser = generator.getRegisteredUser();
        loginPage.logIn(registeredUser.getEmail(), registeredUser.getPassword());

        accountPage.openMenuFromNavbar(Navbar.LOGOUT);

        accountPage.assertions().assertUserIsNotLogged();
    }

    @ParameterizedTest
    @EnumSource(GiftCertificate.class)
    @Description("Users should be able to add voucher to the cart")
    public void addVoucherSuccessfully(GiftCertificate gift) {
        var recipient = generator.createRecipient();
        var person = generator.getRegisteredUser();
        double amount = 50;

        loginPage.logIn(person.getEmail(), person.getPassword());

        purchaseVoucherFacade.purchaseVoucher(recipient, person.getFirstName(), gift, amount, Account.LOGIN);

        successPage.assertions().asserThatPurchaseIsMade();
    }

    @ParameterizedTest
    @ValueSource(ints = 0)
    @Description("Users should not be able to add voucher to the cart when recipient's name is less than min size")
    public void addVoucherFailed_When_RecipientNameIsLess_Than_MinSize(int nameSize) {
        var recipient = generator.createRecipientWithSpecificName(nameSize);
        var registeredUser = generator.getRegisteredUser();
        double amount = 10;

        loginPage.logIn(registeredUser.getEmail(), registeredUser.getPassword());
        mainNavigationSections.moveToMainMenu(MainMenu.MY_ACCOUNT);
        mainNavigationSections.openMenu(MainMenu.VOUCHER);

        accountVoucherPage.fillPurchaseGiftData(recipient, registeredUser.getFirstName(), GiftCertificate.BIRTHDAY, amount);
        accountVoucherPage.elements().continueButton().click();

        accountVoucherPage.assertions().assertPurchaseVoucherFailedWithIncorrectRecipientName();
    }

    @ParameterizedTest
    @ValueSource(ints = {65, 66})
    @Description("Users should not be able to add voucher to the cart when recipient's name is more than max size")
    public void addVoucherFailed_When_RecipientNameIsMore_Than_MaxSize(int nameSize) {
        var recipient = generator.createRecipientWithSpecificName(nameSize);
        var registeredUser = generator.getRegisteredUser();
        double amount = 10;

        loginPage.logIn(registeredUser.getEmail(), registeredUser.getPassword());
        mainNavigationSections.moveToMainMenu(MainMenu.MY_ACCOUNT);
        mainNavigationSections.openMenu(MainMenu.VOUCHER);

        accountVoucherPage.fillPurchaseGiftData(recipient, registeredUser.getLastName(), GiftCertificate.BIRTHDAY, amount);
        accountVoucherPage.elements().continueButton().click();

        accountVoucherPage.assertions().assertPurchaseVoucherFailedWithIncorrectRecipientName();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a12sd@", ""})
    @Description("Users should not be able to add voucher to the cart when recipient's email is incorrect")
    public void addVoucherFailed_When_RecipientEmailIsIncorrect(String endEmail) {
        var recipient = generator.createRecipientWithSpecificEmail(endEmail);
        var registeredUser = generator.getRegisteredUser();
        double amount = 10;

        loginPage.logIn(registeredUser.getEmail(), registeredUser.getPassword());
        mainNavigationSections.moveToMainMenu(MainMenu.MY_ACCOUNT);
        mainNavigationSections.openMenu(MainMenu.VOUCHER);

        accountVoucherPage.fillPurchaseGiftData(recipient, registeredUser.getFirstName(), GiftCertificate.BIRTHDAY, amount);
        accountVoucherPage.elements().continueButton().click();

        accountVoucherPage.assertions().assertPurchaseVoucherFailedWithInvalidEmail(recipient.getRecipientName());
    }

    @Test
    @Description("As a registered user, I should be able to edit information for my account")
    public void editAccountInformationSuccessfully() {
        var registeredUser = generator.getRegisteredUser();
        loginPage.logIn(registeredUser.getEmail(), registeredUser.getPassword());
        accountPage.openMenuFromNavbar(Navbar.EDIT_ACCOUNT);

        editAccountPage.fillAllAccountInformation(registeredUser);
        editAccountPage.elements().continueButton().click();

        successPage.assertions().assertAccountIsUpdated();
    }

    @Test
    @Description("As a registered user, I should be able to change password for my account")
    public void changePasswordSuccessfully() {
        var person = generator.createPersonInfo();
        String newPassword = generator.createTextWithSpecificLength(18);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);
        accountPage.openMenuFromNavbar(Navbar.LOGOUT);
        loginPage.logIn(person.getEmail(), person.getPassword());
        accountPage.openMenuFromNavbar(Navbar.PASSWORD);

        changePasswordPage.changePassword(newPassword);
        changePasswordPage.elements().continueButton().click();
        accountPage.openMenuFromNavbar(Navbar.LOGOUT);
        loginPage.logIn(person.getEmail(), newPassword);

        accountPage.assertions().assertThatUserIsLogged();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3})
    @Description("As a registered user, I should not be able to change password when it has less symbols than min size")
    public void changePasswordFailed_When_NewPasswordLengthHasLessSymbols_Than_MinSize(int passwordLength) {
        var person = generator.createPersonInfo();
        String newPassword = generator.createTextWithSpecificLength(passwordLength);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);
        accountPage.openMenuFromNavbar(Navbar.LOGOUT);
        loginPage.logIn(person.getEmail(), person.getPassword());
        accountPage.openMenuFromNavbar(Navbar.PASSWORD);

        changePasswordPage.changePassword(newPassword);
        changePasswordPage.elements().continueButton().click();

        changePasswordPage.assertions().assertChangePasswordFailedWithIncorrectPassword();
    }

    @ParameterizedTest
    @ValueSource(ints = {21, 22})
    @Description("As a registered user, I should not be able to change password when it has more symbols than max size")
    public void changePasswordFailed_When_NewPasswordLengthHasMoreSymbols_Than_MaxSize(int passwordLength) {
        var person = generator.createPersonInfo();
        String newPassword = generator.createTextWithSpecificLength(passwordLength);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);
        accountPage.openMenuFromNavbar(Navbar.LOGOUT);
        loginPage.logIn(person.getEmail(), person.getPassword());
        accountPage.openMenuFromNavbar(Navbar.PASSWORD);

        changePasswordPage.changePassword(newPassword);
        changePasswordPage.elements().continueButton().click();

        changePasswordPage.assertions().assertChangePasswordFailedWithIncorrectPassword();
    }
}