package org.example.registerpage;

import org.example.ApplicationMessages;
import org.example.accountpage.AccountPage;
import org.example.core.Driver;
import org.example.enums.PageTitle;
import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.junit.jupiter.api.Assertions;

public class RegisterAssertions {
    private Driver driver;
    private final int PASSWORD_MIN_SIZE = 4;
    private final int PASSWORD_MAX_SIZE = 20;
    private final int FIRST_NAME_MIN_SIZE = 1;
    private final int FIRST_NAME_MAX_SIZE = 32;
    private final int LAST_NAME_MIN_SIZE = 1;
    private final int LAST_NAME_MAX_SIZE = 32;
    private final int TELEPHONE_MIN_SIZE = 3;
    private final int TELEPHONE_MAX_SIZE = 32;

    public RegisterAssertions(Driver driver) {
        this.driver = driver;
    }

    private RegisterElements registerElements() {
        return new RegisterElements(driver);
    }

    private BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    private AccountPage accountPage() {
        return new AccountPage(driver);
    }

    public void assertThatRegistrationFailedWithExistEmail(String email) {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.REGISTER.toString(),
            String.format(ApplicationMessages.ACCOUNT_CREATED_WITH_EXISTS_EMAIL, email));

        Assertions.assertTrue(registerElements().emailWarningMessage().isDisplayed());
    }

    public void assertThatRegistrationFailedWithoutData() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.REGISTER.toString(),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, breadcrumbSection().elements().activePageTitle().getText(),
                PageTitle.REGISTER.toString()));

        Assertions.assertTrue(registerElements().emailWarningMessage().isDisplayed());
    }

    public void assertIncorrectPasswordValidation(String password) {
        Assertions.assertEquals(PageTitle.REGISTER.toString(), breadcrumbSection().elements().activePageTitle().getText(),
            String.format(ApplicationMessages.ACCOUNT_HAS_BEEN_CREATED, password.length()) + " " +
                String.format(ApplicationMessages.PASSWORD_ERROR, PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE));

        Assertions.assertEquals(accountPage().elements().warningMessage().getText(),
            String.format(ApplicationMessages.PASSWORD_ERROR, PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, accountPage().elements().warningMessage().getText(),
                String.format(ApplicationMessages.PASSWORD_ERROR, PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE)));
    }

    public void assertIncorrectFirstNameValidation(String firstName) {
        Assertions.assertEquals(PageTitle.REGISTER.toString(), breadcrumbSection().elements().activePageTitle().getText(),
            String.format(ApplicationMessages.ACCOUNT_HAS_BEEN_CREATED, firstName.length()) + " " +
                String.format(ApplicationMessages.FIRST_NAME_ERROR, FIRST_NAME_MIN_SIZE, FIRST_NAME_MAX_SIZE));

        Assertions.assertEquals(accountPage().elements().warningMessage().getText(),
            String.format(ApplicationMessages.FIRST_NAME_ERROR, FIRST_NAME_MIN_SIZE, FIRST_NAME_MAX_SIZE),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, accountPage().elements().warningMessage().getText(),
                String.format(ApplicationMessages.FIRST_NAME_ERROR, FIRST_NAME_MIN_SIZE, FIRST_NAME_MAX_SIZE)));
    }

    public void assertIncorrectLastNameValidation(String lastName) {
        Assertions.assertEquals(PageTitle.REGISTER.toString(), breadcrumbSection().elements().activePageTitle().getText(),
            String.format(ApplicationMessages.ACCOUNT_HAS_BEEN_CREATED, lastName.length()) + " " +
                String.format(ApplicationMessages.LAST_NAME_ERROR, LAST_NAME_MIN_SIZE, LAST_NAME_MAX_SIZE));

        Assertions.assertEquals(accountPage().elements().warningMessage().getText(),
            String.format(ApplicationMessages.LAST_NAME_ERROR, LAST_NAME_MIN_SIZE, LAST_NAME_MAX_SIZE),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, accountPage().elements().warningMessage().getText(),
                String.format(ApplicationMessages.LAST_NAME_ERROR, LAST_NAME_MIN_SIZE, LAST_NAME_MAX_SIZE)));
    }

    public void assertIncorrectPhoneValidaton(String telephone) {
        Assertions.assertEquals(PageTitle.REGISTER.toString(), breadcrumbSection().elements().activePageTitle().getText(),
            String.format(ApplicationMessages.ACCOUNT_HAS_BEEN_CREATED, telephone.length()) + " " +
                String.format(ApplicationMessages.TELEPHONE_ERROR, TELEPHONE_MIN_SIZE, TELEPHONE_MAX_SIZE));

        Assertions.assertEquals(accountPage().elements().warningMessage().getText(),
            String.format(ApplicationMessages.TELEPHONE_ERROR, TELEPHONE_MIN_SIZE, TELEPHONE_MAX_SIZE),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, accountPage().elements().warningMessage().getText(),
                String.format(ApplicationMessages.TELEPHONE_ERROR, TELEPHONE_MIN_SIZE, TELEPHONE_MAX_SIZE)));
    }

    public void assertIncorrectPasswordConfirmationValidation() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.REGISTER.toString(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, PageTitle.REGISTER.toString()));

        Assertions.assertEquals(accountPage().elements().warningMessage().getText(), ApplicationMessages.PASSWORD_CONFIRMATION_ERROR,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, accountPage().elements().warningMessage().getText(),
                ApplicationMessages.PASSWORD_CONFIRMATION_ERROR));
    }

    public void assertThatRegistrationFailedWithoutCheckedAgree() {
        Assertions.assertTrue(registerElements().agreeWithPrivacyMessage().isDisplayed(),
            ApplicationMessages.ACCOUNT_HAS_BEEN_CREATED_WITHOUT_AGREE_INFORMATION);

        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.REGISTER.toString(),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, breadcrumbSection().elements().activePageTitle().getText(),
                PageTitle.REGISTER.toString()));
    }
}