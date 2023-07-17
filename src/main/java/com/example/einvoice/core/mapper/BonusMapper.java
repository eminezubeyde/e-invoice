package com.example.einvoice.core.mapper;

import com.example.einvoice.core.dto.BonusDto;
import com.example.einvoice.core.requests.create.CreateBonusRequest;
import com.example.einvoice.model.Bonus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BonusMapper {
     BonusMapper MAPPER= Mappers.getMapper(BonusMapper.class);
     Bonus requestToEntity(CreateBonusRequest createBonusRequest);

     BonusDto entityToResponse(Bonus bonus);
}
