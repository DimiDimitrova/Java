import org.example.cartpage.CartPage;
import org.example.checkoutpage.CheckoutPage;
import org.example.enums.Brand;
import org.example.enums.Item;
import org.example.homepage.HomePage;
import org.example.models.Product;
import org.example.productpage.ProductPage;
import org.example.quickviewpage.QuickView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProductTests extends BaseTest {
    private static HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private QuickView quickView;

    public ProductTests() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        quickView = new QuickView(driver);
    }


    @Test
    public void viewProductInformation() {
        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().imageItem(Item.IPOD_NANO).click();

        productPage.assertions().assertProductInformationIsDisplayed();
    }

    @Test
    public void addProductToCartSuccessfully() {
        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().imageItem(Item.IPOD_NANO).click();
        var product = productPage.setProductInfo();
        var products = new ArrayList<Product>();
        products.add(product);

        productPage.elements().addToCartItemButton().click();
        cartPage.elements().viewCartButton().click();

        cartPage.assertions().assertProductInfo(products);
    }

    @Test
    public void buyProductSuccessfully() {
        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().imageItem(Item.MAC_BOOK_PRO).click();
        var product = productPage.setProduct();
        var products = new ArrayList<Product>();
        products.add(product);

        productPage.elements().buyNowButton().click();

        checkoutPage.assertions().checkoutPageIsLoaded();
        checkoutPage.assertions().assertProductsInfo(products);
    }

    @Test
    public void increaseQuantitySuccessfully_When_QuantityFieldIsEmpty() {
        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().listViewButton().click();
        productPage.selectQuickView(Item.IPOD_SHUFFLE);

        quickView.elements().quickViewQuantityInput().typeText("");
        quickView.elements().increaseQtyButton().click();

        quickView.assertions().assertQuantityButtonWork(1);
    }

    @Test
    public void DecreaseQuantitySuccessfully() {
        homePage.searchByManufacturer(Brand.APPLE);
        productPage.elements().listViewButton().click();
        productPage.selectQuickView(Item.IPOD_SHUFFLE);

        quickView.elements().increaseQtyButton().click();
        quickView.elements().decreaseQtyButton().click();

        quickView.assertions().assertQuantityButtonWork(1);
    }
}