package org.example;

import org.example.cartpage.CartPage;
import org.example.checkoutpage.CheckoutPage;
import org.example.confirmpage.ConfirmPage;
import org.example.enums.Account;
import org.example.enums.CategoryInSearchBox;
import org.example.enums.Item;
import org.example.homepage.HomePage;
import org.example.models.PaymentAddressInfo;
import org.example.models.PersonInfo;
import org.example.productpage.ProductPage;
import org.example.successpage.SuccessPage;

public class PurchaseFacade {
    private HomePage homePage;
    private CartPage cartPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private ConfirmPage confirmPage;
    private SuccessPage successPage;

    public PurchaseFacade(HomePage homePage, CartPage cartPage, ProductPage productPage,
                          CheckoutPage checkoutPage, ConfirmPage confirmPage, SuccessPage successPage) {
        this.homePage = homePage;
        this.cartPage = cartPage;
        this.productPage = productPage;
        this.checkoutPage = checkoutPage;
        this.confirmPage = confirmPage;
        this.successPage = successPage;
    }

    public void purchaseItem(Item product, PersonInfo person, PaymentAddressInfo paymentAddress, Account account) {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.addItemToCart(product);
        cartPage.elements().checkoutButton().click();

        checkoutPage.assertions().assertTotalPriceIsCorrect();

        checkoutPage.fillAccountDetails(person, paymentAddress, account);
        checkoutPage.continuePurchase();

        confirmPage.assertions().assertTotalSum(checkoutPage.totalPrice);
        confirmPage.assertions().assertPaymentInfo(paymentAddress);
        confirmPage.elements().confirmOrderButton().click();
        successPage.assertions().asserThatPurchaseIsMade();
    }

    public void purchaseValidation(Item product, PersonInfo person, PaymentAddressInfo paymentAddress, Account account) {
        homePage.searchByCategory(CategoryInSearchBox.COMPONENTS);
        productPage.addItemToCart(product);
        cartPage.elements().checkoutButton().click();

        checkoutPage.assertions().assertTotalPriceIsCorrect();

        checkoutPage.fillAccountDetails(person, paymentAddress, account);
        checkoutPage.continuePurchase();
    }
}