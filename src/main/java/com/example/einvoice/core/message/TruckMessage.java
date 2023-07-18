package com.example.einvoice.core.message;

public enum TruckMessage {
    NOT_FOUND("Böyle bir kamyon bulunamadı"),
    ALREADY_EXISTS("Böyle bir kamyon zaten kayıtlı"),
    ALREADY_HAS_DRIVER("bu kamyona kayıtlı bir şöför zaten var"),
    SUCCESSFUL("BAŞARIYLA EKLENDİ");
    ;

    private final String message;

    TruckMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
