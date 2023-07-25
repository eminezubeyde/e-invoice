package com.example.einvoice.core.message;

public enum CompanyMessage {
    NOT_FOUND("companyMessage.NOT_FOUND"),
    ALREADY_EXISTS("companyMessage.ALREADY_EXISTS"),
    SUCCESSFUL("companyMessage.SUCCESSFUL");


    private final String key;

    CompanyMessage(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
