package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.create.CreateDriverRequest;
import com.example.einvoice.core.dto.DriverDto;
import com.example.einvoice.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DriverMapper {
     DriverMapper MAPPER= Mappers.getMapper(DriverMapper.class);
     Driver requestToEntity(CreateDriverRequest createDriverRequest);

     DriverDto entityToResponse(Driver driver);
}
