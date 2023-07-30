package com.example.einvoice.core.exception;

import com.example.einvoice.core.result.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler
    public ExceptionResult handleConstraintViolationException(ConstraintViolationException exception,
                                                              HttpServletRequest request) {
        return new ExceptionResult(HttpStatus.ALREADY_REPORTED.toString(),
                exception.getMessage(),
                request.getServletPath(),
                LocalDateTime.now());
    }
    @ExceptionHandler
    public ExceptionResult handleConstraintViolationException(MethodArgumentNotValidException exception,
                                                              HttpServletRequest request) {

        Map<String, String> validationErrors = new HashMap<String, String>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ExceptionResult(HttpStatus.ALREADY_REPORTED.toString(),
                validationErrors.values().toString(),
                request.getServletPath(),
                LocalDateTime.now());
    }

}
