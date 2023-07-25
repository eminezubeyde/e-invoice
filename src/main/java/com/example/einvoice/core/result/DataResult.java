package com.example.einvoice.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResult <T> extends GeneralResult{
    private T data;

    public DataResult(String message, T data) {
        super(message);
        this.data = data;
    }

    public DataResult(String message, Boolean isSuccessful, T data) {
        super(message, isSuccessful);
        this.data = data;
    }
}
