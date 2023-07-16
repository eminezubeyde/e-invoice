package com.example.einvoice.core.message;

public enum ContactMessage {
    NOT_FOUND("Not found"),
    ALREADY_EXISTS("already exists"),
    SUCCESSFUL("BAŞARIYLA EKLENDİ");;

    private final String message;

    ContactMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
