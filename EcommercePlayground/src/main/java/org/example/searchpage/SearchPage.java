package org.example.searchpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;

public class SearchPage extends BaseEShopPage {
    public SearchPage(Driver driver) {
        super(driver);
    }

    public SearchElements elements() {
        return new SearchElements(driver);
    }

    public SearchAssertions assertions() {
        return new SearchAssertions(driver);
    }

    public void searchByKeywords(String keywords) {
        elements().inputSearch().typeText(keywords);
        elements().searchButton().click();
    }
}