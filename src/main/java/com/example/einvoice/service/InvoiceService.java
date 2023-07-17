package com.example.einvoice.service;

import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.requests.update.UpdateInvoiceRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface InvoiceService {
    GeneralResult create(CreateInvoiceRequest createInvoiceRequest);

    GeneralResult update(UpdateInvoiceRequest updateInvoiceRequest, int invoiceId);

    void delete(int invoiceId);

}
