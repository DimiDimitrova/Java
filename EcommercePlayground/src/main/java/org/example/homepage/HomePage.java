package org.example.homepage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.enums.*;

public class HomePage extends BaseEShopPage {
    public final String url = "https://ecommerce-playground.lambdatest.io";

    public HomePage(Driver driver) {
        super(driver);
    }

    public HomePageAssertions assertions() {
        return new HomePageAssertions(driver);
    }

    public HomePageElements elements() {
        return new HomePageElements(driver);
    }

    public void searchByCategory(CategoryInSearchBox category) {
        driver.goToUrl(url);
        selectCategory(category);
        elements().searchButton().click();
    }

    public void searchByManufacturer(Brand manufacturer) {
        driver.goToUrl(url);
        elements().searchInput().typeText(manufacturer.toString());
        elements().searchButton().click();
    }

    public void navigate() {
        driver.goToUrl(url);
    }

    public void search(String search) {
        driver.goToUrl(url);
        elements().searchInput().typeText(search);
        elements().searchButton().click();
    }

    public void searchByTopCategory(Categories category) {
        driver.goToUrl(url);
        elements().shopByCategoryButton().click();
        elements().getTopCategoryByName(category.toString()).click();
    }

    public void selectByBrandInMegaMenu(Brand brand) {
        mainNavigationSections().moveToMainMenu(MainMenu.MEGA_MENU);
        var temp = MegaMenu.MOBILES;
        elements().getMenuName(temp.toString(), brand.toString()).click();
    }

    public void selectBySoundSystemInMegaMenu(SoundSystem system) {
        mainNavigationSections().moveToMainMenu(MainMenu.MEGA_MENU);

        var temp = MegaMenu.SOUND_SYSTEM;
        elements().getMenuName(temp.toString(), system.toString()).click();
    }

    private void selectCategory(CategoryInSearchBox category) {
        elements().allCategoriesDropDown().click();
        elements().getCategoryById(category.categoryId()).click();
    }
}