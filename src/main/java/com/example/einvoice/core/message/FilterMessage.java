package com.example.einvoice.core.message;

public enum FilterMessage {
    NOT_FOUND("istenilen ayda istenilen şirket için kesilmiş fatura bulunamadı"),
    NOT_FOUND_DATE("belirtilen tarih aralığında kesilen fatura bulunamdı"),
    SUCCESSFUL("BAŞARIYLA EKLENDİ");
    ;

    private final String message;

    FilterMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
