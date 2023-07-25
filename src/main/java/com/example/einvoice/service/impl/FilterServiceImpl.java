package com.example.einvoice.service.impl;

import com.example.einvoice.core.dto.InvoiceDto;
import com.example.einvoice.core.dto.InvoicesDtoResponse;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.exception.GeneralException;
import com.example.einvoice.core.mapper.InvoiceMapper;
import com.example.einvoice.core.message.FilterMessage;
import com.example.einvoice.core.message.TruckMessage;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Company;
import com.example.einvoice.entity.Invoice;
import com.example.einvoice.entity.Truck;
import com.example.einvoice.repository.InvoiceRepository;
import com.example.einvoice.service.CompanyService;
import com.example.einvoice.service.FilterService;
import com.example.einvoice.service.InvoiceService;
import com.example.einvoice.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {
    private final InvoiceService invoiceService;
    private final TruckService truckService;
    private final CompanyService companyService;
    private final InvoiceRepository invoiceRepository;
    private final MessageSource messageSource;
    @Override
    public GeneralResult getTotalAmountOfInvoicesByMonthAndCompany(String companyName, LocalDateTime month) throws EntityNotFoundException {
        Company company = companyService.getByCompanyName(companyName);
        if (company == null) {
            throw new EntityNotFoundException(getMessage(FilterMessage.NOT_FOUND.getKey()));
            //TODO TESTİNİ YAP
        }
        List<Invoice> invoices = invoiceService.getAllInvoice();

        List<Invoice> filteredInvoices = invoices
                .stream()
                .filter(invoice -> invoice.getCompany().getName().equals(companyName) &&
                        invoice.getProcessTime().getMonth() == month.getMonth())
                .toList();


        if (filteredInvoices.isEmpty()) {
            throw new EntityNotFoundException(getMessage(FilterMessage.NOT_FOUND.getKey()));
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
            throw new EntityNotFoundException(getMessage(FilterMessage.NOT_FOUND.getKey()));
        }

        List<InvoiceDto> dtos = filteredInvoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();

        return new DataResult<>(getMessage(FilterMessage.SUCCESSFUL.getKey()),true,dtos);
    }

    @Override
    public GeneralResult getInvoicesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) throws EntityNotFoundException {
        List<Invoice> invoices = invoiceService.getAllInvoice();

        List<Invoice> filteredInvoices = invoices
                .stream()
                .filter(invoice -> invoice.getProcessTime().isAfter(startDate.minusSeconds(1)) &&
                        invoice.getProcessTime().isBefore(endDate.minusSeconds(1))).toList();

        if (filteredInvoices.isEmpty()) {
            throw new EntityNotFoundException(getMessage(FilterMessage.NOT_FOUND.getKey()));
        }

        List<InvoiceDto> dtos = filteredInvoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();

        return new DataResult<>(getMessage(FilterMessage.SUCCESSFUL.getKey()),true,dtos);
    }


    @Override
    public GeneralResult getInvoicesByTruckPlateAndMonth(String plate, LocalDateTime startDate, LocalDateTime endDate) throws EntityNotFoundException {
        InvoicesDtoResponse result = new InvoicesDtoResponse();
        Truck truck = truckService.getByPlate(plate);

        if (truck == null) {
            throw new EntityNotFoundException(getMessage(TruckMessage.NOT_FOUND.getKey()));
        }
        List<Invoice> invoices = invoiceService.getAllInvoice();

        List<Invoice> filteredInvoices = invoices.
                stream().
                filter(invoice -> invoice.getTruck().getPlate().equals(plate) &&
                        invoice.getProcessTime().isBefore(startDate.minusSeconds(1)) &&
                        invoice.getProcessTime().isAfter(endDate.minusSeconds(1))).toList();

        if (filteredInvoices.isEmpty()) {
            throw new EntityNotFoundException(getMessage(FilterMessage.INVALID_DATE.getKey()));
        }

        List<InvoiceDto> invoiceDtos = filteredInvoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();
        result.setInvoiceDtos(invoiceDtos);
        result.setCurrentPage(1);
        BigDecimal totalAmount = filteredInvoices.stream()
                .map(Invoice::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        result.setTotalAmount(totalAmount);

        return new DataResult<>(getMessage(FilterMessage.SUCCESSFUL.getKey()),true, invoiceDtos);
    }

    // buraya kadar yapılan ve daha da farklı değişkenlerle tüm filtreleme işleminin tek metoda dönüştürülmüş hali

    @Override
    public GeneralResult filterInvoices(int page, int size, String companyName, String plate, BigDecimal minTotalAmount, BigDecimal maxTotalAmount, LocalDate startDate, LocalDate endDate) throws GeneralException {
        InvoicesDtoResponse result = new InvoicesDtoResponse();

        if ((page - 1) < 0) {
            throw new GeneralException(getMessage(FilterMessage.PAGE_COUNT_INVALID.getKey()));
        }
        // 1. durum start date < end date
        PageRequest pageRequest = PageRequest.of((page - 1), size);

        checkDateIsValid(startDate, endDate);

        createDateHelper(startDate, endDate);


        List<Invoice> invoices = new ArrayList<>();

        if (companyName != null) {
            invoices = invoiceRepository
                    .findByProcessTimeBetweenAndCompanyName(startDate.atTime(23, 59)
                            , endDate.atTime(23, 59), pageRequest, companyName);

        } else if (plate != null) {
            invoices = invoiceRepository
                    .findByProcessTimeBetweenAndTruckPlate(startDate.atTime(23, 59)
                            , endDate.atTime(23, 59), pageRequest, plate);

        } else if (minTotalAmount != null && minTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            invoices = invoiceRepository.findByTotalAmountGreaterThan(minTotalAmount);

        } else if (plate != null && companyName != null) {
            invoices = invoiceRepository.findByProcessTimeBetweenAndTruckPlateAndCompanyName(startDate.atTime(23, 59)
                    , endDate.atTime(23, 59), pageRequest, companyName, plate);

        } else if (maxTotalAmount != null && maxTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            invoices = invoiceRepository.findByTotalAmountLessThan(maxTotalAmount);

        }


        BigDecimal totalAmountOfInvoices = invoices.stream()
                .map(Invoice::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        List<InvoiceDto> invoiceDtos = invoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();


        result.setInvoiceDtos(invoiceDtos);
        result.setCurrentPage(page + 1);//geçerli sayfa
        result.setInvoiceCount(invoices.size());
        //int totalCount = (int) invoiceRepository.count(); // tODO düzgün çalışmaz.kontrol yap
        result.setTotalPage((invoices.size()) / size);//toplam sayfa
        result.setTotalAmount(totalAmountOfInvoices);

        return new DataResult<>(FilterMessage.SUCCESSFUL.toString(), true, result);
    }

    private void checkDateIsValid(LocalDate startDate, LocalDate endDate) throws GeneralException {
        if (startDate != null && endDate != null) {
            if (!startDate.isEqual(endDate) && endDate.isBefore(startDate)) {
                throw new GeneralException(getMessage(FilterMessage.INVALID_DATE.getKey()));
            }
            if (endDate.isAfter(LocalDate.now())) {
                throw new GeneralException(getMessage(FilterMessage.BAD_REQUEST.getKey()));
            }
        }


    }

    private void createDateHelper(LocalDate startDate, LocalDate endDate) {
        if (startDate == null && endDate == null) {
            var now = LocalDateTime.now();
            startDate = LocalDate.from(LocalDateTime.of(now.getYear(), (now.getMonth().getValue() - 1), now.getDayOfMonth(), now.getHour(), now.getMinute()));
            endDate = LocalDate.from(LocalDateTime.of(startDate.getYear(), (startDate.getMonth().getValue() + 1), startDate.getDayOfMonth(), now.getHour(), now.getMinute()));
        } else {
            if (startDate == null) {
                startDate = LocalDate.from(LocalDateTime.of(endDate.getYear(), (endDate.getMonth().getValue() - 1), endDate.getDayOfMonth(), 0, 0));
            }
            if (endDate == null) {
                endDate = LocalDate.from(LocalDateTime.of(startDate.getYear(), (startDate.getMonth().getValue() + 1), startDate.getDayOfMonth(), 0, 0));
            }
        }

    }
    private String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key,null,locale);
    }


}
