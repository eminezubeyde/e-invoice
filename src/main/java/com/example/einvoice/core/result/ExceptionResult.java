package com.example.einvoice.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResult {


    private String status;
    private String message;
    private String path;
    private LocalDateTime time;
}
