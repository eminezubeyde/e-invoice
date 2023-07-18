package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.dto.InvoiceDto;
import com.example.einvoice.model.Invoice;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceMapper MAPPER = Mappers.getMapper(InvoiceMapper.class);

    Invoice requestToEntity(CreateInvoiceRequest createInvoiceRequest);

    //source kaynak
    //target hedef
    @Mapping(target = "truckId", source = "invoice.truck.id")
    @Mapping(target = "companyId", source = "invoice.company.id")
    InvoiceDto entityToDto(Invoice invoice);

    @Mapping(target = "totalAmount", ignore = true)//ignore=true vererek dönüşüm işleminde total amount kısmını görmezden geliyoruz
    @AfterMapping // Dönüşüm işleminden sonra otomatik olarak çağrılması için
    default void calculateTotalAmount(Invoice invoice, @MappingTarget InvoiceDto invoiceDto) {
        BigDecimal totalAmount = (invoice.getAmount()
                .multiply(invoice.getKdvRate())
                .divide(BigDecimal.valueOf(100)))
                .add(invoice.getAmount());

        invoiceDto.setTotalAmount(totalAmount);
    }
}
