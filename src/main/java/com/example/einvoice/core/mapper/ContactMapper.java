package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.create.CreateContactRequest;
import com.example.einvoice.core.dto.ContactDto;
import com.example.einvoice.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContactMapper {
     ContactMapper MAPPER= Mappers.getMapper(ContactMapper.class);
     Contact requestToEntity(CreateContactRequest createContactRequest);

     ContactDto entityToResponse(Contact contact);
}
