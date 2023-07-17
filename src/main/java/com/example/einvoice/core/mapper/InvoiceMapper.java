package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.create.CreateInvoiceRequest;
import com.example.einvoice.core.dto.InvoiceDto;
import com.example.einvoice.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
     InvoiceMapper MAPPER= Mappers.getMapper(InvoiceMapper.class);
     Invoice requestToEntity(CreateInvoiceRequest createInvoiceRequest);

     InvoiceDto entityToResponse(Invoice invoice);
}
