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
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ContactService contactService;
    private final MessageSource messageSource;

    @Override
    public GeneralResult create(CreateCompanyRequest createCompanyRequest) throws AlreadyExistsException, EntityNotFoundException {
        if (companyRepository.existsByTaxNumber(createCompanyRequest.getTaxNumber())) {
            throw new AlreadyExistsException(getMessage(CompanyMessage.ALREADY_EXISTS.getKey()));
        }
        if (companyRepository.existsByName(createCompanyRequest.getName())) {
            throw new AlreadyExistsException(getMessage(CompanyMessage.ALREADY_EXISTS.getKey()));
        }

        Company company = CompanyMapper.MAPPER.requestToEntity(createCompanyRequest);
        Contact contact = ContactMapper.MAPPER.requestToEntity(createCompanyRequest.getContactRequest());
        company.setContact(contact);
        contactService.create(contact);
        companyRepository.save(company);
        CompanyDto companyDto = CompanyMapper.MAPPER.entityToResponse(company);
        return new DataResult<>(getMessage(CompanyMessage.SUCCESSFUL.getKey()),true,companyDto);
    }
    //todo contact validasyon nereye eklenecek

    @Override
    @Transactional
    public GeneralResult update(UpdateCompanyRequest updateCompanyRequest, int companyId) throws EntityNotFoundException {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(CompanyMessage.NOT_FOUND.getKey())));
        setUpdateCompanyRequestToEntity(updateCompanyRequest, company);
        companyRepository.save(company);
        CompanyDto companyDto = CompanyMapper.MAPPER.entityToResponse(company);
        return new DataResult<>(getMessage(CompanyMessage.SUCCESSFUL.getKey()),true,companyDto);
    }

    @Override
    public GeneralResult getAll() {
        List<Company> companyList = companyRepository.findAll();
        List<CompanyDto> companyDtoList = companyList
                .stream()
                .map(CompanyMapper.MAPPER::entityToResponse)
                .toList();
        return new DataResult<>(getMessage(CompanyMessage.SUCCESSFUL.getKey()),true,companyDtoList);
    }

    @Override
    @Transactional
    public void deleteById(int companyId) throws EntityNotFoundException {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(CompanyMessage.NOT_FOUND.getKey())));
        Contact contact = company.getContact();
        contactService.deleteByID(contact);
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
                .orElseThrow(() -> new EntityNotFoundException(getMessage(CompanyMessage.NOT_FOUND.getKey())));
    }

    @Override
    public Company getByCompanyName(String companyName) {
        return companyRepository.findByName(companyName);
    }


    private void setUpdateCompanyRequestToEntity(UpdateCompanyRequest updateCompanyRequest, Company company) {

        company.setName(updateCompanyRequest.getName());
        company.setTaxNumber(updateCompanyRequest.getTaxNumber());

    }

    private String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

}
