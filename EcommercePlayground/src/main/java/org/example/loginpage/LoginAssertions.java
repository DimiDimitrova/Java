package org.example.loginpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.enums.PageTitle;
import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.junit.jupiter.api.Assertions;

public class LoginAssertions {
    private Driver driver;

    public LoginAssertions(Driver driver) {
        this.driver = driver;
    }

    private LoginElements loginElements() {
        return new LoginElements(driver);
    }

    private BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    public void assertThatInvalidCredentialsMessageIsDisplayed(String email) {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.LOGIN.toString(),
            String.format(ApplicationMessages.LOGIN_MESSAGE, email));

        Assertions.assertEquals(loginElements().loginWarningMessage().getText(), ApplicationMessages.INVALID_CREDENTIALS_MESSAGE,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, loginElements().loginWarningMessage().getText(),
                ApplicationMessages.INVALID_CREDENTIALS_MESSAGE));
    }

    public void assertThatMessageForExceededAllowedNumberOfLoginIsDisplayed() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.LOGIN.toString(),
            ApplicationMessages.LOGGED_MESSAGE);

        Assertions.assertEquals(loginElements().loginWarningMessage().getText(),
            ApplicationMessages.ALLOWED_NUMBER_OF_LOGIN_ERROR,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, loginElements().loginWarningMessage().getText(),
                ApplicationMessages.ALLOWED_NUMBER_OF_LOGIN_ERROR));
    }
}