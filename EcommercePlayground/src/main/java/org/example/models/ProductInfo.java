package org.example.models;

public class ProductInfo {
    public String image;
    public String productLink;
    public String title;
    public double unitPrice;
    public double total;
    public int quantity;
    public String model;
    public String productName;

    public char unitPriceCurrency;
    public char totalCurrency;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public char getUnitPriceCurrency() {
        return unitPriceCurrency;
    }

    public void setUnitPriceCurrency(char unitPriceCurrency) {
        this.unitPriceCurrency = unitPriceCurrency;
    }

    public char getTotalCurrency() {
        return totalCurrency;
    }

    public void setTotalCurrency(char totalCurrency) {
        this.totalCurrency = totalCurrency;
    }

}