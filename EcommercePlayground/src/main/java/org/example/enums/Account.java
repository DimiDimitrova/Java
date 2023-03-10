package org.example.enums;

public enum Account {
    LOGIN("login"),
    REGISTER_ACCOUNT("register"),
    GUEST_ACCOUNT("guest");

    private String name;

    Account(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
}