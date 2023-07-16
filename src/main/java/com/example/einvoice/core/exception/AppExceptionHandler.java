package com.example.einvoice.core.exception;

import com.example.einvoice.core.result.ExceptionResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResult generalExceptionHandler(GeneralException exception,
                                                   HttpServletRequest request) {
        return new ExceptionResult(HttpStatus.BAD_REQUEST.toString(),
                exception.getMessage(),
                request.getServletPath(),
                LocalDateTime.now());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResult entityNotFoundExceptionHandler(EntityNotFoundException exception,
                                                          HttpServletRequest request) {
        return new ExceptionResult(HttpStatus.NOT_FOUND.toString(),
                exception.getMessage(),
                request.getServletPath(),
                LocalDateTime.now());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ExceptionResult alreadyExistsExceptionHandler(AlreadyExistsException exception,
                                                         HttpServletRequest request) {
        return new ExceptionResult(HttpStatus.ALREADY_REPORTED.toString(),
                exception.getMessage(),
                request.getServletPath(),
                LocalDateTime.now());
    }
}
