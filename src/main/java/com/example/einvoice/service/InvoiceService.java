package com.example.einvoice.service;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface InvoiceService {
    GeneralResult create(CreateInvoiceRequest createInvoiceRequest) throws EntityNotFoundException;

    GeneralResult getAll();

    void delete(int invoiceId) throws EntityNotFoundException;


}
