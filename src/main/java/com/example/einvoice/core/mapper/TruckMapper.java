package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.create.CreateTruckRequest;
import com.example.einvoice.core.dto.TruckDto;
import com.example.einvoice.model.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TruckMapper {
     TruckMapper MAPPER= Mappers.getMapper(TruckMapper.class);
     Truck requestToEntity(CreateTruckRequest createTruckRequest);

     TruckDto entityToResponse(Truck truck);
}
