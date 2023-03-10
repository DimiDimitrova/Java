import org.example.enums.HomePageModuleTitle;
import org.example.homepage.HomePage;
import org.example.productpage.ProductPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class HomePageTests extends BaseTest {
    private HomePage homePage;
    private ProductPage productPage;
    public HomePageTests() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
    }

    @ParameterizedTest
    @EnumSource(HomePageModuleTitle.class)
    public void homePageContainsInformationForModules(HomePageModuleTitle moduleTitle) {
        homePage.navigate();

        homePage.assertions().assertModuleInformationPresent(moduleTitle);
    }

    @Test
    public void shopNowFromHomePageSuccessfully() {
        homePage.navigate();

        homePage.elements().shopNowAdButton().click();

        productPage.assertions().assertProductPageIsOpen();
    }
}