package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.ContactRequest;
import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.responses.ContactResponse;
import com.example.einvoice.core.responses.DriverResponse;
import com.example.einvoice.model.Contact;
import com.example.einvoice.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContactMapper {
     ContactMapper MAPPER= Mappers.getMapper(ContactMapper.class);
     Contact requestToEntity(ContactRequest contactRequest);

     ContactResponse entityToResponse(Contact contact);
}
