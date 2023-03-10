package org.example.enums;

public enum MyAccountDropDown {
    DASHBOARD("account/account"),
    REGISTER("account/register"),
    LOGIN("account/login"),
    MY_ORDER("account/order"),
    RETURN("account/return/add"),
    TRACKING("information/tracking"),
    MY_VOUCHER("account/voucher"),
    LOGOUT("account/logout");

    private String name;

    MyAccountDropDown(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}