package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.BonusRequest;
import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.responses.BonusResponse;
import com.example.einvoice.core.responses.DriverResponse;
import com.example.einvoice.model.Bonus;
import com.example.einvoice.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BonusMapper {
     BonusMapper MAPPER= Mappers.getMapper(BonusMapper.class);
     Bonus requestToEntity(BonusRequest bonusRequest);

     BonusResponse entityToResponse(Bonus bonus);
}
