package org.example;

import org.example.accountvoucherpage.AccountVoucherPage;
import org.example.checkoutpage.CheckoutPage;
import org.example.confirmpage.ConfirmPage;
import org.example.enums.Account;
import org.example.enums.GiftCertificate;
import org.example.enums.MainMenu;
import org.example.enums.MyAccountDropDown;
import org.example.models.RecipientInfo;
import org.example.sections.mainnavigationsection.MainNavigationSections;
import org.example.sections.myaccountdropdownsections.MyAccountDropDownSections;
import org.example.successpage.SuccessPage;

public class PurchaseVoucherFacade {
    private MainNavigationSections mainNavigationSections;
    private MyAccountDropDownSections myAccountDropDownSections;
    private AccountVoucherPage accountVoucherPage;
    private CheckoutPage checkoutPage;
    private ConfirmPage confirmPage;
    private SuccessPage successPage;

    public PurchaseVoucherFacade(MainNavigationSections mainNavigationSections, MyAccountDropDownSections myAccountDropDownSection,
                                 AccountVoucherPage accountVoucherPage, SuccessPage successPage, CheckoutPage checkoutPage,
                                 ConfirmPage confirmPage) {
        this.mainNavigationSections = mainNavigationSections;
        myAccountDropDownSections = myAccountDropDownSection;
        this.successPage = successPage;
        this.accountVoucherPage = accountVoucherPage;
        this.checkoutPage = checkoutPage;
        this.confirmPage = confirmPage;
    }

    public void purchaseVoucher(RecipientInfo recipient, String fromName, GiftCertificate gift, double amount, Account account) {
        mainNavigationSections.moveToMainMenu(MainMenu.MY_ACCOUNT);
        myAccountDropDownSections.elements().myAccountMenu(MyAccountDropDown.MY_VOUCHER).click();

        accountVoucherPage.fillPurchaseGiftData(recipient, fromName, gift, amount);
        accountVoucherPage.elements().continueButton().click();

        successPage.assertions().assertVoucherIsPurchased();

        checkoutPage.openCheckout();
        checkoutPage.checkConditions(account);
        checkoutPage.elements().continueButton().click();

        confirmPage.assertions().confirmPageIsLoaded();
        confirmPage.elements().confirmOrderButton().click();
    }
}