package org.example.productpage;

import org.example.ApplicationMessages;
import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.enums.ExtraProductContent;
import org.example.enums.Item;
import org.example.extensions.PriceConvertingExtensions;
import org.example.models.Product;

public class ProductPage extends BaseEShopPage {
    public ProductPage(Driver driver) {
        super(driver);
    }

    public ProductPageElements elements() {
        return new ProductPageElements(driver);
    }

    public ProductPageAssertions assertions() {
        return new ProductPageAssertions(driver);
    }

    public void addItemToCart(Item product) {
        selectQuickView(product);

        if (!elements().productExtraContent(ExtraProductContent.AVAILABILITY).getText().equals("In Stock")) {
            throw new IllegalArgumentException(ApplicationMessages.NOT_IN_STOCK);
        }

        elements().addToCartItemButton().click();
    }

    public Product addProductToCart(Item product) {
        selectQuickView(product);

        if (!elements().productExtraContent(ExtraProductContent.AVAILABILITY).getText().equals("In Stock")) {
            throw new IllegalArgumentException(ApplicationMessages.NOT_IN_STOCK);
        }

        var productInfo = setProductInfo();
        elements().addToCartItemButton().click();

        return productInfo;
    }

    public void selectQuickView(Item productName) {
        driver.waitUntilPageLoadsCompletely();
        elements().imageItem(productName).moveTo();
        driver.waitForAjax();
        driver.waitUntilPageLoadsCompletely();
        elements().quickViewButton(productName).click();
    }

    public Product setProductInfo() {
        var product = new Product();
        product.setTitle(elements().productName().getText());
        product.setUnitPrice(PriceConvertingExtensions.getAmount(elements().productPrice().getText()));
        var imageLink = elements().productImage().getAttribute("src");
        String imageLinkWithoutSize = imageLink.substring(0, imageLink.lastIndexOf('/'));
        product.setImage(imageLinkWithoutSize);
        product.setModel(elements().productExtraContent(ExtraProductContent.PRODUCT_CODE).getText());
        product.setInStock(elements().productExtraContent(ExtraProductContent.AVAILABILITY).getText());
        product.setBrand(elements().brand().getText());

        try {
            product.setRewardPoints(Integer.parseInt(elements().productExtraContent(ExtraProductContent.REWARD_POINTS).getText()));
        } catch (Exception ex) {
            return product;
        }

        return product;
    }

    public Product addProductForCompare(Item product) {
        elements().imageItem(product).click();

        var productInfo = setProduct();
        elements().compareButton().click();

        return productInfo;
    }

    public Product setProduct() {
        var product = new Product();
        product.setTitle(elements().product().getText());
        product.setUnitPrice(PriceConvertingExtensions.getAmount(elements().productPrice().getText()));
        var imageLink = elements().productImage().getAttribute("src");
        String imageLinkWithoutSize = imageLink.substring(0, imageLink.lastIndexOf('/'));
        product.setImage(imageLinkWithoutSize);
        product.setModel(elements().productExtraContent(ExtraProductContent.PRODUCT_CODE).getText());
        product.setInStock(elements().productExtraContent(ExtraProductContent.AVAILABILITY).getText());
        product.setBrand(elements().brand().getText());

        try {
            product.setRewardPoints(Integer.parseInt(elements().productExtraContent(ExtraProductContent.REWARD_POINTS).getText()));
        } catch (Exception exception) {
            return product;
        }

        return product;
    }
}