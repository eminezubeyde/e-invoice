package com.example.einvoice.core.message;

public enum InvoiceMessage {
    NOT_FOUND("Böyle bir fatura bulunamadı"),
    ALREADY_EXISTS("Böyle bir fatura zaten kayıtlı"),
    SUCCESSFUL("BAŞARIYLA EKLENDİ");
    ;

    private final String message;

    InvoiceMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
