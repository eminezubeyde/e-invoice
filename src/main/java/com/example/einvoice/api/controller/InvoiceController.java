package com.example.einvoice.api.controller;

import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.requests.update.UpdateInvoiceRequest;
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
    public GeneralResult create(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
        return invoiceService.create(createInvoiceRequest);
    }

    @PutMapping
    public GeneralResult update(@RequestBody UpdateInvoiceRequest updateInvoiceRequest, @RequestParam int invoiceId) {
        return invoiceService.update(updateInvoiceRequest, invoiceId);
    }

    @DeleteMapping
    public void delete(@RequestParam int invoiceId) {
        invoiceService.delete(invoiceId);
    }
}
