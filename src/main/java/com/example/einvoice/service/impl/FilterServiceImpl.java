package com.example.einvoice.service.impl;

import com.example.einvoice.core.dto.InvoiceDto;
import com.example.einvoice.core.dto.InvoicesDtoResponse;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.InvoiceMapper;
import com.example.einvoice.core.message.CompanyMessage;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {
    private final InvoiceService invoiceService;
    private final TruckService truckService;
    private final CompanyService companyService;
    private final InvoiceRepository invoiceRepository;

    @Override
    public GeneralResult getTotalAmountOfInvoicesByMonthAndCompany(String companyName, LocalDateTime month) throws EntityNotFoundException {
        Company company = companyService.getByCompanyName(companyName);
        if (company == null) {
            throw new EntityNotFoundException(CompanyMessage.NOT_FOUND.toString());
            //TODO TESTİNİ YAP
        }
        List<Invoice> invoices = invoiceService.getAllInvoice();

        List<Invoice> filteredInvoices = invoices
                .stream()
                .filter(invoice -> invoice.getCompany().getName().equals(companyName) &&
                        invoice.getProcessTime().getMonth() == month.getMonth())
                .toList();


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

    @Override
    public GeneralResult getInvoicesByTruckPlateAndMonth(String plate, LocalDateTime startDate, LocalDateTime endDate) throws EntityNotFoundException {
        InvoicesDtoResponse result=new InvoicesDtoResponse();
        Truck truck = truckService.getByPlate(plate);

        if (truck == null) {
            throw new EntityNotFoundException(TruckMessage.NOT_FOUND.toString());
        }
        List<Invoice> invoices = invoiceService.getAllInvoice();

        List<Invoice> filteredInvoices = invoices.
                stream().
                filter(invoice -> invoice.getTruck().getPlate().equals(plate) &&
                        invoice.getProcessTime().isBefore(startDate.minusSeconds(1)) &&
                        invoice.getProcessTime().isAfter(endDate.minusSeconds(1))).toList();

        if (filteredInvoices.isEmpty()) {
            throw new EntityNotFoundException(FilterMessage.NOT_FOUND_DATE.toString());
        }

        List<InvoiceDto> invoiceDtos = filteredInvoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();
        result.setInvoiceDtos(invoiceDtos);
        result.setCurrentPage(1);
        BigDecimal totalAmount = filteredInvoices.stream()
                .map(Invoice::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);//TODO BİR KAÇ SONUCU NASIL GÖNDEREBİLİRİM
        result.setTotalAmount(totalAmount);

        return new DataResult<>("successful", invoiceDtos);
    }

    @Override
    public GeneralResult filterInvoices(int page, int size, String companyName, String plate, LocalDate startDate, LocalDate endDate) throws EntityNotFoundException {// TODO bigdecimeal
        InvoicesDtoResponse result=new InvoicesDtoResponse();

        if((page-1)<0){
            throw new EntityNotFoundException("sayfa sayısı geçersiz.");
        }
        // 1. durum start date < end date
        PageRequest pageRequest = PageRequest.of((page-1), size);

        checkDateIsValid(startDate,endDate);

        if(startDate==null && endDate==null){
            var now =LocalDateTime.now();
            startDate= LocalDate.from(LocalDateTime.of(now.getYear(),(now.getMonth().getValue()-1),now.getDayOfMonth(),now.getHour(),now.getMinute()));
            endDate=LocalDate.from(LocalDateTime.of(startDate.getYear(),(startDate.getMonth().getValue()+1),startDate.getDayOfMonth(),now.getHour(),now.getMinute()));
        }else{
            if(startDate==null){
                startDate= LocalDate.from(LocalDateTime.of(endDate.getYear(),(endDate.getMonth().getValue()-1),endDate.getDayOfMonth(),0,0));
            }
            if(endDate==null){
                endDate=LocalDate.from(LocalDateTime.of(startDate.getYear(),(startDate.getMonth().getValue()+1),startDate.getDayOfMonth(),0,0));
            }
        }



        List <Invoice> invoices=companyName !=null ? // TODO helper sınıfa gönder ve son zamanı ayarla
                invoiceRepository.findByProcessTimeBetweenAndCompanyName(startDate.atTime(23,59),endDate.atTime(23,59),pageRequest,companyName)
                : invoiceRepository.findByProcessTimeBetween(startDate.atStartOfDay(),endDate.atStartOfDay(),pageRequest);

        List<InvoiceDto> invoiceDtos = invoices
                .stream()
                .map(InvoiceMapper.MAPPER::entityToDto)
                .toList();

        result.setInvoiceDtos(invoiceDtos);
        result.setCurrentPage(page+1);
        long totalCount = invoiceRepository.count(); // tODO düzgün çalışmaz.
        result.setTotalPage((int) (totalCount/size));

        BigDecimal totalAmount = invoiceDtos.stream()
                .map(InvoiceDto::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        result.setTotalAmount(totalAmount);
        return new DataResult<>("successful", result);
    }

    private void checkDateIsValid(LocalDate startDate, LocalDate endDate) throws EntityNotFoundException {
        if(startDate!=null && endDate!=null){
            if(!startDate.isEqual(endDate) && endDate.isBefore(startDate)){
                throw new EntityNotFoundException("bitiş tarihi başlangıç tarihinden sonra olmalıdır.");
            }
        }// todo end date bugunden sonra olursa hata


    }
}
