package org.example.accountorderpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.sections.breadcrumbsection.BreadcrumbSectionElements;
import org.junit.jupiter.api.Assertions;

public class AccountOrderAssertions {
    private Driver driver;

    public AccountOrderAssertions(Driver driver) {
        this.driver = driver;
    }

    private BreadcrumbSectionElements breadcrumbSectionElements() {
        return new BreadcrumbSectionElements(driver);
    }

    private AccountOrderElements accountOrderElements() {
        return new AccountOrderElements(driver);
    }

    public void accountOrderHistoryIsDisplayed() {
        Assertions.assertEquals(breadcrumbSectionElements().pageTitle().getText(), "Order History",
            ApplicationMessages.ORDER_HISTORY_ERROR);

        try {
            Assertions.assertTrue(accountOrderElements().orderTable().isDisplayed(),
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, accountOrderElements().orderTable().getText()));
        } catch (Exception exception) {
            Assertions.assertTrue(accountOrderElements().emptyHistory().isDisplayed(),
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, accountOrderElements().emptyHistory().getText()));
        }
    }
}
