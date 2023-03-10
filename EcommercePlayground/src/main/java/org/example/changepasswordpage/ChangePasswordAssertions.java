package org.example.changepasswordpage;

import org.example.ApplicationMessages;
import org.example.accountpage.AccountPage;
import org.example.core.Driver;
import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.junit.jupiter.api.Assertions;

public class ChangePasswordAssertions {
    private Driver driver;
    private final int PASSWORD_MIN_SIZE = 4;
    private final int PASSWORD_MAX_SIZE = 20;

    public ChangePasswordAssertions(Driver driver) {
        this.driver = driver;
    }

    private BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    private AccountPage accountPage() {
        return new AccountPage(driver);
    }

    public void assertChangePasswordFailedWithIncorrectPassword() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), "Change Password",
            String.format(ApplicationMessages.UPDATED_ACCOUNT_MESSAGE));

        Assertions.assertEquals(accountPage().elements().warningMessage().getText(),
            String.format(ApplicationMessages.PASSWORD_ERROR, PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, accountPage().elements().warningMessage().getText(),
                String.format(ApplicationMessages.PASSWORD_ERROR, PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE)));
    }
}