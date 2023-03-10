import org.example.enums.Brand;
import org.example.enums.Categories;
import org.example.enums.CategoryInSearchBox;
import org.example.homepage.HomePage;
import org.example.searchpage.SearchPage;
import org.example.sections.megamenusection.MegaMenuSection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class SearchTest extends BaseTest {
    private HomePage homePage;
    private SearchPage searchPage;
    private MegaMenuSection megaMenuSection;

    public SearchTest() {
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        megaMenuSection = new MegaMenuSection(driver);
    }

    @ParameterizedTest
    @EnumSource(CategoryInSearchBox.class)
    public void searchByCategory(CategoryInSearchBox category) {
        homePage.searchByCategory(category);

        searchPage.assertions().assertBySearchedCategory(category);
    }

    @ParameterizedTest
    @EnumSource(Brand.class)
    public void searchByManufacturer(Brand brand) {
        homePage.searchByManufacturer(brand);

        searchPage.assertions().assertBySearchedManufacturer(brand);
    }

   // @ParameterizedTest
   // @EnumSource(Categories.class)
    @Test
    public void searchWithTopCategoryFilter(/*Categories category*/) {
        homePage.searchByTopCategory(Categories.WASHING_MACHINE);

        megaMenuSection.assertions().assertThatCategoryPresentInThePage(Categories.WASHING_MACHINE);
    }

    @Test
    public void searchWithSearchInput() {
        String search = "canon";

        homePage.search(search);

        searchPage.assertions().assertBySearchedInput(search);
    }

    @Test
    public void SearchWithKeywords() {
        String search = "n";
        driver.goToUrl(homePage.url);
        homePage.elements().searchButton().click();

        searchPage.searchByKeywords(search);

        searchPage.assertions().assertBySearchedInput(search);
    }
}
