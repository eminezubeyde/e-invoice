package com.example.einvoice.service.impl;

import com.example.einvoice.core.dto.InvoiceDto;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.InvoiceMapper;
import com.example.einvoice.core.message.FilterMessage;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Invoice;
import com.example.einvoice.service.FilterService;
import com.example.einvoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {
    private final InvoiceService invoiceService;

    @Override
    public GeneralResult getTotalAmountOfInvoicesByMonthAndCompany(String companyName, LocalDateTime month) throws EntityNotFoundException {
        List<Invoice> invoices = invoiceService.getAllInvoice();

        List<Invoice> filteredInvoices = invoices
                .stream()
                .filter(invoice -> invoice.getCompany().getName().equals(companyName) &&
                        invoice.getProcessTime().getMonth() == month.getMonth())
                .toList();
        //todo filteredInvoices boş geliyor
        //TODO totalAMOUNT HESAPLANMIYOR?
        //tODO POSTMANDA istek atarken tarih detayı sadece ay nasıl verilmeli

        if (filteredInvoices.isEmpty()) {
            throw new EntityNotFoundException(FilterMessage.NOT_FOUND.toString());
        }

        BigDecimal totalAmount = filteredInvoices
                .stream()
                .map(Invoice::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new DataResult<>("istenilen şirketin ,istenilen aydaki kesilen tüm faturalarının toplamı", totalAmount);
    }

    @Override
    public GeneralResult getInvoicesByMonthAndCompany(String companyName, LocalDateTime month) throws EntityNotFoundException {
        List<Invoice> invoices = invoiceService.getAllInvoice();
        List<Invoice> filteredInvoices = invoices
                .stream()
                .filter(invoice -> invoice.getCompany().getName().equals(companyName) &&
                        invoice.getProcessTime().getMonth() == month.getMonth())
                .toList();
        if (filteredInvoices.isEmpty()) {
            throw new EntityNotFoundException(FilterMessage.NOT_FOUND.toString());
        }

        List<InvoiceDto> dtos = filteredInvoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();

        return new DataResult<>(dtos);
    }

    @Override
    public GeneralResult getInvoicesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) throws EntityNotFoundException {
        List<Invoice> invoices = invoiceService.getAllInvoice();

        List<Invoice> filteredInvoices = invoices
                .stream()
                .filter(invoice -> invoice.getProcessTime().isAfter(startDate.minusSeconds(1)) &&
                        invoice.getProcessTime().isBefore(endDate.minusSeconds(1))).toList();

        if (filteredInvoices.isEmpty()) {
            throw new EntityNotFoundException(FilterMessage.NOT_FOUND_DATE.toString());
        }

        List<InvoiceDto> dtos = filteredInvoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();

        return new DataResult<>(dtos);
    }
}
