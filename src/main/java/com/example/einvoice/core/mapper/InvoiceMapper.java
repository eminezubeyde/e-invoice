package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.requests.InvoiceRequest;
import com.example.einvoice.core.responses.DriverResponse;
import com.example.einvoice.core.responses.InvoiceResponse;
import com.example.einvoice.model.Driver;
import com.example.einvoice.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
     InvoiceMapper MAPPER= Mappers.getMapper(InvoiceMapper.class);
     Invoice requestToEntity(InvoiceRequest invoiceRequest);

     InvoiceResponse entityToResponse(Invoice invoice);
}
