package com.example.einvoice.service.impl;

import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.requests.update.UpdateInvoiceRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.repository.InvoiceRepository;
import com.example.einvoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Override
    public GeneralResult create(CreateInvoiceRequest createInvoiceRequest) {
        return null;
    }

    @Override
    public GeneralResult update(UpdateInvoiceRequest updateInvoiceRequest, int invoiceId) {
        return null;
    }

    @Override
    public void delete(int invoiceId) {

    }
}
