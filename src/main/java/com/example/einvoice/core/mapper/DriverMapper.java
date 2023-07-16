package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.responses.DriverResponse;
import com.example.einvoice.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DriverMapper {
     DriverMapper MAPPER= Mappers.getMapper(DriverMapper.class);
     Driver requestToEntity(DriverRequest driverRequest);

     DriverResponse entityToResponse(Driver driver);
}
