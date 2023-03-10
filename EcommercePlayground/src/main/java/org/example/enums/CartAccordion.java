package org.example.enums;

public enum CartAccordion {
    USE_COUPON_CODE("Use Coupon Code"),
    ESTIMATE_SHIPPING_TAXES("Estimate Shipping & Taxes"),
    USE_GIFT_CERTIFICATE("Use Gift Certificate");
    private final String name;

    CartAccordion(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}