package org.example.homepage;

import org.example.core.Driver;
import org.example.core.Element;
import org.example.enums.HomePageModuleTitle;
import org.openqa.selenium.By;

public class HomePageElements {
    private Driver driver;

    public HomePageElements(Driver browser) {
        this.driver = browser;
    }

    public Element searchInput() {
        return driver.findElement(By.xpath("//div[@id='search']//child::input[@name='search']"));
    }

    public Element allCategoriesDropDown() {
        return driver.findElement(
                By.xpath("//*[contains(@id,'search')]//button[contains(@class,'dropdown-toggle')]"));
    }

    public Element searchButton() {
        return driver.findElement(By.xpath("//*[@id='search']/child::div[@class='search-button']/button"));
    }

    public Element megaMenuButton() {
        return driver.findElement(By.xpath("//span[contains(text(),' Mega Menu')]//parent::div//parent::a"));
    }

    public Element shopByCategoryButton() {
        return driver.findElement(By.xpath("//div[@data-id='217832']/a"));
    }

    public Element shopNowAdButton() {
        return driver.findElement(By.xpath("//*[contains(text(),'SHOP NOW')]"));
    }

    public Element getCategoryById(int id) {
        String locator = String.format("//a[@data-category_id='%d']", id);
        return driver.findElement(By.xpath(locator));
    }

    public Element getTopCategoryByName(String name) {
        String locator = String.format("//span[contains(text(),'%s')]//parent::div//parent::a", name);
        return driver.findElement(By.xpath(locator));
    }

    public Element getMenuName(String section, String menu) {
        String locator = String.format(
                "//h3[contains(text(),'%s')]//following-sibling::div/ul/li/a[@title='%s']", section, menu);
        return driver.findElement(By.xpath(locator));
    }

    public Element informationForModule(HomePageModuleTitle title) {
        String locator = String.format("//h3[@class='module-title' and (contains(text(),'%s'))]", title.toString());
        return driver.findElement(By.xpath(locator));
    }
}