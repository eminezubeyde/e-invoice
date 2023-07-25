package com.example.einvoice.service;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.exception.GeneralException;
import com.example.einvoice.core.result.GeneralResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface FilterService {
    GeneralResult getTotalAmountOfInvoicesByMonthAndCompany(String companyName, LocalDateTime month) throws EntityNotFoundException;

    GeneralResult getInvoicesByMonthAndCompany(String companyName, LocalDateTime month) throws EntityNotFoundException;

    GeneralResult getInvoicesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) throws EntityNotFoundException;

    GeneralResult getInvoicesByTruckPlateAndMonth(String plate, LocalDateTime startDate, LocalDateTime endDate) throws EntityNotFoundException;

    GeneralResult filterInvoices(int page, int size, String companyName, String plate, BigDecimal minTotalAmount, BigDecimal maxTotalAmount, LocalDate startDate, LocalDate endDate) throws GeneralException;

}
