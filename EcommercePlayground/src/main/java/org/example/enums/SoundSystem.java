package org.example.enums;

public enum SoundSystem {
    BLUETOOTH_SPEAKER("Bluetooth Speaker"),
    DTH("DTH"),
    HOME_AUDIO("Home Audio"),
    HOME_THEATRE("Home Theatre"),
    SOUND_BAR("SoundBar");
    private final String name;

    SoundSystem(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}