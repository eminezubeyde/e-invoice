package com.example.einvoice.core.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

public enum DriverMessage {
    NOT_FOUND("Böyle bir şoför bulunamadı"),
    ALREADY_EXISTS("${e.invoice.driver.already.exist.message}"),
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
