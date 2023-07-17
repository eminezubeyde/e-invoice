package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.create.CreateCompanyRequest;
import com.example.einvoice.core.dto.CompanyDto;
import com.example.einvoice.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
     CompanyMapper MAPPER= Mappers.getMapper(CompanyMapper.class);
     Company requestToEntity(CreateCompanyRequest createCompanyRequest);

     CompanyDto entityToResponse(Company company);
}
