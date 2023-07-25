package com.example.einvoice.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResult {
    private String message;
    private Boolean isSuccessful;

    public GeneralResult(String message) {
        this.message = message;
    }

}
