package com.example.einvoice.core.message;

public enum FilterMessage {
    NOT_FOUND("istenilen ayda istenilen şirket için kesilmiş fatura bulunamadı"),
    NOT_FOUND_DATE("belirtilen tarih aralığında kesilen fatura bulunamdı"),
    SUCCESSFUL("BAŞARILI"),
    PAGE_COUNT_INVALID("SAYFA SAYISI GEÇERSİZ"),
    BAD_REQUEST("tarih hatalı "),

    INVALID_DATE ("bitiş tarihi başlangıç tarihinden sonra olmalıdır.") ;
    private final String message;

    FilterMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
