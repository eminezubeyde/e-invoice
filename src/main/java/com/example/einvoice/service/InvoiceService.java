package com.example.einvoice.service;

import com.example.einvoice.core.requests.InvoiceRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface InvoiceService {
    GeneralResult create(InvoiceRequest invoiceRequest);

    GeneralResult update(InvoiceRequest invoiceRequest, int invoiceId);

    void delete(int invoiceId);

}
