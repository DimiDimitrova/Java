package org.example.enums;

public enum HomePageModuleTitle {
    TOP_TRENDING_CATEGORIES("Top Trending Categories"),
    DEALS_OF_THE_DAY("Deals of the day"),
    TOP_PRODUCTS("Top Products"),
    TOP_COLLECTION("Top Collection"),
    ON_SALE("On sale"),
    UNDER_99("Under @99"),
    FROM_THE_BLOG("From The Blog");
    private final String name;

    HomePageModuleTitle(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
