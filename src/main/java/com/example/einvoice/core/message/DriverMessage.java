package com.example.einvoice.core.message;

public enum DriverMessage {
    NOT_FOUND("driverMessage.NOT_FOUND"),
    ALREADY_EXISTS("driverMessage.ALREADY_EXISTS"),
    SUCCESSFUL("driverMessage.SUCCESSFUL");

    private final String key;

    DriverMessage(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
