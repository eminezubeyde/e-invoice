package com.example.einvoice.service;

import com.example.einvoice.core.requests.InvoiceRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Override
    public GeneralResult create(InvoiceRequest invoiceRequest) {
        return null;
    }

    @Override
    public GeneralResult update(InvoiceRequest invoiceRequest, int invoiceId) {
        return null;
    }

    @Override
    public void delete(int invoiceId) {

    }
}
