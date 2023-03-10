import org.example.cartpage.CartPage;
import org.example.comparisonpage.ProductComparison;
import org.example.enums.Item;
import org.example.enums.MainHeader;
import org.example.enums.MainMenu;
import org.example.homepage.HomePage;
import org.example.models.Product;
import org.example.productpage.ProductPage;
import org.example.sections.mainheadersection.MainHeaderSection;
import org.example.sections.mainnavigationsection.MainNavigationSections;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CompareTests extends BaseTest {
    private ProductPage productPage;
    private ProductComparison productComparison;
    private CartPage cartPage;
    private HomePage homePage;
    private MainNavigationSections mainNavigationSections;
    private MainHeaderSection mainHeaderSection;

    public CompareTests() {
        productPage = new ProductPage(driver);
        productComparison= new ProductComparison(driver);
        mainNavigationSections = new MainNavigationSections(driver);
        cartPage = new CartPage(driver);
        homePage = new HomePage(driver);
    }


    @Test
    public void AddProductToCartSuccessfully() {
        driver.goToUrl(homePage.url);
        mainNavigationSections.openMenu(MainMenu.SPECIAL);
        productPage.elements().imageItem(Item.IPOD_TOUCH).click();
        var product = productPage.setProductInfo();
        var products = new ArrayList<Product>();
        products.add(product);

        productPage.elements().compareButton().click();
        mainHeaderSection.elements().mainHeaderNavigation(MainHeader.COMPARE).click();
        productComparison.elements().addToCart(product.getTitle()).click();
        mainHeaderSection.elements().mainHeaderNavigation(MainHeader.CART).click();
        cartPage.elements().cartButton().click();

        cartPage.assertions().assertProductInfo(products);
    }

    @Test
    public void RemoveProductOfCompare() {
        driver.goToUrl(homePage.url);
        mainNavigationSections.openMenu(MainMenu.SPECIAL);
        productPage.elements().imageItem(Item.IPOD_TOUCH).click();

        var product = productPage.setProduct();
        productPage.elements().compareButton().click();
        mainHeaderSection.elements().mainHeaderNavigation(MainHeader.COMPARE).click();
        productComparison.elements().removeButton(product.getTitle()).click();

        productComparison.assertions().assertProductIsRemoved();
    }

}