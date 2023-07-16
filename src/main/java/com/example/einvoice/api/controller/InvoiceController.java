package com.example.einvoice.api.controller;

import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.requests.InvoiceRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.CompanyService;
import com.example.einvoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public GeneralResult create(@RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.create(invoiceRequest);
    }

    @PutMapping
    public GeneralResult update(@RequestBody InvoiceRequest invoiceRequest, @RequestParam int invoiceId) {
        return invoiceService.update(invoiceRequest, invoiceId);
    }

    @DeleteMapping
    public void delete(@RequestParam int invoiceId) {
        invoiceService.delete(invoiceId);
    }
}
