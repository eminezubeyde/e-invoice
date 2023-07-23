package com.example.einvoice.service.impl;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.CompanyMapper;
import com.example.einvoice.core.mapper.ContactMapper;
import com.example.einvoice.core.message.CompanyMessage;
import com.example.einvoice.core.requests.create.CreateCompanyRequest;
import com.example.einvoice.core.dto.CompanyDto;
import com.example.einvoice.core.requests.update.UpdateCompanyRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Company;
import com.example.einvoice.entity.Contact;
import com.example.einvoice.repository.CompanyRepository;
import com.example.einvoice.service.CompanyService;
import com.example.einvoice.service.ContactService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ContactService contactService;

    @Override
    public GeneralResult create(CreateCompanyRequest createCompanyRequest) throws AlreadyExistsException, EntityNotFoundException {
        if (companyRepository.existsByTaxNumber(createCompanyRequest.getTaxNumber())) {
            throw new AlreadyExistsException(CompanyMessage.ALREADY_EXISTS.toString());
        }

        Company company = CompanyMapper.MAPPER.requestToEntity(createCompanyRequest);
        Contact contact = ContactMapper.MAPPER.requestToEntity(createCompanyRequest.getContactRequest());
        company.setContact(contact);
        contactService.create(contact);
        companyRepository.save(company);
        CompanyDto companyDto = CompanyMapper.MAPPER.entityToResponse(company);
        return new DataResult<>(companyDto);
    }

    @Override
    @Transactional
    public GeneralResult update(UpdateCompanyRequest updateCompanyRequest, int companyId) throws EntityNotFoundException {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(CompanyMessage.NOT_FOUND.toString()));
        setUpdateCompanyRequestToEntity(updateCompanyRequest, company);
        companyRepository.save(company);
        CompanyDto companyDto = CompanyMapper.MAPPER.entityToResponse(company);
        return new DataResult<>(companyDto);
    }

    @Override
    public GeneralResult getAll() {
        List<Company> companyList = companyRepository.findAll();
        List<CompanyDto> companyDtoList = companyList
                .stream()
                .map(CompanyMapper.MAPPER::entityToResponse)
                .toList();
        return new DataResult<>(companyDtoList);
    }

    @Override
    @Transactional
    public void deleteById(int companyId) throws EntityNotFoundException {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(CompanyMessage.NOT_FOUND.toString()));
        Contact contact = company.getContact();
        contactService.delete(contact);
        companyRepository.deleteById(companyId);

    }


    @Override
    public boolean existsById(int companyId) {
        return companyRepository.existsById(companyId);
    }

    @Override
    public Company findById(int companyId) throws EntityNotFoundException {

        return companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(CompanyMessage.NOT_FOUND.toString()));
    }

    @Override
    public Company getByCompanyName(String companyName) {
        return companyRepository.findByName(companyName);
    }


    private void setUpdateCompanyRequestToEntity(UpdateCompanyRequest updateCompanyRequest, Company company) {

        company.setName(updateCompanyRequest.getName());
        company.setTaxNumber(updateCompanyRequest.getTaxNumber());

    }

}
