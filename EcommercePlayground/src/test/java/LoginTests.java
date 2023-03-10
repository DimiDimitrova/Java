import jdk.jfr.Description;
import org.example.Generator;
import org.example.accountpage.AccountPage;
import org.example.enums.MainMenu;
import org.example.homepage.HomePage;
import org.example.loginpage.LoginPage;
import org.example.sections.mainnavigationsection.MainNavigationSections;
import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTest {
    private final int ALLOWED_NUMBER_OF_LOGIN_ATTEMPTS = 5;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private HomePage homePage;
    private MainNavigationSections mainNavigationSections;
    private Generator generator;

    public LoginTests() {
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        homePage = new HomePage(driver);
        mainNavigationSections = new MainNavigationSections(driver);
        generator = new Generator();
    }

    @Test
    @Description("Users should be able to login successfully with valid credentials")
    public void logInSuccessfully() {
        var registeredUser = generator.getRegisteredUser();
        loginPage.logIn(registeredUser.getEmail(), registeredUser.getPassword());

        accountPage.assertions().assertThatUserIsLogged();
    }

    @Test
    @Description("Users should not be able to login with wrong credentials")
    public void logInFailedWithWrongEmailCredential() {
        var data = generator.createText();
        var registeredUser = generator.getRegisteredUser();

        loginPage.logIn(data, registeredUser.getPassword());

        loginPage.assertions().assertThatInvalidCredentialsMessageIsDisplayed(data);
    }

    @Test
    @Description("Users should not be able to login without credentials")
    public void logInFailedWithEmptyCredentials() {
        driver.goToUrl(homePage.url);
        mainNavigationSections.elements().selectMenu(MainMenu.MY_ACCOUNT).click();

        loginPage.elements().loginButton().click();

        loginPage.assertions().assertThatInvalidCredentialsMessageIsDisplayed("");
        accountPage.assertions().assertThatUserIsLogged();
    }

    @Test
    @Description("Users should not be able to login when does not have account")
    public void logInFailedWithoutRegisteredAccount() {
        var data = generator.createText();

        loginPage.logIn(data, data);

        loginPage.assertions().assertThatInvalidCredentialsMessageIsDisplayed(data);
    }

    @Test
    @Description("User should not be able to login before one hour after exceeded allowed number of login attempts")
    public void logInFailed_When_TryToEnterCredentialsAfterAllowedNumberOfLoginAttemptBeforeOneHour() {
        var registeredUser = generator.getRegisteredUser();
        for (int i = 0; i < ALLOWED_NUMBER_OF_LOGIN_ATTEMPTS; i++) {
            loginPage.logIn(registeredUser.getEmail(), generator.createText());
            loginPage.assertions().assertThatInvalidCredentialsMessageIsDisplayed(registeredUser.getEmail());
        }

        loginPage.logIn(registeredUser.getEmail(), generator.createText());
        loginPage.assertions().assertThatMessageForExceededAllowedNumberOfLoginIsDisplayed();

        loginPage.logIn(registeredUser.getEmail(), registeredUser.getPassword());
        loginPage.assertions().assertThatMessageForExceededAllowedNumberOfLoginIsDisplayed();
    }
}