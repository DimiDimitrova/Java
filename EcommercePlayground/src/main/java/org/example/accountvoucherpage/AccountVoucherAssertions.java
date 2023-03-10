package org.example.accountvoucherpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.junit.jupiter.api.Assertions;

public class AccountVoucherAssertions {
    private Driver driver;

    private final int RECIPIENT_NAME_MIN_SIZE = 1;
    private final int RECIPIENT_NAME_MAX_SIZE = 64;

    public AccountVoucherAssertions(Driver driver) {
        this.driver = driver;
    }

    private AccountVoucherElements accountVoucherElements() {
        return new AccountVoucherElements(driver);
    }

    private BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    public void assertPurchaseVoucherFailedWithIncorrectRecipientName() {
        Assertions.assertEquals("Gift Certificate", breadcrumbSection().elements().activePageTitle().getText(),
            String.format(ApplicationMessages.RECIPIENT_NAME_ERROR, RECIPIENT_NAME_MIN_SIZE, RECIPIENT_NAME_MAX_SIZE));

        Assertions.assertEquals(accountVoucherElements().errorMessage().getText(),
            String.format(ApplicationMessages.RECIPIENT_NAME_ERROR, RECIPIENT_NAME_MIN_SIZE, RECIPIENT_NAME_MAX_SIZE));
    }

    public void assertPurchaseVoucherFailedWithIncorrectRecipientName(String name) {
        Assertions.assertEquals("Gift Certificate", breadcrumbSection().elements().activePageTitle().getText(),
            String.format(ApplicationMessages.PURCHASE_GIFT_MESSAGE, name.length()) + " " +
                String.format(ApplicationMessages.RECIPIENT_NAME_ERROR, RECIPIENT_NAME_MIN_SIZE, RECIPIENT_NAME_MAX_SIZE));

        Assertions.assertEquals(accountVoucherElements().errorMessage().getText(),
            String.format(ApplicationMessages.RECIPIENT_NAME_ERROR, RECIPIENT_NAME_MIN_SIZE, RECIPIENT_NAME_MAX_SIZE));
    }

    public void assertPurchaseVoucherFailedWithInvalidEmail(String email) {
        Assertions.assertEquals(ApplicationMessages.EMAIL_ERROR, accountVoucherElements().errorMessage().getText(),
            String.format(ApplicationMessages.PURCHASE_GIFT_MESSAGE, email.length()));
    }
}