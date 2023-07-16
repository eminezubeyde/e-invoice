package com.example.einvoice.core.mapper;

import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.responses.CompanyResponse;
import com.example.einvoice.core.responses.DriverResponse;
import com.example.einvoice.model.Company;
import com.example.einvoice.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
     CompanyMapper MAPPER= Mappers.getMapper(CompanyMapper.class);
     Company requestToEntity(CompanyRequest companyRequest);

     CompanyResponse entityToResponse(Company company);
}
