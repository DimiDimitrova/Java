import jdk.jfr.Description;
import org.example.Generator;
import org.example.enums.MainMenu;
import org.example.enums.MyAccountDropDown;
import org.example.registerpage.RegisterPage;
import org.example.sections.mainnavigationsection.MainNavigationSections;
import org.example.sections.myaccountdropdownsections.MyAccountDropDownSections;
import org.example.successpage.SuccessPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RegistrationTests extends BaseTest {
    private RegisterPage registerPage;
    private SuccessPage successPage;
    private Generator generator;
    private MainNavigationSections mainNavigationSections;
    private MyAccountDropDownSections myAccountDropDownSections;

    public RegistrationTests() {
        registerPage = new RegisterPage(driver);
        successPage = new SuccessPage(driver);
        mainNavigationSections = new MainNavigationSections(driver);
        myAccountDropDownSections = new MyAccountDropDownSections(driver);
        generator = new Generator();
    }

    @Test
    @Description("Users should be able to register successfully when filling all required fields")
    public void MakeRegistrationSuccessfully() {
        var person = generator.createPersonInfo();

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);

        successPage.assertions().assertThatRegistrationIsMade();
    }

    @Test
    @Description("Users should not be able to register when using an already registered email")
    public void RegistrationFailed_When_UseAlreadyRegisteredEmail() {
        var person = generator.createPersonInfo();
        var registeredAccount = generator.getRegisteredUser();
        registerPage.doRegistration(registeredAccount.getEmail(), person.getPassword(), person);

        registerPage.assertions().assertThatRegistrationFailedWithExistEmail(registeredAccount.getEmail());
    }

    @Test
    @Description("Users should not be able to register when all required fields are empty")
    public void RegistrationFailed_When_AllFieldsAreEmpty() {
        driver.goToUrl(registerPage.url);
        mainNavigationSections.moveToMainMenu(MainMenu.MY_ACCOUNT);
        myAccountDropDownSections.elements().myAccountMenu(MyAccountDropDown.REGISTER).click();

        registerPage.elements().continueButton().click();

        registerPage.assertions().assertThatRegistrationFailedWithoutData();
    }

    @ParameterizedTest
    @ValueSource(ints = 0)
    @Description("Users should not be able to register when first name has less symbols than min size")
    public void RegistrationFailed_When_FirstNameFieldIsLessSymbols_Than_MinSize(int firstName) {
        var person = generator.createPersonWithSpecificFirstName(firstName);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);

        registerPage.assertions().assertIncorrectFirstNameValidation(person.getFirstName());
    }

    @ParameterizedTest
    @ValueSource(ints = {33, 34})
    @Description("Users should not be able to register when first name has more symbols than max size")
    public void RegistrationFailed_When_FirstNameFieldIsMoreSymbols_Than_MaxSize(int sizeFirstName) {
        var person = generator.createPersonWithSpecificFirstName(sizeFirstName);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);

        registerPage.assertions().assertIncorrectFirstNameValidation(person.getFirstName());
    }

    @ParameterizedTest
    @ValueSource(ints = {33, 34})
    @Description("Users should not be able to register when last name has more symbols than max size")
    public void RegistrationFailed_When_LastNameFieldIsMoreSymbols_Than_MaxSize(int sizeLastName) {
        var person = generator.createPersonWithSpecificLastName(sizeLastName);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);

        registerPage.assertions().assertIncorrectLastNameValidation(person.getLastName());
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    @Description("Users should not be able to register when last name has less symbols than min size")
    public void RegistrationFailed_When_LastNameFieldIsLessSymbols_Than_MinSize(int size) {
        var person = generator.createPersonWithSpecificLastName(size);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);

        registerPage.assertions().assertIncorrectLastNameValidation(person.getLastName());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @Description("Users should not be able to register when telephone has less symbols than min size")
    public void RegistrationFailed_When_TelephoneFieldIsLessSymbols_Than_MinSize(int telephoneSize) {
        var person = generator.createPersonWithSpecificTelephone(telephoneSize);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);

        registerPage.assertions().assertIncorrectPhoneValidaton(person.getTelephone());
    }

    @ParameterizedTest
    @ValueSource(ints = {33, 34, 35})
    @Description("Users should not be able to register when telephone has more symbols than max size")
    public void RegistrationFailed_When_TelephoneFieldIsMoreSymbols_Than_MaxSize(int telephoneSize) {
        var person = generator.createPersonWithSpecificTelephone(telephoneSize);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);

        registerPage.assertions().assertIncorrectPhoneValidaton(person.getTelephone());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @Description("Users should not be able to register when password has less symbols than min size")
    public void RegistrationFailed_When_PasswordFieldIsLessSymbols_Than_MinSize(int passwordSize) {
        var person = generator.createPersonWithSpecificPassword(passwordSize);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);

        registerPage.assertions().assertIncorrectPasswordValidation(person.getPassword());
    }

    @ParameterizedTest
    @ValueSource(ints = {21, 22, 23})
    @Description("Users should not be able to register when password has more symbols than max size")
    public void RegistrationFailed_When_PasswordFieldIsMoreSymbols_Than_MaxSize(int passwordSize) {
        var person = generator.createPersonWithSpecificPassword(passwordSize);

        registerPage.doRegistration(person.getEmail(), person.getPassword(), person);

        registerPage.assertions().assertIncorrectPasswordValidation(person.getPassword());
    }

    @Test
    @Description("Users should not be able to register successfully when confirm password is incorrect")
    public void RegistrationFailed_When_ConfirmPasswordIsIncorrect() {
        var person = generator.createPersonInfo();

        registerPage.doRegistration(person.getEmail(), generator.createText(), person);

        registerPage.assertions().assertIncorrectPasswordConfirmationValidation();
    }

    @Test
    public void RegistrationFailed_When_AgreeInformationIsNotChecked() {
        var person = generator.createPersonInfo();

        driver.goToUrl(registerPage.registerUrl);
        registerPage.fillRegistrationForm(person);
        registerPage.elements().continueButton().click();

        registerPage.assertions().assertThatRegistrationFailedWithoutCheckedAgree();
    }
}