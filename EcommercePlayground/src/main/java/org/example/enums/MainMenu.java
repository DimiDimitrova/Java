package org.example.enums;

public enum MainMenu {
    HOME("common/home"),
    SPECIAL("product/special"),
    BLOG("blog/home"),
    MEGA_MENU("information"),
    MY_ACCOUNT("account/account"),
    VOUCHER("account/voucher");
    private final String name;

    MainMenu(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
