package org.example.searchpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.enums.Brand;
import org.example.enums.CategoryInSearchBox;
import org.junit.jupiter.api.Assertions;

public class SearchAssertions {
    private Driver driver;

    public SearchAssertions(Driver driver) {
        this.driver = driver;
    }

    private SearchElements searchElements() {
        return new SearchElements(driver);
    }

    public void assertBySearchedCategory(CategoryInSearchBox category) {
        Assertions.assertTrue(searchElements().searchCategoryDropDown(category.categoryId()).isDisplayed(),
            String.format(ApplicationMessages.SEARCH_ERROR, category.toString()));
    }

    public void assertBySearchedManufacturer(Brand manufacturer) {
        Assertions.assertTrue(searchElements().searchCriteriaInput(manufacturer.toString()).isDisplayed(),
            String.format(ApplicationMessages.SEARCH_ERROR, manufacturer.toString()));
    }

    public void assertBySearchedInput(String input) {
        Assertions.assertTrue(searchElements().searchValueInResult().getText().toLowerCase().contains(input),
            ApplicationMessages.SEARCH_VALUE_ERROR);
    }
}