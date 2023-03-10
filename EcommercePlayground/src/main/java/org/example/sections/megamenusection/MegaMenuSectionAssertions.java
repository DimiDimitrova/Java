package org.example.sections.megamenusection;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.enums.Categories;
import org.junit.jupiter.api.Assertions;

public class MegaMenuSectionAssertions {
    private Driver driver;

    public MegaMenuSectionAssertions(Driver driver) {
        this.driver = driver;
    }

    private MegaMenuSectionElements megaMenuSectionElements() {
        return new MegaMenuSectionElements(driver);
    }

    public void assertThatCategoryPresentInThePage(Categories category) {
        String temp = category.toString();

        Assertions.assertTrue(megaMenuSectionElements().searchCategoryHeader().isDisplayed(),
            String.format(ApplicationMessages.SEARCH_ERROR, category.toString()));

        Assertions.assertEquals(temp, megaMenuSectionElements().searchCategoryHeader().getText(),
            String.format(ApplicationMessages.SEARCH_ERROR, category.toString()));
    }

    public void assertMenuIsLoaded(String menu) {
        var temp = megaMenuSectionElements().searchCategoryHeader().getText();

        Assertions.assertEquals(menu, temp,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, temp, menu));
    }
}