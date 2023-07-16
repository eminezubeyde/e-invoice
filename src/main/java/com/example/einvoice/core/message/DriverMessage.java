package com.example.einvoice.core.message;

public enum DriverMessage {
    NOT_FOUND("Böyle bir şoför bulunamadı"),
    ALREADY_EXISTS("Böyle bir şoför zaten kayıtlı"),
    SUCCESSFUL("BAŞARIYLA EKLENDİ");
    ;

    private final String message;

    DriverMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
