import org.example.enums.*;
import org.example.homepage.HomePage;
import org.example.productpage.ProductPage;
import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.example.sections.mainnavigationsection.MainNavigationSections;
import org.example.sections.megamenusection.MegaMenuSection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class MenuTests extends BaseTest {
    private HomePage homePage;
    private ProductPage productPage;
    private MainNavigationSections mainNavigationSections;
    private MegaMenuSection megaMenuSection;
    private BreadcrumbSection breadcrumbSection;

    public MenuTests() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        mainNavigationSections = new MainNavigationSections(driver);
        megaMenuSection = new MegaMenuSection(driver);
        breadcrumbSection = new BreadcrumbSection(driver);
    }

    @Test
    public void openMegaMenuSuccessfully() {
        driver.goToUrl(homePage.url);

        mainNavigationSections.openMenu(MainMenu.MEGA_MENU);

        homePage.assertions().assertCorrectPageIsOpen(homePage.elements().megaMenuButton().getText());
    }

    @ParameterizedTest
    @EnumSource(Brand.class)
    public void selectByBrandInMenuSuccessfully(Brand brand) {
        driver.goToUrl(homePage.url);

        homePage.selectByBrandInMegaMenu(brand);

        megaMenuSection.assertions().assertMenuIsLoaded(brand.toString());
    }

    @ParameterizedTest
    @EnumSource(SoundSystem.class)
    public void selectMenuInSoundSectionSuccessfully(SoundSystem system) {
        driver.goToUrl(homePage.url);

        homePage.selectBySoundSystemInMegaMenu(system);

        megaMenuSection.assertions().assertMenuIsLoaded(system.toString());
    }

    @Test
    public void openSpecialMenuSuccessfully() {
        driver.goToUrl(homePage.url);

        mainNavigationSections.openMenu(MainMenu.SPECIAL);

        breadcrumbSection.assertions().assertMenuIsOpen(PageTitle.SPECIAL_OFFERS.toString());
    }

    @Test
    public void selectProductFromSpecialMenu() {
        driver.goToUrl(homePage.url);
        mainNavigationSections.openMenu(MainMenu.SPECIAL);

        productPage.elements().imageItem(Item.IPOD_TOUCH).click();

        productPage.assertions().assertProductInformationIsDisplayed();
    }
}