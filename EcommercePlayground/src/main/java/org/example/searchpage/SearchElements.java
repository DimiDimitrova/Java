package org.example.searchpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class SearchElements {
    private Driver driver;

    public SearchElements(Driver driver) {
        this.driver = driver;
    }

    public Element inputSearch() {
        return driver.findElement(By.id("input-search"));
    }

    public Element searchButton() {
        return driver.findElement(By.id("button-search"));
    }

    public Element searchValueInResult() {
        return driver.findElement(By.xpath("//*[contains(@class,'title')]/a"));
    }

    public Element searchCategoryDropDown(int categoryId) {
        String locator = String.format("//*[@name='category_id']/option[@value='%d' and @selected='selected']",
            categoryId);

        return driver.findElement(By.xpath(locator));
    }

    public Element searchCriteriaInput(String searchedCriteria) {
        String locator = String.format("//input[@id='input-search' and @value='%s']", searchedCriteria);
        return driver.findElement(By.xpath(locator));
    }
}