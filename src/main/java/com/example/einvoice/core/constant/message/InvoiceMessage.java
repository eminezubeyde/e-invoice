package com.example.einvoice.core.constant.message;

public enum InvoiceMessage {
    NOT_FOUND("invoiceMessage.NOT_FOUND"),
    ALREADY_EXISTS("invoiceMessage.ALREADY_EXISTS"),
    SUCCESSFUL("invoiceMessage.SUCCESSFUL");

    private final String key;

    InvoiceMessage(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

