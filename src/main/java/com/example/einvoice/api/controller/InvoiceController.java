package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public GeneralResult create(@RequestBody CreateInvoiceRequest createInvoiceRequest) throws EntityNotFoundException {
        return invoiceService.create(createInvoiceRequest);
    }

    @GetMapping
    public GeneralResult getAll() {
        return invoiceService.getAll();
    }

    @DeleteMapping
    public void delete(@RequestParam int invoiceId) throws EntityNotFoundException {
        invoiceService.delete(invoiceId);
    }
}
