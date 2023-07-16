package com.example.einvoice.core.message;

public enum CompanyMessage {
    NOT_FOUND("Böyle bir şirket bulunamadı"),
    ALREADY_EXISTS("Böyle bir şirket zaten kayıtlı"),
    SUCCESSFUL("BAŞARIYLA EKLENDİ");
    ;

    private final String message;

    CompanyMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
