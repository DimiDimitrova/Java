package org.example.successpage;

import org.example.ApplicationMessages;
import org.example.accountpage.AccountPage;
import org.example.core.Driver;
import org.example.enums.Navbar;
import org.example.enums.PageTitle;
import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.junit.jupiter.api.Assertions;

public class SuccessAssertions {
    private Driver driver;

    public SuccessAssertions(Driver driver) {
        this.driver = driver;
    }

    private SuccessElements successElements() {
        return new SuccessElements(driver);
    }

    private AccountPage accountPage() {
        return new AccountPage(driver);
    }

    private BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    public void assertThatRegistrationIsMade() {
        Assertions.assertTrue(accountPage().elements().accountNavbar(Navbar.LOGOUT).isEnabled(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, Navbar.LOGOUT.toString()));
    }

    public void asserThatPurchaseIsMade() {
        driver.waitForAjax();
        Assertions.assertEquals(breadcrumbSection().elements().pageTitle().getText(), ApplicationMessages.ORDER_IS_MADE,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, breadcrumbSection().elements().pageTitle().getText(),
                ApplicationMessages.ORDER_IS_MADE));
    }

    public void assertVoucherIsPurchased() {
        var errorMessage = String.format(ApplicationMessages.NOT_EQUAL_ERROR, breadcrumbSection().elements().pageTitle().getText(),
            ApplicationMessages.PURCHASE_GIFT_IS_MADE);

        Assertions.assertEquals(breadcrumbSection().elements().pageTitle().getText(),
            ApplicationMessages.PURCHASE_GIFT_IS_MADE, errorMessage);
    }

    public void assertAccountIsUpdated() {
        Assertions.assertEquals(breadcrumbSection().elements().pageTitle().getText(), PageTitle.ACCOUNT.toString(),
            String.format(ApplicationMessages.PAGE_ERROR, PageTitle.ACCOUNT.toString()));

        Assertions.assertEquals(ApplicationMessages.UPDATED_ACCOUNT_MESSAGE, successElements().alertMessage().getText(),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, successElements().alertMessage().getText(),
                ApplicationMessages.UPDATED_ACCOUNT_MESSAGE));
    }
}