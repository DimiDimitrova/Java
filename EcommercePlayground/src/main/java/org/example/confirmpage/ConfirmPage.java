package org.example.confirmpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.extensions.PriceConvertingExtensions;
import org.example.models.ProductInfo;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ConfirmPage extends BaseEShopPage {
    public ConfirmPage(Driver driver) {
        super(driver);
    }

    public ConfirmPageElements elements() {
        return new ConfirmPageElements(driver);
    }

    public ConfirmPageAssertions assertions() {
        return new ConfirmPageAssertions(driver);
    }

    public List<ProductInfo> getProductsInfo() {
        var products = new ArrayList<ProductInfo>();

        for (var tableRow : elements().productsContent()) {
            var currentProductInfo = new ProductInfo();
            var currentRowCells = tableRow.findElements(By.tagName("td"));
            currentProductInfo.productName = currentRowCells.get(0).getText();
            currentProductInfo.model = currentRowCells.get(1).getText();
            currentProductInfo.quantity = Integer.parseInt(currentRowCells.get(2).getText());
            currentProductInfo.unitPrice = PriceConvertingExtensions.getAmount(currentRowCells.get(3).getText());
            currentProductInfo.unitPriceCurrency = PriceConvertingExtensions.getCurrencySign(currentRowCells.get(3).getText());
            currentProductInfo.total = PriceConvertingExtensions.getAmount(currentRowCells.get(4).getText());
            currentProductInfo.totalCurrency = PriceConvertingExtensions.getCurrencySign(currentRowCells.get(4).getText());
        }

        return products;
    }
}