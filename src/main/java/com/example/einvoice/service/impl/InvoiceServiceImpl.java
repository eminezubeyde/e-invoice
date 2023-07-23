package com.example.einvoice.service.impl;

import com.example.einvoice.core.dto.InvoiceDto;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.InvoiceMapper;
import com.example.einvoice.core.message.InvoiceMessage;
import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Company;
import com.example.einvoice.entity.Invoice;
import com.example.einvoice.entity.Truck;
import com.example.einvoice.repository.InvoiceRepository;
import com.example.einvoice.service.CompanyService;
import com.example.einvoice.service.InvoiceService;
import com.example.einvoice.service.TruckService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final TruckService truckService;
    private final CompanyService companyService;

    @Override
    @Transactional
    public GeneralResult create(CreateInvoiceRequest createInvoiceRequest) throws EntityNotFoundException {
        Truck truck = truckService.findById(createInvoiceRequest.getTruckId());
        Company company = companyService.findById(createInvoiceRequest.getCompanyId());
        Invoice invoice = InvoiceMapper.MAPPER.requestToEntity(createInvoiceRequest);
        invoice.setActive(true);
        invoice.setProcessTime(LocalDateTime.now());
        invoice.setTruck(truck);
        invoice.setCompany(company);
        invoiceRepository.save(invoice);
        InvoiceDto invoiceDto = InvoiceMapper.MAPPER.entityToDto(invoice);
        return new DataResult<>(invoiceDto);
    }


    @Override
    public GeneralResult getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtos = invoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();
        return new DataResult<>("successful", invoiceDtos);
    }

    @Override
    public List<Invoice> getAllInvoice() {
        List<Invoice> invoices = invoiceRepository.findAll();

        return invoices;
    }

    @Override
    @Transactional
    public void delete(int invoiceId) throws EntityNotFoundException {
        Invoice invoice = invoiceRepository
                .findById(invoiceId)
                .orElseThrow(() -> new EntityNotFoundException(InvoiceMessage.NOT_FOUND.toString()));
        invoiceRepository.delete(invoice);
    }

}
