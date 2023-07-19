package com.example.einvoice.service;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.result.GeneralResult;

import java.time.LocalDateTime;

public interface FilterService {
    GeneralResult getTotalAmountOfInvoicesByMonthAndCompany(String companyName, LocalDateTime month) throws EntityNotFoundException;

    GeneralResult getInvoicesByMonthAndCompany(String companyName, LocalDateTime month) throws EntityNotFoundException;

    GeneralResult getInvoicesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) throws EntityNotFoundException;
}
