package com.example.einvoice.core.message;

public enum TruckMessage {
    NOT_FOUND("truckMessage.NOT_FOUND"),
    ALREADY_EXISTS("truckMessage.ALREADY_EXISTS"),
    ALREADY_HAS_DRIVER("truckMessage.ALREADY_HAS_DRIVER"),
    SUCCESSFUL("truckMessage.SUCCESSFUL"),
    INVALID_PLATE("truckMessage.INVALID_PLATE"),

    PLATE_NOTBLANK("truckMessage.PLATE_NOTBLANK");
    private final String key;

    TruckMessage(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
