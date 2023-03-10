package org.example.enums;

public enum Brand {
    APPLE("Apple"),
    HTC("HTC"),
    LG("LG"),
    NOKIA("Nokia"),
    SAMSUNG("Samsung"),
    XIOMI("Xiomi");
    private final String name;

    Brand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
