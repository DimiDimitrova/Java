package org.example.cartpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.enums.Country;
import org.example.enums.GiftCertificate;
import org.example.enums.TablePrice;
import org.example.extensions.PriceConvertingExtensions;
import org.example.models.ProductInfo;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseEShopPage {
    public CartPage(Driver driver) {
        super(driver);
    }

    public CartPageElements elements() {
        return new CartPageElements(driver);
    }

    public CartPageAssertions assertions() {
        return new CartPageAssertions(driver);
    }

    public double totalPriceProducts() {
        double total = 0;

        for (var quantity : productsQuantity()) {
            for (var price : productsPrice()) {
                total += quantity * price;
            }
        }

        return total;
    }

    public void updateProductQuantityInCart(int newQuantity, String product) {
        elements().quantityField(product).typeText(Integer.toString(newQuantity));
        driver.waitUntilPageLoadsCompletely();
        elements().updateButton(product).click();
    }

    public void fillEstimateShippingTaxes(Country country, String postCode) {
        elements().countryShippingTaxes().click();
        elements().countryOption(country).click();
        elements().regionStateShippingTaxes().click();
        driver.waitForAjax();

        try {
            elements().regionOption("8").click();
        } catch (Exception ex) {
            elements().regionOption().click();
        }

        elements().postCodeSatateShippingTaxes().typeText(postCode);
    }

    public List<Double> getPrices() {
        var prices = new ArrayList<Double>();
        prices.add(PriceConvertingExtensions.getAmount(elements().selectCartTotalInfo(TablePrice.SUB_TOTAL).getText()));
        prices.add(PriceConvertingExtensions.getAmount(elements().selectCartTotalInfo(TablePrice.FLAT_SHIPPING_RATE).getText()));
        prices.add(PriceConvertingExtensions.getAmount(elements().selectCartTotalInfo(TablePrice.TOTAL).getText()));

        return prices;
    }

    public void enterGiftCertificate(GiftCertificate giftTheme) {
        elements().voucherInput().typeText(giftTheme.toString());
    }

    public List<ProductInfo> shoppingTableContent() {
        var products = new ArrayList<ProductInfo>();

        for (var tableRow : elements().cartRowsContent()) {
            var currentProductInfo = new ProductInfo();

            var currentRowCells = tableRow.findElements(By.xpath(".//td"));
            currentProductInfo.productLink = currentRowCells.get(0).findElement(By.tagName("a")).getAttribute("href");
            var imageLink = currentRowCells.get(0).findElement(By.tagName("img")).getAttribute("src");
            String imageLinkWithoutSize = imageLink.substring(0, imageLink.lastIndexOf('/'));
            currentProductInfo.image = imageLinkWithoutSize;
            currentProductInfo.productName = currentRowCells.get(1).getText();
            currentProductInfo.model = currentRowCells.get(2).getText();
            currentProductInfo.quantity = Integer.parseInt(currentRowCells.get(3).
                findElement(By.xpath("//*[contains(@name, 'quantity')]")).getAttribute("value"));

            currentProductInfo.unitPrice = PriceConvertingExtensions.getAmount(currentRowCells.get(4).getText());
            currentProductInfo.unitPriceCurrency = PriceConvertingExtensions.getCurrencySign(currentRowCells.get(4).getText());
            currentProductInfo.total = PriceConvertingExtensions.getAmount(currentRowCells.get(5).getText());
            currentProductInfo.totalCurrency = PriceConvertingExtensions.getCurrencySign(currentRowCells.get(5).getText());

            products.add(currentProductInfo);
        }

        return products;
    }

    private List<Integer> productsQuantity() {
        var list = new ArrayList<Integer>();
      /*  for (var item : elements().tableProductQuantity()) {
            for (var cell : item.findElements(By.tagName("input"))) {
                list.add(Integer.parseInt(cell.getAttribute("value")));
            }
        }*/

        for (var item : elements().tableProductQuantity()) {
            list.add(Integer.parseInt(item.getAttribute("value")));
        }

        return list;
    }

    private List<Double> productsPrice() {
        var list = new ArrayList<Double>();
        for (var item : elements().tableProductUnitPrice()) {
            list.add(PriceConvertingExtensions.getAmount(item.getText()));
        }

        return list;
    }

    public double estimateEcoTax() {
        var number = PriceConvertingExtensions.getTax(elements().ecoTax().getText());
        double ecoTax = countOfProducts() * number;

        return ecoTax;
    }

    public double estimateVAT() {
        String subTotal = elements().selectCartTotalInfo(TablePrice.SUB_TOTAL).getText();
        double vat = PriceConvertingExtensions.getTax(elements().vatTax().getText());

        return Math.round((PriceConvertingExtensions.getAmount(subTotal) * vat) / 100);
    }

    private int countOfProducts() {
        int count = productsQuantity().stream().mapToInt(item -> item).sum();

        return count;
    }
}