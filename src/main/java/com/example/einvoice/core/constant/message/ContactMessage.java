package com.example.einvoice.core.constant.message;

public enum ContactMessage {
    NOT_FOUND("contactMessage.NOT_FOUND"),
    ALREADY_EXISTS("contactMessage.ALREADY_EXISTS"),
    SUCCESSFUL("contactMessage.SUCCESSFUL");

    private final String key;

    ContactMessage(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
