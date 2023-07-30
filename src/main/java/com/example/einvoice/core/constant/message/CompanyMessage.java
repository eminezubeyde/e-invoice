package com.example.einvoice.core.constant.message;

public enum CompanyMessage {
    NOT_FOUND("companyMessage.NOT_FOUND"),// companyMessage.error.NOT_FOUND
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
