package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {
    private final FilterService filterService;

    @GetMapping("/getTotalAmountOfInvoicesByMonthAndCompany")
    public GeneralResult getTotalAmountOfInvoicesByMonthAndCompany(@RequestParam(name = "companyName") String companyName,
                                                                   @RequestParam(name = "month") @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) throws EntityNotFoundException {
        return filterService
                .getTotalAmountOfInvoicesByMonthAndCompany(companyName, LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    @GetMapping("/getInvoicesByMonthAndCompany")
    public GeneralResult getInvoicesByMonthAndCompany(@RequestParam(name = "companyName") String companyName,
                                                      @RequestParam(name = "month") @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) throws EntityNotFoundException {
        return filterService.
                getInvoicesByMonthAndCompany(companyName, LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    @GetMapping("/getInvoicesBetweenDates")
    public GeneralResult getInvoicesBetweenDates(@RequestParam(name = "startDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
                                                 @RequestParam(name = "endDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) throws EntityNotFoundException {
        return filterService.
                getInvoicesBetweenDates(LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()));

    }

    @GetMapping("/getInvoicesByTruckPlateAndMonth")
    public GeneralResult getInvoicesByTruckPlateAndMonth(@RequestParam(name = "plate") String plate,
                                                         @RequestParam(name = "startDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
                                                         @RequestParam(name = "endDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) throws EntityNotFoundException {
        return filterService.
                getInvoicesByTruckPlateAndMonth(plate, LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()));
    }

    @GetMapping("invoices")
    public GeneralResult getInvoicesByFilter(@RequestParam(defaultValue = "0", required = false) int page,
                                             @RequestParam(defaultValue = "10", required = false) int size,
                                             @RequestParam(name = "companyName", required = false) String companyName,
                                             @RequestParam(name = "plate", required = false) String plate,
                                             @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
                                             @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate) throws EntityNotFoundException {
        return filterService.filterInvoices(page, size, companyName, plate, startDate, endDate);
    }


}
