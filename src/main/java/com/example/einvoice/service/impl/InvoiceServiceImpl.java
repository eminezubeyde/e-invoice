package com.example.einvoice.service.impl;

import com.example.einvoice.core.constant.message.InvoiceMessage;
import com.example.einvoice.core.dto.InvoiceDto;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.InvoiceMapper;
import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.*;
import com.example.einvoice.repository.InvoiceRepository;
import com.example.einvoice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final TruckService truckService;
    private final CompanyService companyService;
    private final MessageSource messageSource;
    private final DriverService driverService;
    private final BonusService bonusService;

    @Override
    @Transactional
    public GeneralResult create(CreateInvoiceRequest createInvoiceRequest) throws EntityNotFoundException {
        Truck truck = truckService.findById(createInvoiceRequest.getTruckId());
        Driver driver = driverService.getById(truck.getDriver().getId());
        Company company = companyService.findById(createInvoiceRequest.getCompanyId());
        Invoice invoice = InvoiceMapper.MAPPER.requestToEntity(createInvoiceRequest);
        invoice.setActive(true);
        invoice.setProcessTime(LocalDateTime.now());
        invoice.setTruck(truck);
        invoice.setCompany(company);
        invoiceRepository.save(invoice);

        Bonus bonus = new Bonus();
        bonus.setAmount(invoice.getTotalAmount());
        bonus.setDriver(truck.getDriver());
        bonus.setFromCity(invoice.getFromCity());
        bonus.setToCity(invoice.getToCity());
        bonus.setProcessTime(invoice.getProcessTime());
        driver.setBonuses(List.of(bonus));
        bonusService.create(bonus);

        InvoiceDto invoiceDto = InvoiceMapper.MAPPER.entityToDto(invoice);
        return new DataResult<>(getMessage(InvoiceMessage.SUCCESSFUL.getKey()), true, invoiceDto);
    }


    @Override
    public GeneralResult getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtos = invoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();
        return new DataResult<>(getMessage(InvoiceMessage.SUCCESSFUL.getKey()), true, invoiceDtos);
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
                .orElseThrow(() -> new EntityNotFoundException(getMessage(InvoiceMessage.NOT_FOUND.getKey())));
        invoiceRepository.delete(invoice);
    }

    private String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }
}
