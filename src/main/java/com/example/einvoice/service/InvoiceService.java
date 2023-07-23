package com.example.einvoice.service;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    GeneralResult create(CreateInvoiceRequest createInvoiceRequest) throws EntityNotFoundException;

    GeneralResult getAll();
    List<Invoice> getAllInvoice();

    void delete(int invoiceId) throws EntityNotFoundException;


}
