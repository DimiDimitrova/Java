package org.example.accountvoucherpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.enums.GiftCertificate;
import org.example.models.RecipientInfo;

public class AccountVoucherPage extends BaseEShopPage {
    public AccountVoucherPage(Driver driver) {
        super(driver);
    }

    public AccountVoucherElements elements() {
        return new AccountVoucherElements(driver);
    }

    public AccountVoucherAssertions assertions() {
        return new AccountVoucherAssertions(driver);
    }

    public void fillPurchaseGiftData(RecipientInfo recipient, String fromName, GiftCertificate theme, double amount) {
        elements().recipientName().typeText(recipient.getRecipientName());
        elements().recipientMail().typeText(recipient.getRecipientEmail());
        elements().fromName().typeText(fromName);
        elements().giftCertificateTheme(theme.toString()).click();
        elements().amount().typeText(Double.toString(amount));
        elements().agreeCheckbox().click();
    }
}