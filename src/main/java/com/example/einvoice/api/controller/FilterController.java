package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {
    private final FilterService filterService;

    @GetMapping("/getTotalAmountOfInvoicesByMonthAndCompany")
    public GeneralResult getTotalAmountOfInvoicesByMonthAndCompany(@RequestParam String companyName,
                                                                   @RequestParam LocalDateTime month) throws EntityNotFoundException {
        return filterService.getTotalAmountOfInvoicesByMonthAndCompany(companyName, month);
    }

    @GetMapping("/getInvoicesByMonthAndCompany")
    public GeneralResult getInvoicesByMonthAndCompany(@RequestParam String companyName,
                                                      @RequestParam LocalDateTime month) throws EntityNotFoundException {
        return filterService.getInvoicesByMonthAndCompany(companyName, month);
    }

    @GetMapping("/getInvoicesBetweenDates")
    public GeneralResult getInvoicesBetweenDates(@RequestParam LocalDateTime startDate,
                                                 @RequestParam LocalDateTime endDate) throws EntityNotFoundException {
        return filterService.getInvoicesBetweenDates(startDate,endDate);
    }
}
